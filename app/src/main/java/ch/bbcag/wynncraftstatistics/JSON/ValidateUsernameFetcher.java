package ch.bbcag.wynncraftstatistics.JSON;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by zdomaa on 03.07.2015.
 */
public class ValidateUsernameFetcher extends JSONConnectionBuilder {
    public ValidateUsernameFetcher(ProgressDialog mDialog, Context context, ConnectivityManager connectivityManager, Activity activity) {
        super(mDialog, context, connectivityManager, activity);
        API_URL = "https://api.wynncraft.com/public_api.php?action=playerStats&command=";
    }

    @Override
    public Map<String, String> executeJSONOrder(InputStream inputStream, String username, Context context) {
        return JSONParser.vaidateUsername(inputStream, username, context);
    }

    @Override
    public void useData(Map<String, String> result) {
        // Nothing
    }
}
