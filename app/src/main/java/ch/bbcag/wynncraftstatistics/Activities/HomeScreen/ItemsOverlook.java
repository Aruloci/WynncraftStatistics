package ch.bbcag.wynncraftstatistics.Activities.HomeScreen;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.wynncraftstatistics.Adapter.IconTextListAdapter;
import ch.bbcag.wynncraftstatistics.JSON.ParseItemsFetcher;
import ch.bbcag.wynncraftstatistics.Player.FriendSearcher;
import ch.bbcag.wynncraftstatistics.Player.Player;
import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by zdomaa on 08.07.2015.
 */
public class ItemsOverlook extends Fragment {
    View myView;
    private ProgressDialog mDialog;
    private EditText searchBar = null;
    private FriendSearcher friendSearcher;
    private ParseItemsFetcher task;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        myView = inflater.inflate(R.layout.search_layout, container, false);
        ListView list = (ListView) myView.findViewById(R.id.list);
        TextView title = (TextView) myView.findViewById(R.id.title);
        title.setText("Items");
        mDialog = ProgressDialog.show(getActivity(), getActivity().getString(R.string.loading), getActivity().getString(R.string.wait));
        setHasOptionsMenu(true);
        task = new ParseItemsFetcher(mDialog, getActivity().getApplicationContext(),
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE), getActivity(),list );
        task.execute("");
        //TODO Clicklistener

        friendSearcher = new FriendSearcher((ArrayList) task.getItems());

        searchBar = (EditText) myView.findViewById(R.id.searchbar);
        searchBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
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
                task.getItemsList().setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return myView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_friends_overlook, menu);
    }

    public void hideSoftKeyboard() {
        if(getActivity().getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void onPause() {
        super.onPause();
        hideSoftKeyboard();
        searchBar.setText("");
    }
}
