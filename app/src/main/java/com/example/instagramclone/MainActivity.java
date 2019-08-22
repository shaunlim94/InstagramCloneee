package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickPower, edtKickSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(MainActivity.this);

        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
    }

//    public void helloWorldTapped(View view) {

//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed", 200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//                if (e == null) {
//                    Toast.makeText(MainActivity.this,
//                            "boxer object saved successfully",
//                            Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        @Override
        public void onClick(View view) {

            try {

                final ParseObject kickBoxer = new ParseObject("KickBoxer");
                kickBoxer.put("name", edtName.getText().toString());
                kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
                kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
                kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
                kickBoxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));
                kickBoxer.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(MainActivity.this,
                                    kickBoxer.get("name") + " is saved to server",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
//                    Toast.makeText(MainActivity.this,
//                            kickBoxer.get("name")
//                                    + " is saved to server", Toast.LENGTH_SHORT).show();
                        } else {
                            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
//                    Toast.makeText(MainActivity.this,
//                            e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } catch (Exception e) {

                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
            }
        }
    }

