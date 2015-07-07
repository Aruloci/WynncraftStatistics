package ch.bbcag.wynncraftstatistics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.bbcag.wynncraftstatistics.Player.Player;
import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by zdomaa on 19.06.2015.
 */
public class IconTextListAdapter extends ArrayAdapter<Player> {
    private LayoutInflater mInflater;
    private List<Player> items;

    public IconTextListAdapter(Context context, List<Player> items, LayoutInflater inflater) {
        super(context, -1, items);
        this.items = items;
        this.mInflater = inflater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.icon_text_list_item, null);
        final Player currItem = (Player) items.get(position);
        ImageView userIcon = (ImageView) convertView.findViewById(R.id.friendsItem_usericon);

        if (currItem.getHardIcon() == null) {
            currItem.loadPlayerIcon(userIcon, 64);
        } else {
            userIcon.setImageDrawable(currItem.getHardIcon());
        }


        TextView username = (TextView) convertView.findViewById(R.id.friendsItem_username);
        username.setText(currItem.getPlayerName());

        if (currItem.getCurrentServer() != null){
            TextView currentServer = (TextView) convertView.findViewById(R.id.friendsItem_Server);
            currentServer.setText(currItem.getCurrentServer());

        }

        return convertView;
    }
}
