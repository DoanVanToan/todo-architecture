package com.toandoan.todo.screen.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.toandoan.todo.R
import com.toandoan.todo.data.model.Task

class MainAdapter(var tasks: MutableList<Task>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

  fun updateData(task: MutableList<Task>) {
    this.tasks.addAll(task)
    notifyDataSetChanged()
  }

  fun updateData(task: Task) {
    this.tasks.add(0, task)
    notifyItemInserted(0)
  }

  fun clearData() {
    this.tasks.clear()
    notifyDataSetChanged()
  }

  override fun getItemCount(): Int {
    return tasks.size
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    var view: View = LayoutInflater.from(parent?.context)
        .inflate(R.layout.item_task, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    holder?.bindData(tasks[position])
  }

  class ViewHolder : RecyclerView.ViewHolder {
    var textTitle: TextView? = null
    var textDescription: TextView? = null

    constructor(itemView: View?) : super(itemView) {
      textTitle = itemView?.findViewById(R.id.textTitle)
      textDescription = itemView?.findViewById(R.id.textDescription)
    }


    fun bindData(task: Task) {
      textTitle?.text = task.title
      if (task?.description != null) {
        textDescription?.text = task.description
      } else {
        textDescription?.visibility = View.GONE
      }
    }
  }
}