package fr.im.salimi.projectmanager.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import fr.im.salimi.projectmanager.data.entities.Feature
import fr.im.salimi.projectmanager.data.entities.Task

data class FeatureWithTasks(
        @Embedded
        val feature: Feature,
        @Relation(
                parentColumn = "feature_id",
                entityColumn = "feature_id_fk"
        )
        val tasks: List<Task>
)