package com.toandoan.todo.screen.main

import android.util.Log
import com.toandoan.todo.data.model.Task
import com.toandoan.todo.data.model.source.TaskDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MainPresenter : MainContract.Presenter {

  var taskRepository: TaskDataSource? = null
  var view: MainContract.View? = null
  var compositeDisposable: CompositeDisposable? = null

  constructor(taskRepository: TaskDataSource?, view: MainContract.View?) {
    this.taskRepository = taskRepository
    this.view = view
    this.compositeDisposable = CompositeDisposable()
  }


  override fun onStart() {
  }

  override fun onStop() {
    compositeDisposable!!.clear()
  }

  override fun getTask() {
    Log.d("MainPresenter", "getTask")

    var disposible: Disposable = taskRepository!!.getTasks()
        .doOnSubscribe({
          view?.showProgress()
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { tasks ->
          view?.onGetTaskSuccess(tasks)
        }, Consumer { throwable ->
          view?.onGetTaskFailure(throwable.message!!)
          view?.hideProgress()
        }, Action {
          view?.hideProgress()
        })
    compositeDisposable!!.add(disposible)
  }

  override fun addTask(task: Task) {
    Log.d("MainPresenter", "Add task" +task.title)

    var disposible = taskRepository!!.addTask(task)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { rowId ->
          if (rowId > 0) {
            view?.onAddTaskSuccess(task)
          }
        }, Consumer { throwable ->
          view?.onGetTaskFailure(throwable.message!!)
          view?.hideProgress()
        }, Action {
          view?.hideProgress()
        })
    compositeDisposable!!.add(disposible)
  }

  override fun deleteAllRecord() {
    var disposible = taskRepository!!.deleteAllTask()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { rowId ->
          if (rowId > 0) {
            view?.onDeleAllTaskSuccess()
          }
        }, Consumer { throwable ->
          view?.onGetTaskFailure(throwable.message!!)
          view?.hideProgress()
        }, Action {
          view?.hideProgress()
        })
    compositeDisposable!!.add(disposible)
  }


}