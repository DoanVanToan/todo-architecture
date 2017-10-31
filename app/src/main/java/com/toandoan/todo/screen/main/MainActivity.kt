package com.toandoan.todo.screen.main

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.toandoan.todo.R
import com.toandoan.todo.data.model.Task
import com.toandoan.todo.data.model.TaskRepository
import com.toandoan.todo.data.model.local.TaskDatabase
import com.toandoan.todo.data.model.local.TaskLocalDataSource
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), MainContract.View, View.OnClickListener {

  lateinit var mainPresenter: MainContract.Presenter
  lateinit var adapter: MainAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    var taskRepository = TaskRepository(
        TaskLocalDataSource(TaskDatabase.getInstance(this)!!.taskDAO()))
    mainPresenter = MainPresenter(taskRepository, this)

    adapter = MainAdapter(arrayListOf())
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter
    floatingActionButton.setOnClickListener(this)

    mainPresenter.getTask()
  }

  override fun presenter(presenter: MainContract.Presenter) {
    mainPresenter = presenter
  }

  override fun onStart() {
    super.onStart()
    mainPresenter.onStart()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item!!.itemId) {
      android.R.id.home -> onBackPressed()
      R.id.menu_delete -> {
        AlertDialog.Builder(this)
            .setTitle("Delete")
            .setMessage("Do you want to delete all record?")
            .setPositiveButton(android.R.string.yes,
                DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
                  mainPresenter.deleteAllRecord()
                })
            .setNegativeButton(android.R.string.no, null)
            .create()
            .show()
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onStop() {
    mainPresenter.onStop()
    super.onStop()
  }

  override fun onGetTaskSuccess(tasks: MutableList<Task>) {
    Log.d("MainActivity", "onGetTaskSuccess" + tasks.size.toString())
    adapter.updateData(tasks)
  }

  override fun onAddTaskSuccess(task: Task) {
    Log.d("MainActivity", " onAddTaskSuccess" + task)
    adapter.updateData(task)
    recyclerView.smoothScrollToPosition(0)
  }

  override fun onDeleAllTaskSuccess() {
    adapter.clearData()
  }

  override fun onGetTaskFailure(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }

  override fun showProgress() {
  }

  override fun hideProgress() {
  }

  override fun onClick(v: View?) {
    var task = Task(id = random(0, 1000), title = "Toan Doan" + random(0, 1000),
        description = null, isActive = false)
    mainPresenter.addTask(task)
  }

  private fun random(from: Int, to: Int): Int {
    var randone = Random()
    return randone.nextInt(to - from) + from
  }

}
