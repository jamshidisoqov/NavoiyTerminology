package io.jamshid.navoiyterminology.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.jamshid.navoiyterminology.data.local.entities.Term

@Dao
interface TermDao {

    @Insert(entity = Term::class)
    suspend fun insertTerm(term: Term)

    @Update(entity = Term::class)
    suspend fun updateTerm(term: Term)

    @Query("SELECT*FROM term")
    suspend fun getAllTerms(): List<Term>

    @Query("SELECT*FROM term WHERE status=1")
    suspend fun getTermsByStatus(): List<Term>

}