package com.toandoan.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by doan.van.toan on 12/27/17.
 */

public class BaseActivity extends AppCompatActivity {

    public void setUpActionBar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
