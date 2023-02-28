package com.mpiyush3510.authlogin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
public class SignUp extends AppCompatActivity {

    EditText EditName, EditCourse, EditMno, EditEmail, EditPassword, EditConfirmPassword;
    Button BtnSignUp;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditName = findViewById(R.id.EditName);
        EditCourse = findViewById(R.id.EditCourse);
        EditEmail = findViewById(R.id.EditEmail);
        EditMno = findViewById(R.id.EditMno);
        EditPassword = findViewById(R.id.EditPassword);
        EditConfirmPassword = findViewById(R.id.EditConfirmPassword);
        BtnSignUp = findViewById(R.id.BtnSignUp);
    }
}