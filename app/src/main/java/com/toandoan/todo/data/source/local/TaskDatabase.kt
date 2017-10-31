package com.toandoan.todo.data.model.local

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.content.Context
import com.toandoan.todo.screen.Constant.DATABASE_NAME
import com.toandoan.todo.screen.Constant.DATABASE_VERSION
import com.toandoan.todo.data.model.Task
import com.toandoan.todo.data.source.local.TaskDAO

@Database(entities = arrayOf(Task::class), version = DATABASE_VERSION, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

  abstract fun taskDAO(): TaskDAO

  companion object {
    private var taskDatabase: TaskDatabase? = null

    fun getInstance(context: Context): TaskDatabase? {
      if (taskDatabase == null) {
        taskDatabase = Room.databaseBuilder(context, TaskDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
      }
      return taskDatabase
    }
  }

}