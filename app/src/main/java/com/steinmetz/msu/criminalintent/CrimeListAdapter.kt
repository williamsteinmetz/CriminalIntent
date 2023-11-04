package com.steinmetz.msu.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.steinmetz.msu.criminalintent.databinding.ListItemCrimeBinding
import com.steinmetz.msu.criminalintent.databinding.ListItemCrimeRequiresPoliceBinding

// ViewHolder for layout where requiresPolice = false
class CrimeHolder(
    private val crimeNoPolice: ListItemCrimeBinding
) : ViewHolder(crimeNoPolice.root) {
    fun bind(crime: Crime) {
        crimeNoPolice.crimeTitleTextview.text = crime.title
        crimeNoPolice.crimeDateTextview.text = crime.date.toString()

        crimeNoPolice.root.setOnClickListener {
            Toast.makeText(
                crimeNoPolice.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
// ViewHolder for layout where requiresPolice = true
class CrimeRequiresPoliceHolder(
    private val crimeRequiresPolice: ListItemCrimeRequiresPoliceBinding
) : ViewHolder(crimeRequiresPolice.root) {
    fun bind(crime: Crime) {
        crimeRequiresPolice.crimeTitleTextview.text = crime.title
        crimeRequiresPolice.crimeDateTextview.text = crime.date.toString()

        crimeRequiresPolice.root.setOnClickListener {
            Toast.makeText(
                crimeRequiresPolice.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
// Toast Notification for clicking the Contact Police Button
        crimeRequiresPolice.crimeRequiresPoliceButton.setOnClickListener {
            Toast.makeText(
                crimeRequiresPolice.root.context,
                "Ohh, I'm calling the police!!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}


// Adapter that extends multiple ViewHolders
class CrimeListAdapter(
    private var crimes: MutableList<Crime>
) : RecyclerView.Adapter<ViewHolder>() {


    companion object {
        const val CRIME_NO_POLICE = 0
        const val CRIME_REQUIRES_POLICE = 1
    }


    override fun getItemViewType(position: Int): Int {
        return when {
            crimes[position].requiresPolice -> CRIME_REQUIRES_POLICE
            else -> CRIME_NO_POLICE
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : ViewHolder {
        return if (viewType == CRIME_NO_POLICE) {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            CrimeHolder(binding)
        }else {
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






