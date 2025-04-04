package com.example.sweatout.exercise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sweatout.core.data.local.Converters
import com.example.sweatout.exercise.domain.Exercise

@Database(entities = [Exercise::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}