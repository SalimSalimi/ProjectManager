package fr.im.salimi.projectmanager.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import fr.im.salimi.projectmanager.data.entities.Function
import fr.im.salimi.projectmanager.data.entities.Module

data class ModuleWithFunctions(
        @Embedded
        val module: Module,
        @Relation(
                parentColumn = "module_id",
                entityColumn = "module_id_fk"
        )
        val functions: List<Function>
)