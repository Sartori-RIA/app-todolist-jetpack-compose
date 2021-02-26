package br.com.cookiecode.appguairacacompose.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.data.models.TodoListWithItems

@Dao
interface TodoListDao {

    @Query("SELECT * FROM todo_lists")
    fun all(): LiveData<List<TodoList>>

    @Query("SELECT * FROM todo_lists WHERE id = :id")
    fun show(id: Int): LiveData<TodoListWithItems>

    @Insert
    fun create(data: TodoList)

    @Update
    fun update(data: TodoList)

    @Delete
    fun delete(data: TodoList)
}