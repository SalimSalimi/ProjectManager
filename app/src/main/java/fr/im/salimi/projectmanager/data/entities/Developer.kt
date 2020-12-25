package fr.im.salimi.projectmanager.data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.im.salimi.projectmanager.data.helpers.Address
import fr.im.salimi.projectmanager.data.helpers.Post

@Entity(tableName = "developers")
data class Developer(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "developer_id")
        val developerId: Long = 0,
        @ColumnInfo(name = "first_name")
        var firstName: String = "",
        @ColumnInfo(name = "last_name")
        var lastName: String = "",
        @Embedded
        var address: Address = Address(),
        var email: String = "",
        @ColumnInfo(name = "phone_number")
        var phoneNumber: Long = 0,
        var post: Post = Post.NONE
) : BaseEntity(developerId)