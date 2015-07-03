package ch.bbcag.wynncraftstatistics.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.bbcag.wynncraftstatistics.Activities.HomeScreen.MenuLine;
import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by zdomaa on 18.06.2015.
 */
public class DrawerListAdapter extends ArrayAdapter<MenuLine> {
    private LayoutInflater inflater;
    private List<MenuLine> items;

    public DrawerListAdapter(Context context, List<MenuLine> items, LayoutInflater inflater) {
        super(context, -1, items);
        this.items = items;
        this.inflater = inflater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView text = null;

        convertView = inflater.inflate(R.layout.drawer_list_item, null);
        final MenuLine currItem = (MenuLine) items.get(position);

        if (currItem.getImageId() != -1) {
            ImageView image = (ImageView) convertView.findViewById(R.id.drawerItem_Image);
            image.setImageResource(currItem.getImageId());


            text = (TextView) convertView.findViewById(R.id.drawerItem_Text);
            text.setText(currItem.getText());
        }


        return convertView;

    }
}