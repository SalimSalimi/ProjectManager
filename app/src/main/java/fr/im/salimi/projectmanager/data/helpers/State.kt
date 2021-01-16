package fr.im.salimi.projectmanager.data.helpers

enum class State(val value: String) {
    IN_PROGRESS ("In Progress"),
    DONE("Done"),
    OVERDUE("Overdue");

    override fun toString(): String {
        return value
    }
}