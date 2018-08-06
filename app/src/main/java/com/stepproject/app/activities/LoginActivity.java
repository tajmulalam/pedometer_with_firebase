package com.stepproject.app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.stepproject.app.R;
import com.stepproject.app.managers.LoginManager;
import com.stepproject.app.models.User;
import com.stepproject.app.utils.SharedPreferenceValue;

import java.util.Date;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginManager.LoginFeedbackListener {
    private TextView tvSignup;
    private EditText etCreEmail;
    private EditText etCrePass;
    private Button btnLogin;
    private LoginManager loginManager;
    private LoginActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
    }

    private void initUI() {
        activity = this;
        tvSignup = findViewById(R.id.tvSignup);
        etCreEmail = findViewById(R.id.etCreEmail);
        etCrePass = findViewById(R.id.etCrePass);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        loginManager = new LoginManager(activity, true, this);
        checkAlreadyLoginOrNot();
    }

    private void checkAlreadyLoginOrNot() {
        if (SharedPreferenceValue.getLoggedinUser(activity)!=-1){
            startDashboard();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSignup:
                startLoginActivity();
                break;
            case R.id.btnLogin:
                performLogin();
                break;
        }
    }

    private void performLogin() {
        loginManager.loginUser(etCreEmail.getText().toString(),
                etCrePass.getText().toString());
    }

    private void startLoginActivity() {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        finish();
    }

    @Override
    public void getLoggedinUser(User user) {
        if (user != null) {
            SharedPreferenceValue.clearLoggedInuserData(activity);
            SharedPreferenceValue.setLoggedInUser(activity, user.getUserID());
            startDashboard();
        }
    }

    private void startDashboard() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        finish();
    }

    @Override
    public void noUserFound() {
        Toast.makeText(activity, "Wrong Credential.Login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void emailError() {
        etCreEmail.setError(getString(R.string.email_required_text));
    }

    @Override
    public void passwordError() {
        etCrePass.setError("Please provide valid password that minimum 6 character");
    }
}
