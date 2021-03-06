package br.com.cookiecode.appguairacacompose.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.cookiecode.appguairacacompose.data.models.TodoListItem

@Dao
interface TodoListItemDao {

    @Query("SELECT * FROM todo_list_items ORDER BY id DESC")
    fun all(): LiveData<List<TodoListItem>>

    @Query("SELECT * from todo_list_items WHERE todo_list_id = :todoListId ORDER BY id DESC")
    fun getTodoListItems(todoListId: Int): LiveData<List<TodoListItem>>

    @Query("SELECT * FROM todo_list_items WHERE id = :id LIMIT 1")
    fun show(id: Int): LiveData<TodoListItem>

    @Insert
    fun create(item: TodoListItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(item: TodoListItem)

    @Delete
    fun delete(item: TodoListItem)
}