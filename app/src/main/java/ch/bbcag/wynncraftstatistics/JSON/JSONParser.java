package ch.bbcag.wynncraftstatistics.JSON;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import ch.bbcag.wynncraftstatistics.Activities.HomeScreen.HomeScreen;
import ch.bbcag.wynncraftstatistics.HelperClass;
import ch.bbcag.wynncraftstatistics.Listener.GoButtonListener;
import ch.bbcag.wynncraftstatistics.ValueComparator;

/**
 * Created by zpfisd on 24.06.2015.
 */
public class JSONParser {
    private static final String TAG = "JSONParser";


    public static Map<String, String> parsePlayerStats (InputStream inputStream) {
        Map<String, String> playerStats = new HashMap<String, String>();

        try {
            String input = HelperClass.readInput(inputStream);
            JSONObject playerObject = new JSONObject(input);
            playerStats.put("username", playerObject.getString("username"));
            playerStats.put("rank", playerObject.getString("rank"));
            playerStats.put("playtime", playerObject.getString("playtime"));

            JSONObject global = playerObject.getJSONObject("global");
            playerStats.put("total_level", global.getString("total_level"));

            JSONObject classes = playerObject.getJSONObject("classes");

            Iterator keys = classes.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                Log.v(TAG, "Current class: " + key);

                JSONObject subClass = classes.getJSONObject(key);
                //Converts int level to String
                Integer curClassLevelInt = new Integer (subClass.getInt("level"));
                String curClassLevelString = new String(curClassLevelInt.toString());

                playerStats.put(key + "_level", curClassLevelString);
            }
        } catch (JSONException | IOException e) {
            Log.e(TAG, e.toString());
        }
        Log.v(TAG, playerStats.toString());
        return playerStats;
    }

    public static Map<String, String> parseFriends(InputStream inputStream, String username) {
        Map<String, String> result = new HashMap<String, String>();

        try {
            String input = HelperClass.readInput(inputStream);
            JSONObject playerObject = new JSONObject(input);
            JSONArray friendArray = playerObject.getJSONArray("friends");

           for (Integer i = 0; i < friendArray.length(); i++) {
               if (!friendArray.getString(i).isEmpty()) {
                   result.put(i.toString(), friendArray.getString(i));
               }
           }
        } catch (JSONException | IOException e) {
            Log.e(TAG, e.toString());
        }
        Log.v(TAG, result.toString());
        return result;
    }

    public static Map<String, String> parseServer(InputStream inputStream) {
        HashMap<String,String> unsorted = new HashMap<String,String>();
        ValueComparator bvc =  new ValueComparator(unsorted);
        TreeMap<String,String> sorted_map = new TreeMap<String,String>(bvc);


        try {
            String input = HelperClass.readInput(inputStream);
            JSONObject allServer = new JSONObject(input);
            Iterator keys = allServer.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                Log.v(TAG, "Current class: " + key);
                if (key.contains("WC")) {
                    JSONArray server = allServer.getJSONArray(key);
                    if (server.length() > 0){

                        Integer lengthInt = new Integer(server.length());
                        String lengthString = new String(lengthInt.toString());
                        unsorted.put(key, lengthString);

                    }
                }
            }
            sorted_map.putAll(unsorted);
        } catch (JSONException | IOException e) {
            Log.e(TAG, e.toString());
        }
        Log.v(TAG, sorted_map.toString());
        return sorted_map;
    }


    public static Map<String, String> vaidateUsername(InputStream inputStream, String username, Context context) {
        Map<String, String> result = new HashMap<String, String>();

        try {
            String input = HelperClass.readInput(inputStream);
            JSONObject playerObject = new JSONObject(input);
            result.put("error", "false");
            Intent homeIntent = new Intent(context, HomeScreen.class);


            homeIntent.putExtra("username", username);
            homeIntent.putExtra("mode", "ownName");
            GoButtonListener.saveUsername(username);
            context.startActivity(homeIntent);

        } catch (JSONException | IOException e) {
            result.put("error", "true");
        }
        Log.v(TAG, result.toString());
        return result;
    }







}
