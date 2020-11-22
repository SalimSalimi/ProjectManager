package fr.im.salimi.projectmanager.data.entities

import fr.im.salimi.projectmanager.data.helpers.Address
import fr.im.salimi.projectmanager.data.helpers.Post

data class Developer (
    val id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var address: Address = Address(),
    var email: String = "",
    var phoneNumber: String = "",
    var post: Post = Post.NONE
)