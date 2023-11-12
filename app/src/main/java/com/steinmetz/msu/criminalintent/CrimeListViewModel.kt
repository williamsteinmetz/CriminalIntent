package com.steinmetz.msu.criminalintent

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
import kotlin.random.Random

private const val TAG = "CrimeListViewModel"

@RequiresApi(Build.VERSION_CODES.O)
class CrimeListViewModel : ViewModel() {

    val crimes = mutableListOf<Crime>()

    // Iterates for 100 instances of the crime object. The view of each instance will
    // be displayed differently depending on the booleans isSolved and requiresPolice
    init {
        Log.d(TAG, "init starting")
        viewModelScope.launch {
            Log.d(TAG, "coroutine launched")
            crimes += loadCrimes()
        }
        Log.d(TAG, "Loading crimes finished")
    }


    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun loadCrimes(): MutableList<Crime> {
        // Chapter 11 3b solution: localeDate acquires the current date
        val localDate: LocalDate = LocalDate.now()
        // formatter sets the pattern for the date output in "Sunday, November 5, 2023" format
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")

        // date is assigned the value of a formatted localDate to the above pattern
        val date: String = localDate.format(formatter)
        val result = mutableListOf<Crime>()
        delay(5000)
        for (i in 1 until 101) {
            val crime = Crime(
                id = UUID.randomUUID(),
                title = "Crime #$i",
                date = date,
                isSolved = Random.nextBoolean(), // To randomize the solved image on each crime view
                requiresPolice = Random.nextBoolean() // To randomize the requiresPolice boolean for viewType testing in the RecyclerView
            )
            result += crime
        }
        return result
    }
}

