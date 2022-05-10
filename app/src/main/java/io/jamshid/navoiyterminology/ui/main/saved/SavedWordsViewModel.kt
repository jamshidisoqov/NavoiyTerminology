package io.jamshid.navoiyterminology.ui.main.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.jamshid.navoiyterminology.data.local.entities.Term
import io.jamshid.navoiyterminology.domain.models.Response
import io.jamshid.navoiyterminology.domain.use_cases.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SavedWordsViewModel(private val useCases: UseCases) : ViewModel() {


    private var _savedTerm: MutableStateFlow<Response<List<Term>>> =
        MutableStateFlow(Response.Loading())
    val savedTerm: StateFlow<Response<List<Term>>> get() = _savedTerm

    fun getSavedTerms() {
        viewModelScope.launch {
            useCases.getTermByStatus.invoke().collectLatest {
                _savedTerm.value = it
            }
        }
    }

    fun updateTerm(term: Term){
        viewModelScope.launch {
            useCases.updateTerm.invoke(term)
        }
    }

}