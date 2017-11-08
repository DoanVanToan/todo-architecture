package com.toandoan.todo.screen.main

import com.toandoan.todo.data.model.Task

/**
 * Created by framgia on 11/8/17.
 */
interface OnItemCheckListenner {
  fun onCheckChange(task: Task)
}