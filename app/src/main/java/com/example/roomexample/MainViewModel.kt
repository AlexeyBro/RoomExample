package com.example.roomexample

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.roomexample.data.MainDB
import com.example.roomexample.data.NameEntity
import kotlinx.coroutines.launch

class MainViewModel(
    val database: MainDB
): ViewModel() {

    val itemsList = database.dao.getAll()
    val newAddedNameState = mutableStateOf("")
    var newItem: NameEntity? = null

    fun insertItem() {
        viewModelScope.launch {
            val newEntity = newItem?.copy(name = newAddedNameState.value)
                ?: NameEntity(name = newAddedNameState.value)

            database.dao.insert(newEntity)
            newAddedNameState.value = ""
            newItem = null
        }
    }

    fun deleteItem(item: NameEntity) {
        viewModelScope.launch {
            database.dao.delete(item)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val dataBase = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return MainViewModel(dataBase) as T
            }
        }
    }
}