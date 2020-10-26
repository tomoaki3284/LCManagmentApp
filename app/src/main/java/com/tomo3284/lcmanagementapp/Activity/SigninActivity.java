package com.tomo3284.lcmanagementapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tomo3284.lcmanagementapp.Models.ProblemList;
import com.tomo3284.lcmanagementapp.Models.User;
import com.tomo3284.lcmanagementapp.R;

import java.util.regex.Pattern;

public class SigninActivity extends AppCompatActivity {

    private TextInputLayout mUsernameTVLayout;
    private TextInputLayout mPasswordTVLayout;
    private TextInputLayout mEmailTVLayout;
    private TextInputEditText mUsernameET;
    private TextInputEditText mPasswordET;
    private TextInputEditText mEmailET;
    private AppCompatButton mSignUpButton;
    private TextView mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        setupView();
        setupButton();
    }
    
    private void setupButton() {
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateInput()) return;

                String username = mUsernameET.getText().toString();
                String password = mPasswordET.getText().toString();
                String email = mEmailET.getText().toString();

                // TODO: 2020/10/25 : check if the username (pk) already exist in database

                User user = new User(username, password, email, new ProblemList());

                // TODO: 2020/10/25 : add to database through repo


            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/10/25 : launch login activity
            }
        });
    }

    private boolean validateInput() {
        if(mUsernameET.getText() == null || !mUsernameET.getText().toString().matches("[A-Za-z][^.]*")){
            mUsernameTVLayout.setError("invalid input");
            return false;
        }
        if(mPasswordET.getText() == null){
            mPasswordTVLayout.setError("invalid input");
            return false;
        }
        if(TextUtils.isEmpty(mEmailET.getText()) || !Patterns.EMAIL_ADDRESS.matcher(mEmailET.getText().toString()).matches()){
            mEmailTVLayout.setError("invalid input");
            return false;
        }
        return true;
    }

    private void setupView() {
        mUsernameTVLayout = findViewById(R.id.input_usernameLayout);
        mPasswordTVLayout = findViewById(R.id.input_passwordLayout);
        mEmailTVLayout = findViewById(R.id.input_emailLayout);
        mSignUpButton = findViewById(R.id.btn_signUp);
        mLoginButton = findViewById(R.id.link_login);
        mUsernameET = findViewById(R.id.input_username);
        mPasswordET = findViewById(R.id.input_password);
        mEmailET = findViewById(R.id.input_email);
    }
}