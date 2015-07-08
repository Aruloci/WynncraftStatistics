package ch.bbcag.wynncraftstatistics.Listener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import ch.bbcag.wynncraftstatistics.JSON.ValidateUsernameFetcher;
import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by zdomaa on 17.06.2015.
 */
public class GoButtonListener implements View.OnClickListener {
    private static Activity activity;
    private ProgressDialog mDialog;

    public GoButtonListener(Activity activity){
        super();
        this.activity = activity;
    }



    @Override
    public void onClick(View v) {
        EditText usernameView = (EditText) activity.findViewById(R.id.input);
        String username = usernameView.getText().toString();
        hideSoftKeyboard();
        if (!username.equals("")) {
            mDialog = ProgressDialog.show(activity, activity.getString(R.string.loading), activity.getString(R.string.wait));
            new ValidateUsernameFetcher(mDialog, v.getContext(), (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE), activity).execute(username);
        } else {
            new Toast(activity.getApplicationContext()).makeText(activity.getApplicationContext(), "Username can not be empty", Toast.LENGTH_SHORT).show();
        }
    }


    public static void saveUsername(String username){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", username);
        editor.commit();
    }

    public void hideSoftKeyboard() {
        if(activity.getCurrentFocus() !=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
