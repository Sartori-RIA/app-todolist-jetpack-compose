package br.com.cookiecode.appguairacacompose.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.cookiecode.appguairacacompose.data.models.TodoListItem

@Dao
interface TodoListItemDao {

    @Query("SELECT * from TODO_LIST_ITEMS WHERE todo_list_id = :todoListId")
    fun getTodoListItems(todoListId: Int): LiveData<List<TodoListItem>>

    @Insert
    fun create(item: TodoListItem)

    @Update
    fun update(item: TodoListItem)

    @Delete
    fun delete(item: TodoListItem)
}