package fr.im.salimi.projectmanager.data.entities.subsets

import androidx.room.ColumnInfo
import androidx.room.Embedded
import fr.im.salimi.projectmanager.data.entities.BaseEntity
import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.helpers.State

data class ModuleState(
        @Embedded val module: Module?,
        @ColumnInfo(name = "state")
        val state: State?
) : BaseEntity(module!!.id)