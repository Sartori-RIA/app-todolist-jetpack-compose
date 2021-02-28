package br.com.cookiecode.appguairacacompose.data.repositories

import android.app.Application
import br.com.cookiecode.appguairacacompose.data.AppDatabase
import br.com.cookiecode.appguairacacompose.data.dao.TodoListItemDao
import br.com.cookiecode.appguairacacompose.data.models.TodoListItem

class TodoListItemRepository(application: Application) :
    BaseRepository<TodoListItem, Int> {
    private val dao: TodoListItemDao

    init {
        val db = AppDatabase.getAppDatabase(application)
        dao = db.todoListItemDao()
    }

    override fun all() = dao.all()

    fun getItemsByTodoList(id: Int) = dao.getTodoListItems(id)

    override fun show(id: Int) = dao.show(id)

    override fun create(data: TodoListItem) = dao.create(data)

    override fun update(data: TodoListItem) = dao.update(data)

    override fun delete(data: TodoListItem) = dao.delete(data)
}