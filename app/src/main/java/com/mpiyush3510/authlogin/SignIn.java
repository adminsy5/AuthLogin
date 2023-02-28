package com.mpiyush3510.authlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.mpiyush3510.authlogin.databinding.ActivitySignInBinding;
import com.mpiyush3510.authlogin.databinding.ActivitySignUpBinding;

public class SignIn extends AppCompatActivity {

    ActivitySignInBinding binding;
    DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=new DbHelper(this);
        myMethods();
    }

    private void myMethods()
    {
        binding.BtnSignIn.setOnClickListener(v -> {
            if(isValidSignIn()) {
                signIn();
            }
        });
    }

    private void showToast(String Message)
    {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    private Boolean isValidSignIn() {
        if(binding.EditEmail.getText().toString().isEmpty()){
            showToast("Please Enter Email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.EditEmail.getText().toString()).matches()) {
            showToast("Please Enter Valid Email !");
            return false;
        } else if (binding.EditPassword.getText().toString().isEmpty()) {
            showToast("Password Can't Be Empty");
            return false;
        }
        return true;
    }

    private void signIn()
    {
        isLoading(true);
        String email=binding.EditEmail.getText().toString();
        String password= binding.EditPassword.getText().toString();

        boolean checkEmailPassword=db.checkEmailPassword(email,password);
        if(checkEmailPassword){
            isLoading(false);
            showToast("Welcome ❤️");
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }else{
            showToast("Invalid Credential !");
        }
    }

    private void isLoading(Boolean Loading)
    {
        if(Loading){
            binding.BtnSignIn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }else {
            binding.BtnSignIn.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}