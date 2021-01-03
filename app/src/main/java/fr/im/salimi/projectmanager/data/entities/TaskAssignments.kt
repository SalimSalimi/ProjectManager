package fr.im.salimi.projectmanager.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "tasks_assignments",
        primaryKeys = ["developer_id", "task_id"],
        foreignKeys = [
            ForeignKey(
                    entity = Developer::class,
                    parentColumns = ["developer_id"],
                    childColumns = ["developer_id"]
            ),
            ForeignKey(
                    entity = Task::class,
                    parentColumns = ["task_id"],
                    childColumns = ["task_id"]
            )
        ])
data class TaskAssignments(
        @ColumnInfo(name = "developer_id")
        val developerId: Long,
        @ColumnInfo(name = "task_id")
        val taskId: Long
)