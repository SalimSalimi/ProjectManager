package fr.im.salimi.projectmanager.data.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.entities.Team

/**
 * Helper class that get List of developers according to a Team
 *
 * */

data class TeamAndDevelopers(
    @Embedded val team: Team,
    @Relation(
        parentColumn = "team_id",
        entityColumn = "developer_id",
        associateBy = Junction(TeamDeveloperCrossRef::class)
    )
    val developers: List<Developer>
)
