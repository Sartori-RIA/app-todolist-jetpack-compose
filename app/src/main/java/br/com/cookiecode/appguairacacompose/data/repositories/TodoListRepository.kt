package br.com.cookiecode.appguairacacompose.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import br.com.cookiecode.appguairacacompose.data.AppDatabase
import br.com.cookiecode.appguairacacompose.data.dao.TodoListDao
import br.com.cookiecode.appguairacacompose.data.models.TodoList

class TodoListRepository(application: Application) {

    private val dao: TodoListDao
    var todoLists: LiveData<List<TodoList>>

    init {
        val db = AppDatabase.getAppDatabase(application)
        dao = db.toDoDao()
        todoLists = dao.all()
    }

    fun create(data: TodoList) {
        dao.create(data)
    }

    fun update(data: TodoList) {
        dao.update(data)
    }

    fun delete(data: TodoList) {
        dao.delete(data)
    }
}