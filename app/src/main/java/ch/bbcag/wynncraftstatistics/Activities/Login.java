package ch.bbcag.wynncraftstatistics.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import ch.bbcag.wynncraftstatistics.Activities.HomeScreen.HomeScreen;
import ch.bbcag.wynncraftstatistics.Listener.GoButtonListener;
import ch.bbcag.wynncraftstatistics.R;


public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String username = readUsername();
        Log.v("Login", username);


        if (!username.equals("") && getIntent().getStringExtra("ignoreSavedName") == null){

            Intent homeIntent = new Intent(this, HomeScreen.class);
            homeIntent.putExtra("username", username);
            homeIntent.putExtra("mode", "ownName");
            startActivity(homeIntent);
        }


        Button goButton = (Button) findViewById(R.id.button);
        goButton.setOnClickListener(new GoButtonListener(this));
    }

    private String readUsername(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String username = sharedPref.getString("username", "");
        return username;

    }

}
