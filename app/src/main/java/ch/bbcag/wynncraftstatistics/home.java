package ch.bbcag.wynncraftstatistics;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class home extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.burger); //Sets burger

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        android.support.v4.app.Fragment selectedFragment = null;

        switch (position) {
            case 0:
                selectedFragment = PlaceholderFragment.newInstance(position + 1);
                break;
            case 1:
                selectedFragment = PlaceholderFragment.newInstance(position + 1);
                break;
            case 2:
                selectedFragment = PlaceholderFragment.newInstance(position + 1);
                break;
            case 3:
                selectedFragment = new FriendOverlookFragment();
                break;
            case 4:
                selectedFragment = PlaceholderFragment.newInstance(position + 1);
                break;

        }


        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, selectedFragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.app_name);
                break;
            case 2:
                mTitle = getString(R.string.app_name);
                break;
            case 3:
                mTitle = getString(R.string.app_name);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            PlayerStatsHolder holder;
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            Player user = new Player();
            user.setPlayerName(getActivity().getIntent().getStringExtra("username"));

            ImageView playerImage = (ImageView) rootView.findViewById(R.id.userIcon);
            user.getPlayerIcon(playerImage);

            TextView username = (TextView) rootView.findViewById(R.id.username);
            username.setText(user.getPlayerName());

            holder = new PlayerStatsHolder(
                    (ImageView) rootView.findViewById(R.id.userIcon),
                    (TextView) rootView.findViewById(R.id.rank),
                    (TextView) rootView.findViewById(R.id.playtimeText),
                    (TextView) rootView.findViewById(R.id.totallevelText),

                    (TextView) rootView.findViewById(R.id.mageLabel1),
                    (TextView) rootView.findViewById(R.id.mageLabel2),
                    (TextView) rootView.findViewById(R.id.archerLabel1),
                    (TextView) rootView.findViewById(R.id.archerLabel2),
                    (TextView) rootView.findViewById(R.id.warriorLabel1),
                    (TextView) rootView.findViewById(R.id.warriorLabel2),
                    (TextView) rootView.findViewById(R.id.assassinLabel1),
                    (TextView) rootView.findViewById(R.id.assassinLabel2)
            );
            new AsyncTaskJSONParser(null, 0, getActivity().getApplicationContext(), holder, (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE)).execute(user.getPlayerName());
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((home) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
