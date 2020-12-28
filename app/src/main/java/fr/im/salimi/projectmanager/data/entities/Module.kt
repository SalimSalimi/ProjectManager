package fr.im.salimi.projectmanager.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "modules")
data class Module (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "module_id")
        val moduleId: Long = 0L,
        var name: String = "",
        var description: String = "",
        @ColumnInfo(name = "starting_date")
        var startingDate: Date = Date(),
        @ColumnInfo(name = "finishing_date")
        var endingDate: Date = Date(),
        @ColumnInfo(name = "project_id")
        var projectId: Long = 0L
        ): BaseEntity(
        moduleId)