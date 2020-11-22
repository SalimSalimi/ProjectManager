package fr.im.salimi.projectmanager.data.helpers

enum class Post(val state: String) {
    NONE("None"),
    WEB_DEVELOPER("Web Developer"),
    MOBILE_DEVELOPER("Mobile Developer"),
    UI_UX_DESIGNER("UI/UX Designer");

    override fun toString(): String {
        return state
    }
}