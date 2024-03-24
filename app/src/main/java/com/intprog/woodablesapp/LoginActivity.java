package com.intprog.woodablesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    public static final String username = "bonk";
    public static final String password = "alvin123";

    EditText userEditText;
    EditText passEditText;
    Button toProfile;
    TextView toForgot;
    TextView toRegister;
    // Method to show a dialog for invalid username
    private void showInvalidUsernameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid Username")
                .setMessage("The username you entered is incorrect. Please try again.")
                .setPositiveButton("OK", null); // You can add an OnClickListener for the button if needed
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Method to show a dialog for invalid password
    private void showInvalidPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid Password")
                .setMessage("The password you entered is incorrect. Please try again.")
                .setPositiveButton("OK", null); // You can add an OnClickListener for the button if needed
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEditText = findViewById(R.id.userIn);
        passEditText = findViewById(R.id.passIn);
        toProfile = findViewById(R.id.toprofilelogin);
        toForgot = findViewById(R.id.forgotpassword);
        toRegister = findViewById(R.id.register);

        toProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inUser = userEditText.getText().toString();
                String inPass = passEditText.getText().toString();

                Log.d("LoginActivity", "Clicked login button");
                Log.d("LoginActivity", "Entered username: " + inUser);
                Log.d("LoginActivity", "Entered password: " + inPass);

                if(inUser.equals(username) && inPass.equals(password)) {
                    Intent navprofile = new Intent(LoginActivity.this,ClientProfileActivity.class);
                    startActivity(navprofile);
                }else {
                    if (!inUser.equals(username)) {
                        Snackbar.make(v, "Invalid Credentials, please try again", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(v, "Invalid Credentials, please try again", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

        toForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navforgot = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(navforgot);
            }
        });

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent navreg = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(navreg);
            }
        });
    }



}