package fr.im.salimi.projectmanager.data.entities.subsets

import androidx.room.ColumnInfo
import fr.im.salimi.projectmanager.data.helpers.State

data class NumberByState(
        @ColumnInfo(name = "state")
        val state: State,
        @ColumnInfo(name = "number")
        val number: Int
)
