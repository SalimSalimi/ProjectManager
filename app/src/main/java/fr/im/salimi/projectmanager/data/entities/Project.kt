package fr.im.salimi.projectmanager.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "projects")
data class Project(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "project_id")
        val projectId: Long = 0,
        @ColumnInfo(name = "project_name")
        var name: String = "",
        var customer: String = "",
        var description: String = "",
        @ColumnInfo(name = "starting_date")
        var startingDate: Date = Date(),
        var deadline: Date = Date()
) : BaseEntity(projectId)
