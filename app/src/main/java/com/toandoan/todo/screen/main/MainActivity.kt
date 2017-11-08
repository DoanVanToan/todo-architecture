package com.toandoan.todo.screen.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.toandoan.todo.R
import com.toandoan.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  var viewModel: MainViewModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = MainViewModel(this)
    var binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
        R.layout.activity_main)
    setSupportActionBar(binding.toolbar)

    binding.viewModel = viewModel
  }

  override fun onStart() {
    super.onStart()
    viewModel?.onStart()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item!!.itemId) {
      android.R.id.home -> onBackPressed()
      R.id.menu_delete -> {
        viewModel?.onDeleteAllTaskClicked()
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onStop() {
    viewModel?.onStop()
    super.onStop()
  }

}
