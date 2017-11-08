package com.toandoan.todo.screen.main

import android.content.Context
import android.content.DialogInterface
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import com.toandoan.todo.BR
import com.toandoan.todo.data.model.Task
import com.toandoan.todo.data.model.TaskRepository
import com.toandoan.todo.data.model.local.TaskDatabase
import com.toandoan.todo.data.model.local.TaskLocalDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by framgia on 11/8/17.
 */
class MainViewModel(val context: Context) : BaseObservable(),
    OnItemCheckListenner, CompoundButton.OnCheckedChangeListener {

  var repository: TaskRepository
  var compositeDisposable: CompositeDisposable

  @Bindable
  var positionScroll: Int = 0
    set(value) {
      field = value
      notifyPropertyChanged(BR.positionScroll)
    }

  @Bindable
  var adapter: MainAdapter? = null
    set(value) {
      field = value
      notifyPropertyChanged(BR.adapter)
    }


  init {
    adapter = MainAdapter(arrayListOf(), this)
    repository = TaskRepository(TaskLocalDataSource(TaskDatabase.getInstance(context)!!.taskDAO()))
    compositeDisposable = CompositeDisposable()
    positionScroll = 0
    getTask()
  }

  fun onStart() {

  }

  fun onStop() {
    compositeDisposable.clear()
  }

  override fun onCheckChange(task: Task) {
    saveTask(task)
  }

  fun getTask() {
    var disposible: Disposable = repository.getTasks()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { tasks ->
          adapter?.updateData(tasks)
        }, Consumer { throwable ->
          Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
        })
    compositeDisposable!!.add(disposible)
  }

  fun addTask(task: Task) {
    Log.d("MainPresenter", "Add task" + task.title)

    var disposible = repository.addTask(task)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { rowId ->
          if (rowId > 0) {
            adapter?.updateData(task)
            positionScroll = 0
          }
        }, Consumer { throwable ->
          Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
        })
    compositeDisposable!!.add(disposible)
  }

  fun deleteAllRecord() {
    var disposible = repository.deleteAllTask()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { rowId ->
          if (rowId > 0) {
            adapter?.clearData()
          }
        }, Consumer { throwable ->
          Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
        })
    compositeDisposable!!.add(disposible)
  }

  fun saveTask(task: Task) {
    repository.saveTask(task)
  }

  fun onDeleteAllTaskClicked() {
    AlertDialog.Builder(context)
        .setTitle("Delete")
        .setMessage("Do you want to delete all record?")
        .setPositiveButton(android.R.string.yes,
            DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
              deleteAllRecord()
            })
        .setNegativeButton(android.R.string.no, null)
        .create()
        .show()
  }

  fun onClickAddTask() {
    var task = Task(id = random(0, 1000), title = "Toan Doan" + random(0, 1000),
        description = null, isActive = false)
    addTask(task)
  }

  private fun random(from: Int, to: Int): Int {
    var randone = Random()
    return randone.nextInt(to - from) + from
  }

  override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {

  }



}