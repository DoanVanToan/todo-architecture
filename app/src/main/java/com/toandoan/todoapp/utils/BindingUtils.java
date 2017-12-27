package com.toandoan.todoapp.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by doan.van.toan on 12/27/17.
 */

public class BindingUtils {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView view, String url){
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }
}
