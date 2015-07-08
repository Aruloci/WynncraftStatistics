package ch.bbcag.wynncraftstatistics.Activities.HomeScreen;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import ch.bbcag.wynncraftstatistics.JSON.ParseAllItemsFetcher;
import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by zdomaa on 08.07.2015.
 */
public class ItemsOverlook extends Fragment {


    View myView;
    private ProgressDialog mDialog;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        myView = inflater.inflate(R.layout.overlook, container, false);
        ListView list = (ListView) myView.findViewById(R.id.list);
        TextView title = (TextView) myView.findViewById(R.id.title);
        title.setText("Items");
        setHasOptionsMenu(true);
        new ParseAllItemsFetcher(mDialog, getActivity().getApplicationContext(),
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE), getActivity(),list ).execute("");

        AdapterView.OnItemClickListener mListClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {

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
