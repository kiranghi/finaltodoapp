package com.example.finaltodoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.muddzdev.styleabletoast.StyleableToast;

public class LoginActivity extends AppCompatActivity{
    private final AppCompatActivity activity = LoginActivity.this;
    private NestedScrollView nestedScrollView;
    private TextView txtSignUp;
    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getSupportActionBar().hide();
        initViews();
        Login();

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    void initViews(){
        txtSignUp = (TextView) findViewById(R.id.txtsignup);
        txtUsername = (EditText) findViewById(R.id.inputUsername);
        txtPassword = (EditText) findViewById(R.id.inputPassword);
        btnLogin = (Button) findViewById(R.id.btnSignup);
    }

    void Login(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUsername.getText().toString().equals("") && txtPassword.getText().toString().equals("")){
                    StyleableToast.makeText(LoginActivity.this,"Please Enter Credentials",R.style.errorToast).show();
                }
                else if(txtUsername.getText().toString().equals("kiran") && txtPassword.getText().toString().equals("1234")){
                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref",0);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putBoolean("authentication",true);
                    editor.commit();
                    StyleableToast.makeText(LoginActivity.this,"LoggedIn Successfully",R.style.successToast).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    StyleableToast.makeText(LoginActivity.this,"Invalid Credentials..Try Again",R.style.errorToast).show();
                }
            }
        });

    }
}