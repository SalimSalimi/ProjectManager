package fr.im.salimi.projectmanager.data.entities.subsets

import androidx.room.ColumnInfo
import fr.im.salimi.projectmanager.data.helpers.Post

data class NumberByPost (
    @ColumnInfo(name = "post")
    val post: Post,
    @ColumnInfo(name = "number")
    val number: Int
)