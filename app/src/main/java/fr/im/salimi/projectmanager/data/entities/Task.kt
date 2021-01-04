package fr.im.salimi.projectmanager.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks",
        foreignKeys = [
                ForeignKey(
                        entity = Function::class,
                        parentColumns = ["function_id"],
                        childColumns = ["function_id_fk"]
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
        @ColumnInfo(name = "function_id_fk")
        var functionId: Long = 0L
) : BaseEntity(taskId) {
}