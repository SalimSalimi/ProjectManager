package fr.im.salimi.projectmanager.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.im.salimi.projectmanager.data.converters.Converters
import fr.im.salimi.projectmanager.data.daos.*
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.entities.Team

@Database(entities = [Developer::class, Team::class, Project::class, Module::class, Function::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProjectRoomDatabase: RoomDatabase() {

    abstract fun developerDao(): DeveloperDao
    abstract fun teamDao(): TeamDao
    abstract fun projectDao(): ProjectDao
    abstract fun moduleDao(): ModuleDao
    abstract fun functionDao(): FunctionDao

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