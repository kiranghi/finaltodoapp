package com.example.finaltodoapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.finaltodoapp.viewModel.TodoViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.muddzdev.styleabletoast.StyleableToast;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    Fragment fragment;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragment = new ListTodoFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.list_activity_container, fragment)
                .commit();
        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_delete_comp:
                ShowAlertDeleteCompleted();
                break;
            case R.id.mnu_delete_all:
                ShowAlertDeleteAll();
                break;
            case R.id.mnu_logout:
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                StyleableToast.makeText(getApplicationContext(),"User Logged Out",R.style.successToast).show();
                Intent intent= new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Alert Dialog Box For Delete All
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)//for getdrawable method
    void ShowAlertDeleteAll(){
        new MaterialAlertDialogBuilder(MainActivity.this,
                R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("To Do List")
                .setMessage(getString(R.string.alert_delete_all))
                .setIcon(R.drawable.ic_delete)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TodoViewModel viewModel  = new ViewModelProvider(MainActivity.this).get(TodoViewModel.class);
                        viewModel.deleteAll();
                        StyleableToast.makeText(getApplicationContext(),"All Tasks Deleted",R.style.deleteToast).show();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    //Alert Dialog Box For Delete All
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)//for getdrawable method
    void ShowAlertDeleteCompleted(){
        new MaterialAlertDialogBuilder(MainActivity.this,
                R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("To Do List")
                .setMessage(getString(R.string.alert_delete_comp))
                .setIcon(R.drawable.ic_delete)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TodoViewModel viewModel  = new ViewModelProvider(MainActivity.this).get(TodoViewModel.class);
                        viewModel.deleteAllCompleted();
                        StyleableToast.makeText(getApplicationContext(),"Completed Tasks Deleted",R.style.deleteToast).show();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

}
