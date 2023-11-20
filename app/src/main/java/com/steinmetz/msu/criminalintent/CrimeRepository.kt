package com.steinmetz.msu.criminalintent

import android.content.Context
import androidx.room.Room
import com.steinmetz.msu.criminalintent.database.CrimeDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.util.UUID

private const val DATABASE_NAME = "crime-database.db"

class CrimeRepository @OptIn(DelicateCoroutinesApi::class)
private constructor(
    context: Context, private val coroutineScope: CoroutineScope = GlobalScope
) {

    private val database: CrimeDatabase = Room.databaseBuilder(
        context.applicationContext, CrimeDatabase::class.java, DATABASE_NAME
    ).createFromAsset(DATABASE_NAME).fallbackToDestructiveMigration().build()

    fun getCrimes(): Flow<List<Crime>> = database.crimeDao().getCrimes()
    suspend fun getCrime(id: UUID): Crime = database.crimeDao().getCrime(id)

    fun updateCrime(crime: Crime) {
        coroutineScope.launch {
            database.crimeDao().updateCrime(crime)
        }

    }

    companion object {
        private var INSTANCE: CrimeRepository? = null


        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}