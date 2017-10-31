package com.toandoan.todo.screen

interface BaseView<P : BasePresenter> {
  fun presenter(presenter: P)
}