package fr.im.salimi.projectmanager.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "features",
        foreignKeys = [
            ForeignKey(
                    entity = Module::class,
                    childColumns = ["module_id_fk"],
                    parentColumns = ["module_id"],
                    onDelete = ForeignKey.CASCADE
            ),
            ForeignKey(
                    entity = Project::class,
                    childColumns = ["project_id_fk"],
                    parentColumns = ["project_id"],
                    onDelete = ForeignKey.CASCADE
            )
        ]
)
data class Feature(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "feature_id")
        val featureId: Long = 0L,
        var name: String = "",
        var description: String = "",
        @ColumnInfo(name = "starting_date")
        var startingDate: Date = Date(),
        @ColumnInfo(name = "finishing_date")
        var endingDate: Date = Date(),
        @ColumnInfo(name = "module_id_fk")
        var moduleId: Long = 0L,
        @ColumnInfo(name = "project_id_fk")
        var projectId: Long = 0L
) : BaseEntity(
        featureId)