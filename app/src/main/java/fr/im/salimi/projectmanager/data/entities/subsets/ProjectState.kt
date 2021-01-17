package fr.im.salimi.projectmanager.data.entities.subsets

import androidx.room.ColumnInfo
import androidx.room.Embedded
import fr.im.salimi.projectmanager.data.entities.BaseEntity
import fr.im.salimi.projectmanager.data.entities.Project
import fr.im.salimi.projectmanager.data.helpers.State

data class ProjectState (
        @Embedded val project: Project,
        @ColumnInfo(name = "state")
        val state: State
) : BaseEntity(project.id)