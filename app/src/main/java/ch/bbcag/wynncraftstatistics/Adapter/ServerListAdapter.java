package ch.bbcag.wynncraftstatistics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.bbcag.wynncraftstatistics.R;
import ch.bbcag.wynncraftstatistics.Server;

/**
 * Created by zdomaa on 03.07.2015.
 */
public class ServerListAdapter extends ArrayAdapter<Server> {
    private LayoutInflater mInflater;
    private List<Server> items;

    public ServerListAdapter(Context context, List<Server> items, LayoutInflater inflater){
        super(context, -1, items);
        this.items = items;
        this.mInflater = inflater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.server_list_item, null);

        final Server curServer = items.get(position);

        TextView name = (TextView) convertView.findViewById(R.id.serverItem_name);
        name.setText(curServer.getServername());

        TextView playerCount = (TextView) convertView.findViewById(R.id.serveritem_playercount);
        playerCount.setText(curServer.getCurPlayerCount() + " / 60");
        return convertView;
    }

}
