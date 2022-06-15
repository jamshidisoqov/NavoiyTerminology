package io.jamshid.navoiyterminology.domain.use_cases

import io.jamshid.navoiyterminology.data.local.dao.TermDao
import io.jamshid.navoiyterminology.data.local.entities.Term
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchTerm(private var dao: TermDao) {

    suspend operator fun invoke(query:String): Flow<List<Term>> = flow {
        emit(dao.searchByChapter(query))
    }
}
