package com.toandoan.todo.data.model.source

import com.toandoan.todo.data.model.Task
import io.reactivex.Flowable

interface TaskDataSource {
  fun getTaskById(id: Int)

  fun saveTask(task: Task)

  fun activeTask(task: Task)

  fun deleteTask(task: Task)

  fun addTask(task: Task): Flowable<Long>

  fun editTask(task: Task)

  fun getTasks(): Flowable<MutableList<Task>>

  fun deleteAllTask(): Flowable<Int>
}