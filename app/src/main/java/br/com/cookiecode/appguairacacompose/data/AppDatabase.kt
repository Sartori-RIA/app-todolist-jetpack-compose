package br.com.cookiecode.appguairacacompose.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.cookiecode.appguairacacompose.data.converters.DateTypeConverter
import br.com.cookiecode.appguairacacompose.data.dao.TodoListDao
import br.com.cookiecode.appguairacacompose.data.dao.TodoListItemDao
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.data.models.TodoListItem

@Database(entities = [TodoList::class, TodoListItem::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toDoDao(): TodoListDao

    abstract fun todoListItemDao(): TodoListItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "todo_list_app_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}