package ch.bbcag.wynncraftstatistics;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zdomaa on 19.06.2015.
 */
public class FriendOverlookFragment extends Fragment {
    View myView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        myView = inflater.inflate(R.layout.friends_overlook, container, false);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;


        List<Player> testUser = new ArrayList<Player>();
        testUser.add(new Player("Eleking02"));
        testUser.add(new Player("D14m0nD", "WC7"));
        testUser.add(new Player("Phong6"));
        testUser.add(new Player("Herobrine", "WC2"));
        testUser.add(new Player("Novagamer1"));
        testUser.add(new Player("Notch"));
        testUser.add(new Player("Salted", "WC7"));
        testUser.add(new Player("Eleking02"));
        testUser.add(new Player("Herobrine", "WC2"));
        testUser.add(new Player("Mario"));

        ListView list = (ListView) myView.findViewById(R.id.friend_list);
        list.setAdapter(new FriendListAdapter(myView.getContext(), testUser, inflater));

        return myView;
    }
}
