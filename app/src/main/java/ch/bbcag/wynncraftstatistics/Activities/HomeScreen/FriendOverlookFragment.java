package ch.bbcag.wynncraftstatistics.Activities.HomeScreen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import ch.bbcag.wynncraftstatistics.JSON.ParseFriendsFetcher;
import ch.bbcag.wynncraftstatistics.Player.Player;
import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by zdomaa on 19.06.2015.
 */
public class FriendOverlookFragment extends Fragment {
    View myView;
    private ProgressDialog mDialog;
    private ParseFriendsFetcher parseFriendsFetcher = null;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        myView = inflater.inflate(R.layout.overlook, container, false);
        ListView list = (ListView) myView.findViewById(R.id.list);
        setHasOptionsMenu(true);
        Intent homeIntent = getActivity().getIntent();

        parseFriendsFetcher = new ParseFriendsFetcher(mDialog, getActivity().getApplicationContext(),
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE), getActivity(),list);
        parseFriendsFetcher.execute(homeIntent.getStringExtra("username"));

        AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                for (int i = 0; i < parent.getCount(); i++ ) {
                    ((Player)parent.getItemAtPosition(i)).cancel();
                }

                Player selectedPlayer = (Player) parent.getItemAtPosition(position);
                String SelectedUsername = selectedPlayer.getPlayerName();
                getActivity().getIntent().putExtra("mode", "friendName");
                getActivity().getIntent().putExtra("friendName", SelectedUsername);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment()).commit();

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

}
