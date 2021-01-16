package fr.im.salimi.projectmanager.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import fr.im.salimi.projectmanager.data.helpers.State
import java.util.*

@Entity(tableName = "tasks",
        foreignKeys = [
                ForeignKey(
                        entity = Feature::class,
                        parentColumns = ["feature_id"],
                        childColumns = ["feature_id_fk"]
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
        var state: State = State.IN_PROGRESS
) : BaseEntity(taskId) {
}