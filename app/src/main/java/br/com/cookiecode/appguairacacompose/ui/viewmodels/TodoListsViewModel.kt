package br.com.cookiecode.appguairacacompose.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListRepository

class TodoListsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoListRepository(application)

    fun items() = repository.all()
}