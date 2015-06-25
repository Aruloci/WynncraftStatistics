package ch.bbcag.wynncraftstatistics;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by zpfisd on 24.06.2015.
 */
public class AsyncTaskJSONParser extends AsyncTask<String, Void , Map<String, String>> {
    final String TAG = "AsyncTaskJSONParser";
    String API_URL = "https://api.wynncraft.com/public_api.php?action=playerStats&command=";
    ProgressDialog mDialog;
    int methodeType;
    static Context context;
    Holder holder;
    ConnectivityManager connectivityManager;
    private Activity activity;


    public AsyncTaskJSONParser(ProgressDialog mDialog, int methodeType, Context context, Holder holder, ConnectivityManager connectivityManager, Activity activity){
        this.mDialog = mDialog;
        this.methodeType = methodeType;
        this.context = context;
        this.holder = holder;
        this.connectivityManager = connectivityManager;
        this.activity = activity;
    }


    protected Map<String, String> doInBackground(String... params) {
        Map<String, String> result = null;

        API_URL += params[0];

        if (isNetworkConnectionAvailable()) {
            try {
                URL url = new URL(API_URL);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                int responseCode = connection.getResponseCode();

                if (HttpURLConnection.HTTP_OK == responseCode) {
                    if (methodeType == 0) {
                        result = JSONParser.parsePlayerStats(connection.getInputStream());
                    } else if (methodeType == 1) {
                        result = JSONParser.vaidateUsername(connection.getInputStream(),  params[0], context);
                    }

                } else {
                    Log.e(TAG, String.format("An error occurred while loading the data in the background. HTTP status: %d", responseCode));
                }

                connection.disconnect();

            } catch (Exception e) {
                Log.e(TAG, "An error occurred while loading the data in the background", e);
            }
        }
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }

        return result;
    }

    @Override
    protected void onPostExecute(Map<String, String> result) {

        if (!isNetworkConnectionAvailable()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle("Internet Connection failed");
            alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });

            alert.show();
        } else {

        if (result != null) {
            if (methodeType == 0) {
                PlayerStatsHolder holder = (PlayerStatsHolder) this.holder;
                String rank = result.get("rank");
                holder.getRank().setText(rank);
                if (rank.equals("Moderator")) {
                    holder.getRank().setTextColor(context.getResources().getColor(R.color.orange));
                } else if (rank.equals("Administrator")) {
                    holder.getRank().setTextColor(context.getResources().getColor(R.color.red));
                } else if (rank.equals("Builder") || rank.equals("Game Master")) {
                    holder.getRank().setTextColor(context.getResources().getColor(R.color.aqua));
                }

                validatePlayerStats(result);
                holder.getPlaytimeText().setText(result.get("playtime") + "h");
                holder.getTotallevelText().setText("Total Lvl: " + result.get("total_level"));

                holder.getMageLabel1().setText("Lvl: " + result.get("mage_level"));
                holder.getMageLabel2().setText("Lvl: " + result.get("darkwizard_level"));
                holder.getArcherLabel1().setText("Lvl: " + result.get("archer_level"));
                holder.getArcherLabel2().setText("Lvl: " + result.get("hunter_level"));
                holder.getWarriorLabel1().setText("Lvl: " + result.get("warrior_level"));
                holder.getWarriorLabel2().setText("Lvl: " + result.get("knight_level"));
                holder.getAssassinLabel1().setText("Lvl: " + result.get("assassin_level"));
                holder.getAssassinLabel2().setText("Lvl: " + result.get("ninja_level"));
            } else if (methodeType == 1) {
                // Nothing
            } else if (methodeType == 2) {

            }
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
            alert.setTitle("User was not found");
            alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });

            alert.show();
            }
        }

    }

    @Override
    protected void onCancelled() {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
    }

    private boolean isNetworkConnectionAvailable() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return null != networkInfo && networkInfo.isConnected();
    }

    /**
     * Checks if any class level is null and changes it to 0
     * @param playerStats
     */
    public void validatePlayerStats(Map<String, String> playerStats) {
        if (playerStats.get("darkwizard_level") == null) {
            playerStats.put("darkwizard_level", "0");
        }
        if (playerStats.get("hunter_level") == null) {
            playerStats.put("hunter_level", "0");
        }

        if (playerStats.get("knight_level") == null) {
            playerStats.put("knight_level", "0");
        }

        if (playerStats.get("ninja_level") == null) {
            playerStats.put("ninja_level", "0");
        }
    }

}