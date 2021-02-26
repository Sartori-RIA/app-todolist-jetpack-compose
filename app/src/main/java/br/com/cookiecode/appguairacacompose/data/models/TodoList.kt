package br.com.cookiecode.appguairacacompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_lists")
data class TodoList(
    @ColumnInfo(name = "name") var name: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}