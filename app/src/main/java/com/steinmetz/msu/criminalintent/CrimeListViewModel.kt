package com.steinmetz.msu.criminalintent

import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.UUID
import kotlin.random.Random

class CrimeListViewModel : ViewModel() {

    val crimes = mutableListOf<Crime>()


    init {
        for (i in 1 until 101) {
            val crime = Crime(
                id = UUID.randomUUID(),
                title ="Crime #$i",
                date = Date(),
                isSolved = i % 2 == 0,
                requiresPolice = Random.nextBoolean()
            )
            crimes += crime
        }
    }
}