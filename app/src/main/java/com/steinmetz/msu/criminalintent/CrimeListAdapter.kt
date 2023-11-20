package com.steinmetz.msu.criminalintent

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.steinmetz.msu.criminalintent.databinding.ListItemCrimeBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

// ViewHolder for list_item_crime layout when crime instance property has requiresPolice = false


class CrimeHolder(
    private val crimeBinding: ListItemCrimeBinding
) : ViewHolder(crimeBinding.root) {
    fun bind(crime: Crime, onCrimeClicked: (crimeId: UUID) -> Unit) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun formatDate(): String {
            val localDate: LocalDate = LocalDate.now()
            // formatter sets the pattern for the date output in "Sunday, November 5, 2023" format
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")
            // date is assigned the value of a formatted localDate to the above pattern
            val date: String = localDate.format(formatter)
            val long = crime.date.toLong()
            crime.date
            return date
        }
        crimeBinding.root.setOnClickListener {
            onCrimeClicked(crime.id)
        }
        crimeBinding.crimeTitleTextview.text = crime.title
        crimeBinding.crimeDateTextview.text = formatDate()

        // Hides or shows image based on crime instance property isSolved
        crimeBinding.crimeSolved.visibility = if (crime.isSolved == 1) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

// Adapter that extends multiple ViewHolders
class CrimeListAdapter(
    private val crimeList: List<Crime>, private val onCrimeClicked: (crimeId: UUID) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crime = crimeList[position]
        (holder as CrimeHolder).bind(crime, onCrimeClicked)
    }

    override fun getItemCount() = crimeList.size
}






