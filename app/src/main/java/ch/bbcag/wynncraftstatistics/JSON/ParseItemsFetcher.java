package ch.bbcag.wynncraftstatistics.JSON;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ch.bbcag.wynncraftstatistics.Adapter.IconTextListAdapter;
import ch.bbcag.wynncraftstatistics.HelperClass;
import ch.bbcag.wynncraftstatistics.Player.Player;

/**
 * Created by zdomaa on 08.07.2015.
 */
public class ParseItemsFetcher extends JSONConnectionBuilder {
    private ListView itemsList;
    private List<Player> items = new ArrayList<Player>();

    public ParseItemsFetcher(ProgressDialog mDialog, Context context, ConnectivityManager connectivityManager, Activity activity, ListView itemsList) {
        super(mDialog, context, connectivityManager, activity);
        this.itemsList = itemsList;
        API_URL = "https://api.wynncraft.com/public_api.php?action=items";
    }

    public List<Player> getItems() {
        return items;
    }

    @Override
    public Map<String, String> executeJSONOrder(InputStream inputStream, String username, Context context) {
        return JSONParser.parseAllItems(inputStream);
    }

    @Override
    public void useData(Map<String, String> result) {
        for (Map.Entry entry : result.entrySet()){
            if (entry.getValue().equals("ItemName")){
                String itemName = (String) entry.getKey();
                String itemLvl = "Lvl: " + result.get(itemName + "_level");
                String itemCat = result.get(itemName + "_Cat");
                Player curItem = new Player(itemName, itemLvl);
                Drawable icon = activity.getResources().getDrawable(HelperClass.findItemIcon(itemCat));
                curItem.setHardIcon(icon);
                items.add(curItem);

            }
        }

        itemsList.setAdapter(new IconTextListAdapter(context, items, activity.getLayoutInflater()));
    }

    public ListView getItemsList() {
        return itemsList;
    }

}
