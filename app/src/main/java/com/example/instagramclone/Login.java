package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignupLoginActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignupLoginActivity = findViewById(R.id.btnSignupLoginActivity);

        btnLoginActivity.setOnClickListener(this);
        btnSignupLoginActivity.setOnClickListener(this);
        if (ParseUser.getCurrentUser() != null) {

            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnLoginActivity:
                ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                        edtLoginPassword.getText().toString(),

                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {

                                if (user != null && e == null) {

                                    FancyToast.makeText(Login.this, user.getUsername()
                                                    + " is logged in succesfully",
                                            FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                    transitionToSocialMediaActivity();
                                }
                            }
                        });


                break;

            case R.id.btnSignupLoginActivity:


                break;

        }
    }

    private void transitionToSocialMediaActivity() {

        Intent intent = new Intent(Login.this, SocialMediaActivity.class);
        startActivity(intent);
    }

}
