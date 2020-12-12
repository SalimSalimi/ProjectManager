package fr.im.salimi.projectmanager.data.converters

import androidx.room.TypeConverter
import fr.im.salimi.projectmanager.data.helpers.Post
import java.sql.Timestamp
import java.util.*

class Converters {

    @TypeConverter
    fun fromPost(value: Post) = value.name

    @TypeConverter
    fun toPost(value: String) = enumValueOf<Post>(value)

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}