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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

    private var viewHolderCounter = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("ON BIND VIEWHOLDER")
        (holder as CrimeHolder).bind(crimeList, viewHolderCounter)
        viewHolderCounter = holder.absoluteAdapterPosition
    }



    class CrimeHolder(
        private val crimeBinding: ListItemCrimeBinding
    ) : ViewHolder(crimeBinding.root) {
        fun bind(crime: List<Crime>, viewHolderCounter: Int) {

             @RequiresApi(Build.VERSION_CODES.O)
             fun formatDate(): String {

                 val localDate: LocalDate = LocalDate.now()
                // formatter sets the pattern for the date output in "Sunday, November 5, 2023" format
                val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")
                // date is assigned the value of a formatted localDate to the above pattern
                val date: String = localDate.format(formatter)
                 val long = crime[viewHolderCounter].date.toLong()
                crime[viewHolderCounter].date
                return date
            }
            crimeBinding.crimeTitleTextview.text = crime[viewHolderCounter].title
            crimeBinding.crimeDateTextview.text = formatDate()
            crimeBinding.root.setOnClickListener {
                Toast.makeText(
                    crimeBinding.root.context,
                    "${crime[viewHolderCounter].title} clicked!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // Hides or shows image based on crime instance property isSolved
            crimeBinding.crimeSolved.visibility = if (crime[viewHolderCounter].isSolved == 0) {
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






