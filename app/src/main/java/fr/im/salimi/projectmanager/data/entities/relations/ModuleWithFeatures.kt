package fr.im.salimi.projectmanager.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import fr.im.salimi.projectmanager.data.entities.Feature
import fr.im.salimi.projectmanager.data.entities.Module

data class ModuleWithFeatures(
        @Embedded
        val module: Module,
        @Relation(
                parentColumn = "module_id",
                entityColumn = "module_id_fk"
        )
        val features: List<Feature>
)