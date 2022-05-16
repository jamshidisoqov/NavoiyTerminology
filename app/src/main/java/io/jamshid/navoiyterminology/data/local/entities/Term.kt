package io.jamshid.navoiyterminology.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "term")
data class Term(
    @PrimaryKey(autoGenerate = false)
    var id: Int?=null,
    var name: String?=null,
    var description: String?=null,
    var status: Boolean?=null
)
