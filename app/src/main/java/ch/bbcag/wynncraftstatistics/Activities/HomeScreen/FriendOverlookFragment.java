package ch.bbcag.wynncraftstatistics.Activities.HomeScreen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import ch.bbcag.wynncraftstatistics.JSON.ParseFriendsFetcher;
import ch.bbcag.wynncraftstatistics.Player.FriendSearcher;
import ch.bbcag.wynncraftstatistics.Player.Player;
import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by zdomaa on 19.06.2015.
 */
public class FriendOverlookFragment extends Fragment {
    View myView;
    private ProgressDialog mDialog;
    private ParseFriendsFetcher parseFriendsFetcher = null;
    private ParseFriendsFetcher asyncTaskJSONParser = null;
    private FriendSearcher friendSearcher;
    private EditText searchBar = null;
    private ListView playerList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        myView = inflater.inflate(R.layout.search_layout, container, false);

        TextView title = (TextView) myView.findViewById(R.id.title);
        title.setText("Friends");

        searchBar = (EditText) myView.findViewById(R.id.searchbar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                friendSearcher.readInput(searchBar);
//                ListView friendList = (ListView) myView.findViewById(R.id.friendlist);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        ListView list = (ListView) myView.findViewById(R.id.friendlist);
        setHasOptionsMenu(true);
        Intent homeIntent = getActivity().getIntent();

        parseFriendsFetcher = new ParseFriendsFetcher(mDialog, getActivity().getApplicationContext(),
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE), getActivity(),list);
        parseFriendsFetcher.execute(homeIntent.getStringExtra("username"));
        playerList = parseFriendsFetcher.getFriendsList();

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

                fragmentManager.beginTransaction().replace(R.id.container,(Fragment) new HomeFragment()).commit();
            }
        };
        list.setOnItemClickListener(mListClickedHandler);
        return myView;
    }

    @Override
    public void onPause() {
        Log.e("DEBUG", "OnPause of loginFragment");
        super.onPause();
        for (int i = 0; i < playerList.getCount(); i++ ) {
            ((Player)playerList.getItemAtPosition(i)).cancel();
        }
    }

}
