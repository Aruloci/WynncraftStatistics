package ch.bbcag.wynncraftstatistics.JSON;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import ch.bbcag.wynncraftstatistics.HelperClass;
import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by zpfisd on 24.06.2015.
 */
public abstract class JSONConnectionBuilder extends AsyncTask<String, Void , Map<String, String>> {
    final String TAG = "JSONConnectionBuilder";
    String API_URL;
    ProgressDialog mDialog;
    static Context context;
    ConnectivityManager connectivityManager;
    protected Activity activity;
    boolean hasCon = true;


    public JSONConnectionBuilder(ProgressDialog mDialog, Context context, ConnectivityManager connectivityManager, Activity activity){
        this.mDialog = mDialog;
        this.context = context;
        this.connectivityManager = connectivityManager;
        this.activity = activity;
    }


    public abstract Map<String, String> executeJSONOrder(InputStream inputStream, String username, Context context);
    public abstract void useData(Map<String, String> result);

    protected Map<String, String> doInBackground(String... params) {

        Map<String, String> result = null;

        API_URL += params[0];

        if (HelperClass.isNetworkConnectionAvailable(connectivityManager)) {
            HttpURLConnection connection = null;
            try {

                URL url = new URL(API_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {

                    // Hier hat die Unterklasse zu bestimmen was passiert
                    result = executeJSONOrder(connection.getInputStream(),  params[0], context);

                } else {
                    Log.e(TAG, String.format("An error occurred while loading the data in the background. HTTP status: %d", responseCode));
                }

            } catch (Exception e) {
                Log.e(TAG, "An error occurred while loading the data in the background", e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        } else {
            hasCon = false;
        }
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }

        return result;
    }


    @Override
    protected void onPostExecute(Map<String, String> result) {

        if (!hasCon) {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle(activity.getString(R.string.no_connection));
            alert.setNeutralButton("OK", null);

            alert.show();
        } else {
            if (result != null) {
                // Hier hat die Unterklasse zu bestimmen was passiert
                useData(result);
            } else {
                Toast toast = Toast.makeText(context, activity.getString(R.string.user_not_found), Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }

    @Override
    protected void onCancelled() {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
    }



}
