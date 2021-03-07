package br.com.cookiecode.appguairacacompose.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.cookiecode.appguairacacompose.data.models.TodoList

@Dao
interface TodoListDao {

    @Query("SELECT * FROM todo_lists ORDER BY id DESC")
    fun all(): LiveData<List<TodoList>>

    @Query("SELECT todo_lists.*, ROUND(CAST(((SELECT COUNT(*) FROM todo_list_items WHERE todo_list_id = :id AND done = 1) * 100.0 / (SELECT COUNT(*) FROM todo_list_items WHERE todo_list_id = :id)) AS FLOAT), 2) AS percentage FROM todo_lists")
    fun show(id: Int): LiveData<TodoList>

    @Insert
    fun create(data: TodoList)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(data: TodoList)

    @Delete
    fun delete(data: TodoList)
}