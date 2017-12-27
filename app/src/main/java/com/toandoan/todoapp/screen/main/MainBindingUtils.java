package com.toandoan.todoapp.screen.main;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.toandoan.todoapp.utils.EndlessScrollListener;

/**
 * Created by doan.van.toan on 12/26/17.
 */

public class MainBindingUtils {
    @BindingAdapter("adapter")
    public static void setRecyclerAdapter(RecyclerView view, RecyclerView.Adapter adapter){
        view.setAdapter(adapter);
    }

    @BindingAdapter("endlessListener")
    public static void setRecyclerEndlessListener(RecyclerView view, EndlessScrollListener listener){
        view.addOnScrollListener(listener);
    }
}
