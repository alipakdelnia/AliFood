package com.example.alifood.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [Food::class])
abstract class MyDatabase : RoomDatabase() {

    abstract val foodDao: FoodDao

    companion object {

        private val database : MyDatabase? = null
        fun getDatabase (context:Context) : MyDatabase{

            var instance = database
            if (instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "myDatabase.db"
                ).build()

            }
            return instance
        }

    }

}