package io.jamshid.navoiyterminology.ui.main.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.jamshid.navoiyterminology.data.local.entities.Term
import io.jamshid.navoiyterminology.domain.models.Response
import io.jamshid.navoiyterminology.domain.use_cases.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val useCases: UseCases,application: Application) : AndroidViewModel(application) {
    private var _allTerms:MutableStateFlow<Response<List<Term>>> = MutableStateFlow(Response.Loading())
    val allTerms:StateFlow<Response<List<Term>>> get() = _allTerms
    fun getTerms(){
        viewModelScope.launch {
            useCases.allTerm.invoke().collect{
                when(it){
                    is Response.Loading->{
                        _allTerms.value = Response.Loading()
                    }
                    is Response.Success->{
                        _allTerms.value = Response.Success(it.data)
                    }
                    is Response.Error->{
                        _allTerms.value = Response.Error(it.message)
                    }
                }
            }
        }
    }
    fun addTerms(term: Term){
        viewModelScope.launch {
            useCases.insertTerm.invoke(term)
        }
    }

    fun updateTerm(term: Term){
        viewModelScope.launch {
            useCases.updateTerm.invoke(term)
        }
    }
    fun search(query:String){
        viewModelScope.launch {
            useCases.searchTerm.invoke(query).collectLatest {
                _allTerms.value =Response.Success(it)
            }
        }
    }
}