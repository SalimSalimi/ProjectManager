package fr.im.salimi.projectmanager.data.entities

import androidx.room.*
import fr.im.salimi.projectmanager.data.helpers.State
import java.util.*

@Entity(tableName = "tasks",
        foreignKeys = [
            ForeignKey(
                    entity = Feature::class,
                    parentColumns = ["feature_id"],
                    childColumns = ["feature_id_fk"]
            ),
            ForeignKey(
                    entity = Project::class,
                    parentColumns = ["project_id"],
                    childColumns = ["project_id_fk"]
            )

        ],
        indices = [
            Index(
                    value = ["project_id_fk"],
                    unique = true
            )
        ])
data class Task(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "task_id")
        val taskId: Long = 0L,
        var name: String = "",
        var description: String = "",
        @ColumnInfo(name = "starting_date")
        var startingDate: Date = Date(),
        @ColumnInfo(name = "finishing_date")
        var endingDate: Date = Date(),
        @ColumnInfo(name = "feature_id_fk")
        var featureId: Long = 0L,
        @ColumnInfo(name = "project_id_fk")
        var projectId: Long = 0L,
        var state: State = State.IN_PROGRESS
) : BaseEntity(taskId) {
}