package com.steinmetz.msu.criminalintent

import java.util.UUID

// Crime constructor
   data class Crime(
       val id: UUID,
       val title: String,
       val date: String,
       val isSolved: Boolean,
       val requiresPolice: Boolean
    )





