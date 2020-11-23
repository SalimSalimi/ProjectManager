package fr.im.salimi.projectmanager.data.converters

import androidx.room.TypeConverter
import fr.im.salimi.projectmanager.data.helpers.Post

class Converters {

    @TypeConverter
    fun fromPost(value: Post) = value.name

    @TypeConverter
    fun toPost(value: String) = enumValueOf<Post>(value)
}