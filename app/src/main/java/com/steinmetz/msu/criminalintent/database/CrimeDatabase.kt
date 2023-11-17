package com.steinmetz.msu.criminalintent.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.steinmetz.msu.criminalintent.Crime


@Database(entities = [Crime::class ], version = 1)
abstract class CrimeDatabase : RoomDatabase() {
    abstract fun crimeDao(): CrimeDao
}

