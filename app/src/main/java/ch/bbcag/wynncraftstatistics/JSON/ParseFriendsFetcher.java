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

import ch.bbcag.wynncraftstatistics.Adapter.IconTextListAdapter;
import ch.bbcag.wynncraftstatistics.Player.Player;

/**
 * Created by zdomaa on 03.07.2015.
 */
public class ParseFriendsFetcher extends JSONConnectionBuilder {
    ListView friendsList;
    List<Player> items = new ArrayList<Player>();

    public ParseFriendsFetcher(ProgressDialog mDialog, Context context, ConnectivityManager connectivityManager, Activity activity, ListView listView) {
        super(mDialog, context, connectivityManager, activity);
        this.friendsList = listView;
        API_URL = "https://api.wynncraft.com/public_api.php?action=playerStats&command=";
    }

    @Override
    public Map<String, String> executeJSONOrder(InputStream inputStream, String username, Context context) {
        return JSONParser.parseFriends(inputStream, username);
    }

    public ListView getFriendsList() {
        return friendsList;
    }

    public List<Player> getItems() {
        return items;
    }

    @Override
    public void useData(Map<String, String> result) {
        for (Map.Entry entry : result.entrySet()){
            items.add(new Player((String) entry.getValue()));
        }
        friendsList.setAdapter(new IconTextListAdapter(context, items, activity.getLayoutInflater()));

    }
}
