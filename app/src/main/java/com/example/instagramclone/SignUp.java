package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;
import java.util.Objects;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    // Ui components
    private EditText edtSignUpEmail, edtSignUpUsername, edtSignUpPassword;
    private Button btnSignup, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setTitle("Sign Up");

        edtSignUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpUsername = findViewById(R.id.edtSignUpUsername);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);

        edtSignUpPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {

                    onClick(btnSignup);
                }


                return false;
            }
        });

        btnSignup = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
//            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSignup:

                if (edtSignUpEmail.getText().toString().equals("") ||
                        edtSignUpUsername.getText().toString().equals("") ||
                        edtSignUpPassword.getText().toString().equals("")) {

                    FancyToast.makeText(SignUp.this, "Email, Username, Password is required!",
                            FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();

                } else {

                    if (edtSignUpEmail.getText().toString().equals("")
                            || edtSignUpUsername.getText().toString().equals("")
                            || edtSignUpPassword.getText().toString().equals("")) {

                        FancyToast.makeText(SignUp.this, "Email, Username, Password is required!"
                                        + " is signed up",
                                FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                    } else {


                    }

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtSignUpEmail.getText().toString());
                    appUser.setUsername(edtSignUpUsername.getText().toString());
                    appUser.setPassword(edtSignUpPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up " + edtSignUpUsername.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {

                            if (e == null) {
                                FancyToast.makeText(SignUp.this, appUser.getUsername()
                                                + " is signed up",
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                transitionToSocialMediaActivity();


                            } else {
                                FancyToast.makeText(SignUp.this, "There was an error: "
                                                + e.getMessage(),
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }

                            progressDialog.dismiss();

                        }

                    });

                }

                break;


            case R.id.btnLogin:

                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);

                break;
        }

    }

    public void rootLayoutTapped(View view) {

// Hides the keyboard

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        assert inputMethodManager != null;
        inputMethodManager.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);

    }

    private void transitionToSocialMediaActivity() {

        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}
