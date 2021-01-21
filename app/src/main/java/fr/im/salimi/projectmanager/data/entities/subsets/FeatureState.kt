package fr.im.salimi.projectmanager.data.entities.subsets

import androidx.room.ColumnInfo
import androidx.room.Embedded
import fr.im.salimi.projectmanager.data.entities.BaseEntity
import fr.im.salimi.projectmanager.data.entities.Feature
import fr.im.salimi.projectmanager.data.helpers.State

data class FeatureState(
        @Embedded val feature: Feature?,
        @ColumnInfo(name = "state")
        val state: State?
): BaseEntity(feature!!.featureId)