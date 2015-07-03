package ch.bbcag.wynncraftstatistics.JSON;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ch.bbcag.wynncraftstatistics.Adapter.ServerListAdapter;
import ch.bbcag.wynncraftstatistics.Server;

/**
 * Created by zdomaa on 03.07.2015.
 */
public class ParseServerFetcher extends JSONConnectionBuilder {
    ListView serverList;


    public ParseServerFetcher(ProgressDialog mDialog, Context context, ConnectivityManager connectivityManager, Activity activity, ListView serverList) {
        super(mDialog, context, connectivityManager, activity);
        this.serverList = serverList;
        API_URL = "https://api.wynncraft.com/public_api.php?action=onlinePlayers";
    }

    @Override
    public Map<String, String> executeJSONOrder(InputStream inputStream, String username, Context context) {
        return JSONParser.parseServer(inputStream);
    }

    @Override
    public void useData(Map<String, String> result) {
        List<Server> serverArray= new ArrayList<Server>();
        for (Map.Entry entry : result.entrySet()){
            serverArray.add(new Server((String) entry.getKey(), (String) entry.getValue()));
        }
        serverList.setAdapter(new ServerListAdapter(context, serverArray, activity.getLayoutInflater()));
    }
}
