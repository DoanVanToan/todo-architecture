package com.toandoan.todoapp.screen.detail;

import dagger.Component;

/**
 * Created by doan.van.toan on 12/27/17.
 */
@Component(modules = DetailModule.class)
public interface DetailComponent {
    void inject(DetailActivity activity);
}
