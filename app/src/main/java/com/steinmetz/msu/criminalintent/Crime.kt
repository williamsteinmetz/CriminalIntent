package com.steinmetz.msu.criminalintent

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

// Crime constructor
@Entity
   data class Crime(
    @PrimaryKey val id: UUID,
    val title: String,
    val date: String,
    val isSolved: Boolean,
    val requiresPolice: Int
    )





