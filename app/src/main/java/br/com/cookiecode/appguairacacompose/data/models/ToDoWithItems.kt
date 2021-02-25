package br.com.cookiecode.appguairacacompose.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class ToDoWithItems(
    @Embedded
    val todo: ToDo,
    @Relation(
        parentColumn = "id",
        entityColumn = "to_do_id",
    )
    var items: List<ToDoItem>
)