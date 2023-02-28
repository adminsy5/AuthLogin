package com.mpiyush3510.authlogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mpiyush3510.authlogin.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=new DbHelper(this);
        myMethods();
    }

    private void myMethods()
    {
        binding.BtnSignUp.setOnClickListener(v -> {
            if(isValidSignUp()) {
                signUp();
            }
        });
        binding.TextViewSignIn.setOnClickListener(v -> startActivity(new Intent(SignUp.this, SignIn.class)));
    }

    private void showToast(String Message)
    {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    private Boolean isValidSignUp() {
        if(binding.EditName.getText().toString().isEmpty()){
            showToast("Please Enter Name");
            return false;
        } else if (binding.EditCourse.getText().toString().isEmpty()) {
            showToast("Please Enter Your Course");
            return false;
        }else if(binding.EditEmail.getText().toString().isEmpty()){
            showToast("Please Enter Email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.EditEmail.getText().toString()).matches()) {
            showToast("Please Enter Valid Email !");
            return false;
        } else if (binding.EditMno.getText().toString().isEmpty()) {
            showToast("Enter Your Mobile Number");
            return false;
        } else if (binding.EditPassword.getText().toString().isEmpty()) {
            showToast("Password Can't Be Empty");
            return false;
        }else if (binding.EditConfirmPassword.getText().toString().isEmpty()) {
            showToast("Enter Confirm Password");
            return false;
        }else if (!binding.EditPassword.getText().toString().matches(binding.EditConfirmPassword.getText().toString())) {
            showToast("Password Can't be Same");
            return false;
        }
        return true;
    }

    private void signUp()
    {
        String email=binding.EditEmail.getText().toString();
        String password= binding.EditPassword.getText().toString();

            boolean checkEmail=db.checkEmail(email);
            if(!checkEmail){
                boolean insert=db.insertData(email,password);
                if(insert){
                    isLoading(true);
                    showToast("Sign Up 😊");
                    Intent intent = new Intent(getApplicationContext(), SignIn.class);
                    startActivity(intent);
            }else{
                    showToast("Error ! Try Again ...");
                }
        }else{
                showToast("You Have Already Account ! Sign In Please ...");
            }
    }

    private void isLoading(Boolean Loading)
    {
        if(Loading){
            binding.BtnSignUp.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }else {
            binding.BtnSignUp.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}