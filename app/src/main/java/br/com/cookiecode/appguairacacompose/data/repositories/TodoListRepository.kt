package br.com.cookiecode.appguairacacompose.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import br.com.cookiecode.appguairacacompose.data.AppDatabase
import br.com.cookiecode.appguairacacompose.data.dao.TodoListDao
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.data.models.TodoListWithItems

class TodoListRepository(application: Application) {

    private val dao: TodoListDao

    init {
        val db = AppDatabase.getAppDatabase(application)
        dao = db.toDoDao()
    }

    fun all() = dao.all()

    fun show(id: Int): LiveData<TodoListWithItems> = dao.show(id)

    fun create(data: TodoList) = dao.create(data)

    fun update(data: TodoList) = dao.update(data)

    fun delete(data: TodoList) = dao.delete(data)
}