package ch.bbcag.wynncraftstatistics.Activities.HomeScreen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import ch.bbcag.wynncraftstatistics.JSON.ParseServerFetcher;
import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by zdomaa on 03.07.2015.
 */
public class ServerOverlookFragment extends Fragment {

    View myView;
    private ProgressDialog mDialog;
    private ParseServerFetcher parseServerFetcher = null;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        myView = inflater.inflate(R.layout.overlook, container, false);
        ListView list = (ListView) myView.findViewById(R.id.list);
        TextView title = (TextView) myView.findViewById(R.id.title);
        title.setText("Server");
        setHasOptionsMenu(true);
        Intent homeIntent = getActivity().getIntent();

        mDialog = ProgressDialog.show(getActivity(), getActivity().getString(R.string.loading), getActivity().getString(R.string.wait));
        parseServerFetcher = new ParseServerFetcher(mDialog, getActivity().getApplicationContext(),
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE), getActivity(), list);
        parseServerFetcher.execute("");


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
