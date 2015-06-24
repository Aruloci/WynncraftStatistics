package ch.bbcag.wynncraftstatistics;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.EditText;

/**
 * Created by zdomaa on 17.06.2015.
 */
public class GoButtonListener implements View.OnClickListener {
    private static Activity activity;

    public GoButtonListener(Activity activity){
        super();
        this.activity = activity;
    }



    @Override
    public void onClick(View v) {
        EditText usernameView = (EditText) activity.findViewById(R.id.input);
        String username = usernameView.getText().toString();

        new AsyncTaskJSONParser(null, 1, v.getContext(), null,(ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE), activity).execute(username);
    }


    public static void saveUsername(String username){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", username);
        editor.commit();
    }
}
