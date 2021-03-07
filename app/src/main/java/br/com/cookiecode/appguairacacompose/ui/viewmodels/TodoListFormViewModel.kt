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

    // TODO when using the same component and ViewModel is throwing a exception
    fun onIdChange(id: Int) {
        if (id > 0)
            _name.value = repository.show(id).value?.name
    }

    fun edit(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(TodoList(id = id, name = _name.value.toString()))
        }
    }

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.create(TodoList(name = _name.value.toString()))
        }
    }
}