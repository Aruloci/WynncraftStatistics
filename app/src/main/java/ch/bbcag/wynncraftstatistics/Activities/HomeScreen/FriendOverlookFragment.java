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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.wynncraftstatistics.Adapter.IconTextListAdapter;
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
    private ListView playerList = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        myView = inflater.inflate(R.layout.search_layout, container, false);
        getActivity().getIntent().putExtra("mode", "ownName");
        TextView title = (TextView) myView.findViewById(R.id.title);
        title.setText("Friends");

        ListView list = (ListView) myView.findViewById(R.id.friendlist);
        setHasOptionsMenu(true);
        Intent homeIntent = getActivity().getIntent();

        parseFriendsFetcher = new ParseFriendsFetcher(mDialog, getActivity().getApplicationContext(),
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE), getActivity(),list);
        parseFriendsFetcher.execute(homeIntent.getStringExtra("username"));
        playerList = parseFriendsFetcher.getFriendsList();

        friendSearcher = new FriendSearcher((ArrayList) parseFriendsFetcher.getItems());


        AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                hideSoftKeyboard();

                for (int i = 0; i < parent.getCount(); i++ ) {
                    ((Player)parent.getItemAtPosition(i)).cancel();
                }

                Player selectedPlayer = (Player) parent.getItemAtPosition(position);
                String SelectedUsername = selectedPlayer.getPlayerName();
                getActivity().getIntent().putExtra("mode", "friendName");
                getActivity().getIntent().putExtra("friendName", SelectedUsername);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();

            }
        };

        searchBar = (EditText) myView.findViewById(R.id.searchbar);
        searchBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    hideSoftKeyboard();
                    return true;
                }
                return false;
            }
        });
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Player> result = friendSearcher.adaptFriendsToInput(friendSearcher.readInput(searchBar));

                IconTextListAdapter adapter = new IconTextListAdapter(getActivity().getBaseContext(), result, getActivity().getLayoutInflater());
                playerList.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        list.setOnItemClickListener(mListClickedHandler);
        return myView;
    }

    @Override
    public void onPause() {
        hideSoftKeyboard();
        Log.v("T", "OnPause of loginFragment");
        super.onPause();
        for (int i = 0; i < playerList.getCount(); i++ ) {
            ((Player)playerList.getItemAtPosition(i)).cancel();
        }
    }

    public void hideSoftKeyboard() {
        if(getActivity().getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }
}
