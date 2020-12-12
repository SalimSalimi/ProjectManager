package fr.im.salimi.projectmanager.data.entities.relations

import androidx.room.Entity

/**
 * Helper class for Many-To-Many relation
 * Between Team
 * and Developer
 *
 * */
@Entity(primaryKeys = ["team_id", "developer_id"])
data class TeamDeveloperCrossRef(
    val teamId: Long,
    val developerId: Long
)
