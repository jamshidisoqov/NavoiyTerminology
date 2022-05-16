package io.jamshid.navoiyterminology.data.local.dao

import androidx.room.*
import io.jamshid.navoiyterminology.data.local.entities.Term
import kotlinx.coroutines.flow.Flow

@Dao
interface TermDao {

    @Insert(entity = Term::class)
    suspend fun insertTerm(term: Term)

    @Update(entity = Term::class)
    suspend fun updateTerm(term: Term)

    @Query("SELECT*FROM term ORDER BY term.name")
    suspend fun getAllTerms(): List<Term>

    @Query("SELECT*FROM term WHERE status=1")
    suspend fun getTermsByStatus(): List<Term>

    @Transaction
    @Query("SELECT*FROM term WHERE name like :query||'%' ORDER BY term.name")
    suspend fun searchByChapter(query: String): List<Term>

}