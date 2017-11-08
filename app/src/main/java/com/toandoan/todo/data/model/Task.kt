package com.toandoan.todo.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import android.databinding.Bindable
import io.reactivex.annotations.NonNull

/***
 *
 */
@Entity(tableName = "tasks")
data class Task(
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @Bindable
    var id: Int = 0,
    @ColumnInfo(name = "title")
    @Bindable
    var title: String? = null,
    @ColumnInfo(name = "description")
    @Bindable
    var description: String? = null,
    @ColumnInfo(name = "isActive")
    @Bindable
    var isActive: Boolean? = null
) : BaseObservable()