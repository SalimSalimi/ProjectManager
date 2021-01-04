package fr.im.salimi.projectmanager.data.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.entities.Task
import fr.im.salimi.projectmanager.data.entities.TaskAssignments

data class TaskWithDevelopers(
        @Embedded
        val task: Task,
        @Relation(
                parentColumn = "task_id",
                entityColumn = "developer_id",
                associateBy = Junction(value = TaskAssignments::class)
        )
        val developers: List<Developer>)