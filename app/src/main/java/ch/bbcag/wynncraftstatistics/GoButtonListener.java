package ch.bbcag.wynncraftstatistics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

/**
 * Created by zdomaa on 17.06.2015.
 */
public class GoButtonListener implements View.OnClickListener {
    private Activity activity;

    public GoButtonListener(Activity activity){
        super();
        this.activity = activity;
    }



    @Override
    public void onClick(View v) {
        Intent homeIntent = new Intent(activity, home.class);
        EditText usernameView = (EditText) activity.findViewById(R.id.input);
        String username = usernameView.getText().toString();

        homeIntent.putExtra("username", username);
        this.saveUsername(username);

        activity.startActivity(homeIntent);
    }

    private void saveUsername(String username){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", username);
        editor.commit();
    }
}
