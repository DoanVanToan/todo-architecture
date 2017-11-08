package com.toandoan.todo.screen.main

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView

/**
 * Created by framgia on 11/8/17.
 */
object MainBinding {
  @BindingAdapter("bind:recyclerAdapter")
  @JvmStatic
  fun setAdapter(recyclerView: RecyclerView,
      adapter: MainAdapter) {
    recyclerView?.adapter = adapter
  }

  @BindingAdapter("bind:scrollPosition")
  @JvmStatic
  fun setScrollPosition(recyclerView: RecyclerView, position: Int) {
    if (position < 0 || position > recyclerView.childCount - 1) {
      return
    }
    recyclerView?.smoothScrollToPosition(position)
  }


}