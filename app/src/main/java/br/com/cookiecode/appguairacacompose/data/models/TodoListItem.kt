package br.com.cookiecode.appguairacacompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list_items")
data class TodoListItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "done") var done: Boolean = false,
    @ColumnInfo(name = "todo_list_id") val toDoId: Int = 0
)