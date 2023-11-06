package com.steinmetz.msu.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.steinmetz.msu.criminalintent.databinding.ListItemCrimeBinding
import com.steinmetz.msu.criminalintent.databinding.ListItemCrimeRequiresPoliceBinding

// ViewHolder for list_item_crime layout when crime instance property has requiresPolice = false
class CrimeHolder(
    private val crimeNoPoliceBinding: ListItemCrimeBinding
) : ViewHolder(crimeNoPoliceBinding.root) {
    fun bind(crime: Crime) {
        crimeNoPoliceBinding.crimeTitleTextview.text = crime.title
        crimeNoPoliceBinding.crimeDateTextview.text = crime.date

        crimeNoPoliceBinding.root.setOnClickListener {
            Toast.makeText(
                crimeNoPoliceBinding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
        // Hides or shows image based on crime instance property isSolved
        crimeNoPoliceBinding.crimeSolved.visibility = if (crime.isSolved) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

// ViewHolder for list_item_crime_requires_police layout when crime instance property
// has requiresPolice = false
class CrimeRequiresPoliceHolder(
    private val crimeRequiresPoliceBinding: ListItemCrimeRequiresPoliceBinding
) : ViewHolder(crimeRequiresPoliceBinding.root) {
    fun bind(crime: Crime) {
        crimeRequiresPoliceBinding.crimeTitleTextview.text = crime.title
        crimeRequiresPoliceBinding.crimeDateTextview.text = crime.date

        crimeRequiresPoliceBinding.root.setOnClickListener {
            Toast.makeText(
                crimeRequiresPoliceBinding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
// Toast Notification for clicking the Contact Police Button
        crimeRequiresPoliceBinding.crimeRequiresPoliceButton.setOnClickListener {
            Toast.makeText(
                crimeRequiresPoliceBinding.root.context,
                "Ohh, I'm calling the police!!",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Hides or shows image based on crime instance property isSolved
        crimeRequiresPoliceBinding.crimeSolved.visibility = if (crime.isSolved) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}


// Adapter that extends multiple ViewHolders
class CrimeListAdapter(
    private var crimes: MutableList<Crime>
) : RecyclerView.Adapter<ViewHolder>() {

// Used to create unique values that correspond with each view
    companion object {
        const val CRIME_NO_POLICE = 0
        const val CRIME_REQUIRES_POLICE = 1
    }

// Returns the unique value depending the boolean requiresPolice
    override fun getItemViewType(position: Int): Int {
        return when {
            crimes[position].requiresPolice -> CRIME_REQUIRES_POLICE
            else -> CRIME_NO_POLICE
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return if (viewType == CRIME_NO_POLICE) {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            CrimeHolder(binding)
        } else {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemCrimeRequiresPoliceBinding.inflate(inflater, parent, false)
            CrimeRequiresPoliceHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == CRIME_NO_POLICE) {
            (holder as CrimeHolder).bind(crimes[position])
        } else {
            (holder as CrimeRequiresPoliceHolder).bind(crimes[position])
        }
    }

    override fun getItemCount() = crimes.size
}






