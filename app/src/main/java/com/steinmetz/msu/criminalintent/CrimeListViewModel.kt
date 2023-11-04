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


    private val localDate: LocalDate = LocalDate.now()
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")
    @RequiresApi(Build.VERSION_CODES.O)
    val date: String = localDate.format(formatter)

    init {
        for (i in 1 until 101) {
            val crime = Crime(
                id = UUID.randomUUID(),
                title ="Crime #$i",
                date = date,
                isSolved = i % 2 == 0,
                requiresPolice = Random.nextBoolean()
            )

            crimes += crime
        }
    }

}