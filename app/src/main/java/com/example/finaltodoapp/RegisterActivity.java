package com.example.finaltodoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.finaltodoapp.model.User;
import com.example.finaltodoapp.viewModel.UserViewModel;
import com.muddzdev.styleabletoast.StyleableToast;

public class RegisterActivity extends AppCompatActivity{
        private TextView txtLogin;
        private EditText txtEmail;
        private EditText txtPassword;
        private EditText txtUsername;
        private EditText txtConfirmPassword;
        private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtLogin = (TextView) findViewById(R.id.txtlogin);
        txtEmail = (EditText) findViewById(R.id.inputEmail);
        txtPassword = (EditText) findViewById(R.id.inputPassword);
        txtUsername = (EditText) findViewById(R.id.inputUsername);
        txtConfirmPassword = (EditText) findViewById(R.id.inputConfirmPassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void register(){
        User user = new User();
        user.setEmail(txtEmail.getText().toString());
        user.setUsername(txtUsername.getText().toString());
        user.setPassword(txtPassword.getText().toString());
        UserViewModel uVM = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(UserViewModel.class);
        uVM.insert(user);
        StyleableToast.makeText(this,"User Registered Successfully",R.style.successToast).show();
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);

    }




}