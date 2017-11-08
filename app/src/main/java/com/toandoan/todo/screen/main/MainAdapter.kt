package com.toandoan.todo.screen.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import com.toandoan.todo.R
import com.toandoan.todo.data.model.Task

class MainAdapter(var tasks: MutableList<Task>,
    var listenner: OnItemCheckListenner) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {


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
    holder?.bindData(tasks[position], listenner)
  }

  class ViewHolder : RecyclerView.ViewHolder {
    var textTitle: TextView? = null
    var textDescription: TextView? = null
    var checkActive: CheckBox? = null

    constructor(itemView: View?) : super(itemView) {
      textTitle = itemView?.findViewById(R.id.textTitle)
      textDescription = itemView?.findViewById(R.id.textDescription)
      checkActive = itemView?.findViewById(R.id.checkedActive)

    }

    fun bindData(task: Task, listenner: OnItemCheckListenner) {
      textTitle?.text = task.title
      if (task?.description != null) {
        textDescription?.text = task.description
      } else {
        textDescription?.visibility = View.GONE
      }
      checkActive?.isChecked = task.isActive!!
      checkActive?.setOnCheckedChangeListener(
          CompoundButton.OnCheckedChangeListener { compoundButton: CompoundButton,
              isChecked: Boolean ->
            task.isActive = isChecked
            listenner?.onCheckChange(task)
          })
    }
  }
}