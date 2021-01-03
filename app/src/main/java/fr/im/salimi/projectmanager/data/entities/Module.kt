package fr.im.salimi.projectmanager.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "modules",
        foreignKeys = [ForeignKey(
                entity = Project::class,
                childColumns = ["project_id_fk"],
                parentColumns = ["project_id"]
        )])
data class Module (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "module_id")
        override var id: Long = 0,
        var name: String = "",
        var description: String = "",
        @ColumnInfo(name = "starting_date")
        var startingDate: Date = Date(),
        @ColumnInfo(name = "finishing_date")
        var endingDate: Date = Date(),
        @ColumnInfo(name = "project_id_fk")
        var projectId: Long = 0L
        ): BaseEntity(id)