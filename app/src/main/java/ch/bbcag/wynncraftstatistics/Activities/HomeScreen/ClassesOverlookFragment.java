package ch.bbcag.wynncraftstatistics.Activities.HomeScreen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.wynncraftstatistics.Activities.WynClassFragment;
import ch.bbcag.wynncraftstatistics.Adapter.IconTextListAdapter;
import ch.bbcag.wynncraftstatistics.Player.Player;
import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by Yevgen on 07.07.2015.
 */
public class ClassesOverlookFragment extends Fragment {

    View myView;
    private ProgressDialog mDialog;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        myView = inflater.inflate(R.layout.overlook, container, false);
        ListView list = (ListView) myView.findViewById(R.id.list);
        TextView title = (TextView) myView.findViewById(R.id.title);
        title.setText("Classes");
        setHasOptionsMenu(true);
        List<Player> classes = new ArrayList<Player>();
        String[] classnames = {"Warrior", "Archer", "Mage", "Assassin"};
        List<Drawable> classIcons = new ArrayList<Drawable>();
        classIcons.add(getResources().getDrawable(R.drawable.warrior));
        classIcons.add(getResources().getDrawable(R.drawable.archer));
        classIcons.add(getResources().getDrawable(R.drawable.mage));
        classIcons.add(getResources().getDrawable(R.drawable.assassin));
        int index = 0;
        for (Drawable drawable : classIcons){
            addClass(classes, classnames[index], drawable);
            index++;
        }
        list.setAdapter(new IconTextListAdapter(getActivity().getApplicationContext(), classes, getActivity().getLayoutInflater()));
        AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Player curClass = (Player) parent.getItemAtPosition(position);
                String classname = curClass.getPlayerName();
                Intent wynClassIntent = getActivity().getIntent();
                wynClassIntent.putExtra("classname", classname);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, new WynClassFragment()).addToBackStack(null).commit();

            }
        };
        list.setOnItemClickListener(mListClickedHandler);

        return myView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_friends_overlook, menu);
    }

    private void addClass (List<Player> classes, String classname, Drawable classIcon){
        Player player = new Player(classname);
        player.setHardIcon(classIcon);
        classes.add(player);
    }

}

