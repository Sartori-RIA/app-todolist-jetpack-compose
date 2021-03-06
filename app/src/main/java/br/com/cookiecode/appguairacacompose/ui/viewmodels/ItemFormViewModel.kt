package br.com.cookiecode.appguairacacompose.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.cookiecode.appguairacacompose.data.models.TodoListItem
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ItemFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoListItemRepository(application)
    private val _name = MutableLiveData("")

    val name: LiveData<String> = _name
    var todoListId: Int = 0

    fun onNameChanged(newName: String) {
        _name.value = newName
    }

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.create(
                    TodoListItem(
                        name = _name.value.toString(),
                        toDoId = todoListId,
                        done = false
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}