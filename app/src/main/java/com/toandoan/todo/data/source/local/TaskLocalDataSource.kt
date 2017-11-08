package com.toandoan.todo.data.model.local

import com.toandoan.todo.data.model.Task
import com.toandoan.todo.data.model.source.TaskDataSource
import com.toandoan.todo.data.source.local.TaskDAO
import io.reactivex.Flowable
import io.reactivex.Observable
import org.intellij.lang.annotations.Flow

class TaskLocalDataSource(val taskDAO: TaskDAO) : TaskDataSource {
  override fun deleteAllTask(): Flowable<Int> {
    return Flowable.just(taskDAO.deleteAllTask())
  }

  override fun getTasks(): Flowable<MutableList<Task>> {
    return taskDAO.getTasks()
  }

  override fun getTaskById(id: Int) {
  }

  override fun saveTask(task: Task) {
    taskDAO.updateTask(task)
  }

  override fun activeTask(task: Task) {
  }

  override fun deleteTask(task: Task) {
  }

  override fun addTask(task: Task): Flowable<Long> {
    return Flowable.just(taskDAO.addTask(task))
  }

  override fun editTask(task: Task) {

  }

}