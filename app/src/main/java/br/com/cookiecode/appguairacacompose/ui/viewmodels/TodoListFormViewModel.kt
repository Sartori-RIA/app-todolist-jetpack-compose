package br.com.cookiecode.appguairacacompose.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoListRepository(application)
    private val _name = MutableLiveData("")

    val name: LiveData<String> = _name

    fun onNameChanged(newName: String) {
        _name.value = newName
    }

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.create(TodoList(name = _name.value.toString()))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}