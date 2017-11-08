package com.toandoan.todo.data.model

import com.toandoan.todo.data.model.source.TaskDataSource
import io.reactivex.Flowable

class TaskRepository(val taskLocalDataSource: TaskDataSource) : TaskDataSource {

  override fun deleteAllTask(): Flowable<Int> {
    return taskLocalDataSource.deleteAllTask()
  }

  override fun getTaskById(id: Int) {
  }

  override fun saveTask(task: Task) {
    taskLocalDataSource.saveTask(task)
  }

  override fun activeTask(task: Task) {
  }

  override fun deleteTask(task: Task) {
  }

  override fun addTask(task: Task): Flowable<Long> {
    return taskLocalDataSource.addTask(task)
  }

  override fun editTask(task: Task) {
  }

  override fun getTasks(): Flowable<MutableList<Task>> {
    return taskLocalDataSource.getTasks()
  }

}