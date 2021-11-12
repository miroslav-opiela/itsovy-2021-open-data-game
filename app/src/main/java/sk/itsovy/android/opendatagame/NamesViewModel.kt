package sk.itsovy.android.opendatagame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NamesViewModel(private val repository: NamesRepository): ViewModel() {

val allRecords = repository.allRecords.asLiveData()

fun loadRecords(){
    viewModelScope.launch{repository.loadRecords()}
}

    class NamesViewModelFactory(private val repository: NamesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NamesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NamesViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}