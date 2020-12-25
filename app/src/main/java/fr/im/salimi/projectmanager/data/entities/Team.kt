package fr.im.salimi.projectmanager.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class Team(
        @PrimaryKey
        @ColumnInfo(name = "team_id")
        val teamId: Long = 0,
        var name: String = "",
        @ColumnInfo(name = "leader_id")
        var leaderId: Long = 0,
        var description: String = ""
) : BaseEntity(teamId)