package com.steinmetz.msu.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.steinmetz.msu.criminalintent.databinding.ListItemCrimeBinding
import java.util.*

// ViewHolder for list_item_crime layout when crime instance property has requiresPolice = false



// Adapter that extends multiple ViewHolders
class CrimeListAdapter(
    private var crimeList: Crime
) : RecyclerView.Adapter<ViewHolder>() {

    init {
        println("Run at beginning of Adapter -- ${crimeList} crimes collected")
        println("title of crimes -- ${crimeList.title}")
    }
//// Used to create unique values that correspond with each view
//    companion object {
//        const val CRIME_NO_POLICE = 0
//        const val CRIME_REQUIRES_POLICE = 1
//    }
// Returns the unique value depending the boolean requiresPolice
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

     private var testCrime = Crime(id = UUID.randomUUID(),
         title = "dj",
         date = 234324,
         isSolved = 0,
         )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("ON BIND VIEWHOLDER")
        (holder as CrimeHolder).bind(crimeList)
    }

    class CrimeHolder(
        private val crimeBinding: ListItemCrimeBinding
    ) : ViewHolder(crimeBinding.root) {

        fun bind(crime: Crime) {

            val currentCrime = crime

            crimeBinding.crimeTitleTextview.text = currentCrime.title
            crimeBinding.crimeDateTextview.text = currentCrime.date.toString()



            crimeBinding.root.setOnClickListener {
                Toast.makeText(
                    crimeBinding.root.context,
                    "${crime.toString()} clicked!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // Hides or shows image based on crime instance property isSolved
            crimeBinding.crimeSolved.visibility = if (true) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
    override fun getItemCount() = crimeList

    init {
        println("End of Adapter")
    }
}






