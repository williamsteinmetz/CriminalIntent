package com.steinmetz.msu.criminalintent

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.steinmetz.msu.criminalintent.databinding.ListItemCrimeBinding
import java.util.Date

// ViewHolder for list_item_crime layout when crime instance property has requiresPolice = false



// Adapter that extends multiple ViewHolders
class CrimeListAdapter(
    private var crimeList: List<Crime>
) : RecyclerView.Adapter<ViewHolder>() {

    init {
        println("Run at beginning of Adapter -- $crimeList crimes collected")
        println("title of crimes -- ${crimeList.map { it.title }}")
    }
//// Used to create unique values that correspond with each view
//    companion object {
//        const val CRIME_NO_POLICE = 0
//        const val CRIME_REQUIRES_POLICE = 1
//    }
// Returns the unique value depending on the boolean requiresPolice
//    override fun getItemViewType(position: Int): Int {
//        return 0
//    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }
    private var counter = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("ON BIND VIEWHOLDER")
        (holder as CrimeHolder).bind(crimeList, counter)
        counter++
    }

    class CrimeHolder(
        private val crimeBinding: ListItemCrimeBinding
    ) : ViewHolder(crimeBinding.root) {


        fun bind(crime: List<Crime>, counter: Int) {

            val long = crime[counter].date.toLong()
            crimeBinding.crimeTitleTextview.text = crime[counter].title
            crimeBinding.crimeDateTextview.text = Date(long).toString()
            println("Run at beginning of crimeHolder Bind -- ${crime.size} crimes collected")
            println("title of crimes -- ${crime.map { it.title }}")



            crimeBinding.root.setOnClickListener {
                Toast.makeText(
                    crimeBinding.root.context,
                    "$crime clicked!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // Hides or shows image based on crime instance property isSolved
            crimeBinding.crimeSolved.visibility = if (crime[counter].isSolved == 0) {
                View.VISIBLE
            } else {
                View.GONE
            }


        }
    }
    override fun getItemCount() = crimeList.size

    init {
        println("End of Adapter")
    }
}






