package com.toandoan.todo.screen.main

import com.toandoan.todo.screen.BasePresenter
import com.toandoan.todo.screen.BaseView
import com.toandoan.todo.data.model.Task

interface MainContract {
  interface View : BaseView<Presenter> {
    fun onGetTaskSuccess(tasks: MutableList<Task>)

    fun onGetTaskFailure(message: String)

    fun showProgress()

    fun hideProgress()

    fun onAddTaskSuccess(task: Task)

    fun onDeleAllTaskSuccess()
  }

  interface Presenter : BasePresenter {
    fun getTask()

    fun addTask(task: Task)

    fun deleteAllRecord()

    fun saveTask(task: Task)
  }
}