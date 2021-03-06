package br.com.cookiecode.appguairacacompose.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoListRepository(application)

    fun items() = repository.all()

    fun delete(data: TodoList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(data)
        }
    }
}