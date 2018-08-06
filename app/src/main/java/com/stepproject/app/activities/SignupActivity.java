package com.stepproject.app.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.stepproject.app.R;
import com.stepproject.app.managers.SignupManager;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener, SignupManager.SignUpFeedbackListener {
    private TextView tvLoginNow;
    private ImageButton ibtnBack;
    private EditText etFirstName, etLastName, etEmailAddress, etPass, etCfPass, etAddress;
    private Button ibtnSignUp;
    private SignupManager signupManager;
    private SignupActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initUI();
    }

    // init ui elements
    private void initUI() {
        activity = this;
        signupManager = new SignupManager(activity, true, this);
        tvLoginNow = findViewById(R.id.tvLoginNow);
        ibtnBack = findViewById(R.id.ibtnBack);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etPass = findViewById(R.id.etPass);
        etCfPass = findViewById(R.id.etCfPass);
        etAddress = findViewById(R.id.etAddress);
        ibtnSignUp = findViewById(R.id.ibtnSignUp);


        tvLoginNow.setOnClickListener(this);
        ibtnBack.setOnClickListener(this);
        ibtnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLoginNow:
                startLoginActivity();
                break;
            case R.id.ibtnBack:
                startLoginActivity();
                break;
            case R.id.ibtnSignUp:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        signupManager.signUpUser(etFirstName.getText().toString(),
                etLastName.getText().toString(), etEmailAddress.getText().toString(),
                etPass.getText().toString(), etCfPass.getText().toString(),
                etAddress.getText().toString());
    }

    private void startLoginActivity() {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void signUpSuccess() {
        Toast.makeText(activity, "Sign up success.Now you can login", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startLoginActivity();
            }
        }, 2000);
    }

    @Override
    public void signUpFailed() {
        Toast.makeText(activity, "Error Occurred while processing the request", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void firstNameError() {
        etFirstName.setError("Please provide a valid first name");
    }

    @Override
    public void lastNameError() {
        etLastName.setError("Please provide a valid last name");

    }

    @Override
    public void emailError() {
        etEmailAddress.setError("Please provide a valid email");

    }

    @Override
    public void passwordError() {
        etCfPass.setError("Please provide a valid password with minimum >5 character");

    }

    @Override
    public void addressError() {
        etAddress.setError("Please provide a valid address");

    }

    @Override
    public void validationError() {
        Toast.makeText(activity, "Fillup the form properly", Toast.LENGTH_SHORT).show();
    }
}
