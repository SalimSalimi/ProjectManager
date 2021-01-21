package fr.im.salimi.projectmanager.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.im.salimi.projectmanager.data.converters.Converters
import fr.im.salimi.projectmanager.data.daos.*
import fr.im.salimi.projectmanager.data.entities.*

@Database(entities = [Developer::class, Project::class, Module::class, Feature::class, Task::class, TaskAssignments::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProjectRoomDatabase: RoomDatabase() {

    abstract fun developerDao(): DeveloperDao
    abstract fun projectDao(): ProjectDao
    abstract fun moduleDao(): ModuleDao
    abstract fun featureDao(): FeatureDao
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: ProjectRoomDatabase? = null

        fun createInstance(context: Context): ProjectRoomDatabase {
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

        fun getInstance(): ProjectRoomDatabase {
            return INSTANCE!!
        }
    }
}