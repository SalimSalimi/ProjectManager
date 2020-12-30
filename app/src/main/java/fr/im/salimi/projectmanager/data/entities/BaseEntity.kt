package fr.im.salimi.projectmanager.data.entities

import androidx.room.Ignore

open class BaseEntity(
        @Ignore
        open var id: Long)