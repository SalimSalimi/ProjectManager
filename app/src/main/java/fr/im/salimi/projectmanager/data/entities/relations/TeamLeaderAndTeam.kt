package fr.im.salimi.projectmanager.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import fr.im.salimi.projectmanager.data.entities.Developer
import fr.im.salimi.projectmanager.data.entities.Team

/**
 * One-To-One relation between leaderId of Team
 * With Developer id
 * */

data class TeamLeaderAndTeam (
    @Embedded
    val teamLeader: Developer,
    @Relation(
        parentColumn = "developer_id",
        entityColumn = "leader_id"
    )
    val team: Team )
