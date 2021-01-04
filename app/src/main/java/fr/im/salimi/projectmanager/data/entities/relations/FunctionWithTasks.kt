package fr.im.salimi.projectmanager.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import fr.im.salimi.projectmanager.data.entities.Function
import fr.im.salimi.projectmanager.data.entities.Task

data class FunctionWithTasks(
        @Embedded
        val function: Function,
        @Relation(
                parentColumn = "function_id",
                entityColumn = "function_id_fk"
        )
        val tasks: List<Task>
)