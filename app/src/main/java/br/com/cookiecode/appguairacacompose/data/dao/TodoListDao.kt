package br.com.cookiecode.appguairacacompose.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.cookiecode.appguairacacompose.data.models.TodoList

@Dao
interface TodoListDao {

    @Query("SELECT * FROM todo_lists")
    fun all(): LiveData<List<TodoList>>

    @Query("SELECT * FROM todo_lists WHERE id = :id LIMIT 1")
    fun show(id: Int): LiveData<TodoList>

    @Insert
    fun create(data: TodoList)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(data: TodoList)

    @Delete
    fun delete(data: TodoList)
}