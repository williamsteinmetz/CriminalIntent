package com.steinmetz.msu.criminalintent

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.steinmetz.msu.criminalintent.databinding.FragmentCrimeDetailBinding
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

// Fragment used to input crime information.

class CrimeDetailFragment : Fragment() {



    private val args: CrimeDetailFragmentArgs by navArgs()

    private val crimeDetailViewModel: CrimeDetailViewModel by viewModels {
        CrimeDetailViewModelFactory(args.crimeId)
    }


    private var _binding: FragmentCrimeDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCrimeDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            crimeTitle.doOnTextChanged { text, _, _, _ ->
                crimeDetailViewModel.updateCrime { oldCrime ->
                    oldCrime.copy(title = text.toString())
                }
            }

            crimeDate.apply {
                isEnabled = false
            }

            crimeSolved.setOnCheckedChangeListener { _, isChecked ->
                crimeDetailViewModel.updateCrime { oldCrime ->
                    oldCrime.copy(
                        isSolved = when (isChecked) {
                            true -> 1
                            else -> 0
                        }
                    )
                }
            println(isChecked)
            println("Crime has been updated with the following value: $isChecked")
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                crimeDetailViewModel.crime.collect { crime ->
                    crime?.let { updateUi(it) }
                    println("This should be the value passed to _crime: " + crime?.isSolved.toString())
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUi(crime: Crime) {
        binding.apply {
            @RequiresApi(Build.VERSION_CODES.O)
            if (crimeTitle.text.toString() != crime.title) {
                crimeTitle.setText(crime.title)
            }

            fun formatDate(dateInt: Int): String {
                val long = dateInt.toLong()
                val crimeDate = Date(long)
                // formatter sets the pattern for the date output in "Sunday, November 5, 2023" format
                val format = SimpleDateFormat("EEEE, MMMM d, yyyy")
                // date is assigned the value of a formatted localDate to the above pattern
                return format.format(crimeDate)
            }

            crimeDate.text = formatDate(crime.date)
            crimeSolved.isChecked = when(crime.isSolved) {
                1 -> true
                else -> false
            }
        }
    }
}