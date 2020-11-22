package fr.im.salimi.projectmanager.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.im.salimi.projectmanager.data.daos.DeveloperDao

@
abstract class Database: RoomDatabase() {

    abstract fun developerDao(): DeveloperDao

    companion object {
        @Volatile
        private var INSTANCE: Database? = null

        fun getInstance(context: Context): Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "project_manager_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}