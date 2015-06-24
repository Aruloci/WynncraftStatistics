package ch.bbcag.wynncraftstatistics;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Button;


public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String username = readUsername();
        Log.v("Login", username);
        if (!username.equals("")){
            Intent homeIntent = new Intent(this, home.class);
            homeIntent.putExtra("username", username);
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
