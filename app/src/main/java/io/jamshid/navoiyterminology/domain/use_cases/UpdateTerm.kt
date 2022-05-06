package io.jamshid.navoiyterminology.domain.use_cases

import io.jamshid.navoiyterminology.data.local.dao.TermDao
import io.jamshid.navoiyterminology.data.local.entities.Term

class UpdateTerm(private val dao: TermDao) {
    suspend operator fun invoke(term: Term) = dao.updateTerm(term)
}