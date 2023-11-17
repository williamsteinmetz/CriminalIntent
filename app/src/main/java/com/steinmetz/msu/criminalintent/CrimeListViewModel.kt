package com.steinmetz.msu.criminalintent


import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID


private const val TAG = "CrimeListViewModel"

class CrimeListViewModel : ViewModel() {

    private val crimeRepository = CrimeRepository.get()

    private val _crimes: MutableStateFlow<List<Crime>> = MutableStateFlow(emptyList())
    val crimes: StateFlow<List<Crime>>
        get() = _crimes.asStateFlow()

    init {
        Log.d(TAG, "init starting")
        viewModelScope.launch {
            crimeRepository.getCrimes().collect { crimeList ->
                _crimes.value = crimeList
                println("Run inside .collect on Viewmodel -- ${crimeList.size} crimes collected")
                println("value of _crimes -- ${_crimes.value}")
                println("value of crimes -- ${crimes.value}")
                println("End of Viewmodel")
            }
        }

    }
}

// Iterates for 100 instances of the crime object. The view of each instance will
// be displayed differently depending on the booleans isSolved and requiresPolice
// Chapter 11 3b solution: localeDate acquires the current date
//val localDate: LocalDate = LocalDate.now()
//// formatter sets the pattern for the date output in "Sunday, November 5, 2023" format
//val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")
//// date is assigned the value of a formatted localDate to the above pattern
//val date: String = localDate.format(formatter)
//        val result = mutableListOf<Crime>()
//        delay(5000)
//        for (i in 1 until 101) {
//            val crime = Crime(
//                id = UUID.randomUUID(),
//                title = "Crime #$i",
//                date = date,
//                isSolved = Random.nextBoolean(), // To randomize the solved image on each crime view
//                requiresPolice = Random.nextBoolean() // To randomize the requiresPolice boolean for viewType testing in the RecyclerView
//            )
//            result += crime
//        }
//        return result


