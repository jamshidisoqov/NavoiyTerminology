package io.jamshid.navoiyterminology.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Term(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var description: String,
    var status: Boolean = false
)
