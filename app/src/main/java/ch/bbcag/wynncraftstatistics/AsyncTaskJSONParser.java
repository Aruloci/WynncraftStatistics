package ch.bbcag.wynncraftstatistics;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private ListView friendsList;


    public AsyncTaskJSONParser(ProgressDialog mDialog, int methodeType, Context context, Holder holder, ConnectivityManager connectivityManager, Activity activity){
        this.mDialog = mDialog;
        this.methodeType = methodeType;
        this.context = context;
        this.holder = holder;
        this.connectivityManager = connectivityManager;
        this.activity = activity;
    }

    public AsyncTaskJSONParser(ProgressDialog mDialog, int methodeType, Context context, ListView listView, ConnectivityManager connectivityManager, Activity activity) {
        this.mDialog = mDialog;
        this.methodeType = methodeType;
        this.context = context;
        this.friendsList = listView;
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

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    if (methodeType == 0) {
                        result = JSONParser.parsePlayerStats(connection.getInputStream());
                    } else if (methodeType == 1) {
                        result = JSONParser.vaidateUsername(connection.getInputStream(),  params[0], context);
                    } else if (methodeType == 2) {
                        result = JSONParser.parseFriends(connection.getInputStream(), params[0]);
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
            alert.setTitle("No internet connection available");
            alert.setNeutralButton("OK", null);

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
                    } else if (rank.equals("VIP")) {
                        holder.getRank().setTextColor(context.getResources().getColor(R.color.green));
                    } else if (rank.equals("Media")) {
                        holder.getRank().setTextColor(context.getResources().getColor(R.color.violet));
                    }

                    validatePlayerLevel(result);
                    holder.getPlaytimeText().setText(result.get("playtime") + " h");
                    holder.getTotallevelText().setText("Total Lvl: " + result.get("total_level"));
                } else if (methodeType == 1) {
                    // Nothing
                } else if (methodeType == 2) {
                    List<Player> friendsArray= new ArrayList<Player>();
                    for (Map.Entry entry : result.entrySet()){
                        friendsArray.add(new Player((String) entry.getValue()));
                    }
                    friendsList.setAdapter(new FriendListAdapter(context, friendsArray, activity.getLayoutInflater()));
                }
            } else {
                Toast toast = Toast.makeText(context, "User was not found", Toast.LENGTH_SHORT);
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

    private boolean isNetworkConnectionAvailable() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return null != networkInfo && networkInfo.isConnected();
    }

    /**
     * Checks if any class level is null and changes it to 0
     * @param playerStats
     */
    private void validatePlayerLevel(Map<String, String> playerStats) {
        PlayerStatsHolder holder = (PlayerStatsHolder) this.holder;

        if (playerStats.get("mage_level") != null) {
            holder.getMageLabel1().setText("Lvl: " + playerStats.get("mage_level"));
        }
        if (playerStats.get("darkwizard_level") != null) {
            holder.getMageLabel2().setText("Lvl: " + playerStats.get("darkwizard_level"));
        }
        if (playerStats.get("archer_level") != null) {
            holder.getArcherLabel1().setText("Lvl: " + playerStats.get("archer_level"));
        }
        if (playerStats.get("hunter_level") != null) {
            holder.getArcherLabel2().setText("Lvl: " + playerStats.get("hunter_level"));
        }
        if (playerStats.get("warrior_level") != null) {
            holder.getWarriorLabel1().setText("Lvl: " + playerStats.get("warrior_level"));
        }
        if (playerStats.get("knight_level") != null) {
            holder.getWarriorLabel2().setText("Lvl: " + playerStats.get("knight_level"));
        }
        if (playerStats.get("assassin_level") != null) {
            holder.getAssassinLabel1().setText("Lvl: " + playerStats.get("assassin_level"));
        }
        if (playerStats.get("ninja_level") != null) {
            holder.getAssassinLabel2().setText("Lvl: " + playerStats.get("ninja_level"));
        }
    }
}
