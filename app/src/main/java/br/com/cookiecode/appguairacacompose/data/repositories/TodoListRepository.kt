package br.com.cookiecode.appguairacacompose.data.repositories

import android.app.Application
import br.com.cookiecode.appguairacacompose.data.AppDatabase
import br.com.cookiecode.appguairacacompose.data.dao.TodoListDao
import br.com.cookiecode.appguairacacompose.data.models.TodoList

class TodoListRepository(application: Application) :
    BaseRepository<TodoList, Int> {

    private val dao: TodoListDao

    init {
        val db = AppDatabase.getAppDatabase(application)
        dao = db.toDoDao()
    }

    override fun all() = dao.all()

    override fun show(id: Int) = dao.show(id)

    override fun create(data: TodoList) = dao.create(data)

    override fun update(data: TodoList) = dao.update(data)

    override fun delete(data: TodoList) = dao.delete(data)
}