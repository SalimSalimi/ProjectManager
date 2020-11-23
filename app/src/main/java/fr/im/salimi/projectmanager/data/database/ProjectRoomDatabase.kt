package fr.im.salimi.projectmanager.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.im.salimi.projectmanager.data.daos.DeveloperDao
import fr.im.salimi.projectmanager.data.entities.Developer

@Database(entities = [Developer::class], version = 1, exportSchema = false)
abstract class ProjectRoomDatabase: RoomDatabase() {

    abstract fun developerDao(): DeveloperDao

    companion object {
        @Volatile
        private var INSTANCE: ProjectRoomDatabase? = null

        fun getInstance(context: Context): ProjectRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectRoomDatabase::class.java,
                    "project_manager_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}