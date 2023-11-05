package com.steinmetz.msu.criminalintent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
class CrimeListViewModel : ViewModel() {

    val crimes = mutableListOf<Crime>()

    // Chapter 11 3b solution: localeDate acquires the current date
    private val localDate: LocalDate = LocalDate.now()

    // formatter sets the pattern for the date output in "Sunday, November 5, 2023" format
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")

    // date is assigned the value of a formatted localDate to the above pattern
    @RequiresApi(Build.VERSION_CODES.O)
    val date: String = localDate.format(formatter)


    // Iterates for 100 instances of the crime object. The view of each instance will
    // be displayed differently depending on the booleans isSolved and requiresPolice
    init {
        for (i in 1 until 101) {
            val crime = Crime(
                id = UUID.randomUUID(),
                title = "Crime #$i",
                date = date,
                isSolved = Random.nextBoolean(), // To randomize the solved image on each crime view
                requiresPolice = Random.nextBoolean() // To randomize the requiresPolice boolean for viewType testing in the RecyclerView
            )

            crimes += crime
        }
    }

}