package br.com.cookiecode.appguairacacompose.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.cookiecode.appguairacacompose.data.converters.DateTypeConverter
import br.com.cookiecode.appguairacacompose.data.dao.ToDoDao
import br.com.cookiecode.appguairacacompose.data.models.ToDo
import br.com.cookiecode.appguairacacompose.data.models.ToDoItem

@Database(entities = [ToDo::class, ToDoItem::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null)
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "todo_list_app_db"
                    ).build()
                }
            return INSTANCE
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}