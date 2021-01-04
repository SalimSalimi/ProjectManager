package fr.im.salimi.projectmanager.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.entities.Project

data class ProjectWithModules(
        @Embedded
        val project: Project,
        @Relation(
                parentColumn = "project_id",
                entityColumn = "project_id_fk"
        )
        val modules: List<Module>
)