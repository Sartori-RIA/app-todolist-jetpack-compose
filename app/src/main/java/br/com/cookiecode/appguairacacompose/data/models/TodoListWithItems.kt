package br.com.cookiecode.appguairacacompose.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class TodoListWithItems(
    @Embedded
    val todo: TodoList,
    @Relation(
        parentColumn = "id",
        entityColumn = "todo_list_id",
    )
    var items: List<TodoListItem>
)