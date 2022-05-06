package io.jamshid.navoiyterminology.domain.use_cases

import io.jamshid.navoiyterminology.data.local.dao.TermDao
import io.jamshid.navoiyterminology.data.local.entities.Term
import io.jamshid.navoiyterminology.domain.models.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTermByStatus(private val dao: TermDao) {
    operator fun invoke(): Flow<Response<List<Term>>> = flow {
        try {
            emit(Response.Loading())
            val data = dao.getTermsByStatus()
            emit(Response.Success(data))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage))
        }
    }
}