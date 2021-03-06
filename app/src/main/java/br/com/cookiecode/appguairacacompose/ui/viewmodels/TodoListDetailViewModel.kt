package br.com.cookiecode.appguairacacompose.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.cookiecode.appguairacacompose.data.models.TodoListItem
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListItemRepository
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListDetailViewModel(application: Application) :
    AndroidViewModel(application) {
    private val repository = TodoListRepository(application)
    private val itemRepository = TodoListItemRepository(application)

    fun items(id: Int) = itemRepository.getItemsByTodoList(id)

    fun todoList(id: Int) = repository.show(id)

    fun updateItem(item: TodoListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            itemRepository.update(item)
        }
    }

    fun deleteItem(item: TodoListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            itemRepository.delete(item)
        }
    }
}