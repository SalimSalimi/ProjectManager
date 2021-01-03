package fr.im.salimi.projectmanager.data.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.entities.TaskAssignments

data class DeveloperWithTasks(
        @Embedded
        val developer: Developer,
        @Relation(
                parentColumn = "developer_id",
                entityColumn = "task_id",
                associateBy = Junction(value = TaskAssignments::class)
        )
        val tasks: List<Task>
) {
}