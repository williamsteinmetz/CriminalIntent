package com.steinmetz.msu.criminalintent.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.steinmetz.msu.criminalintent.Crime
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface CrimeDao {
    @Query("SELECT * FROM Crime")
    fun getCrimes(): Flow<List<Crime>>

    @Query("SELECT * FROM Crime WHERE id=(:id)")
    suspend fun getCrime(id: UUID): Crime

    @Update
    suspend fun updateCrime(crime: Crime)

}

