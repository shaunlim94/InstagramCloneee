package com;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("WtCZMgKtfKkl9vILUjFeBLwrunhD2tAc1cNEFqXw")
                // if defined
                .clientKey("ZKYI6HiJZe1uJoA50aygVr8tWbRnFpKR1Po7LiSA")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }

}
