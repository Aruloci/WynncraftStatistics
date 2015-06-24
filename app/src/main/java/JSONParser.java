import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zpfisd on 24.06.2015.
 */
public class JSONParser {
    private static String TAG = "JSONParser";

    public static Map<String, String> parsePlayerStats (String url, Context context) {
        Map<String, String> playerStats = new HashMap<String, String>();

        try {
            JSONObject playerObject = new JSONObject(url);
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
        } catch (JSONException e) {
            Log.v(TAG, e.toString());
        }
        return playerStats;
    }
}
