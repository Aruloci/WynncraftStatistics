package ch.bbcag.wynncraftstatistics.Activities.HomeScreen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import ch.bbcag.wynncraftstatistics.Activities.Login;
import ch.bbcag.wynncraftstatistics.Activities.WynncraftMapWebView;
import ch.bbcag.wynncraftstatistics.JSON.PlayerStatsFetcher;
import ch.bbcag.wynncraftstatistics.Player.Player;
import ch.bbcag.wynncraftstatistics.Player.PlayerStatsHolder;
import ch.bbcag.wynncraftstatistics.R;


public class HomeScreen extends ActionBarActivity
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
        boolean isLogout = false;
        boolean isMap = false;
        this.getIntent().putExtra("mode", "ownName");

        if (position == 0) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment()).commit();
            return;
        } else if (position == 1) {
            selectedFragment = new HomeFragment();

        } else if (position == 2) {
            selectedFragment = new ServerOverlookFragment();

        } else if (position == 3) {
            selectedFragment = new FriendOverlookFragment();

        } else if (position == 4) {
            selectedFragment = new ClassesOverlookFragment();

        } else if (position == 5) {
            selectedFragment = new ItemsOverlook();

        } else if (position == 6) {
            isMap = true;

        } else if (position == 7) {
            isLogout = true;

        }


        // update the main content by replacing fragments
        if (!isLogout && !isMap) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, selectedFragment).addToBackStack(null).commit();
        } else if (isLogout) {
            Intent logout = new Intent(this, Login.class);
            logout.putExtra("ignoreSavedName", "true");
            this.startActivity(logout);
        } else if (isMap) {
            Intent map = new Intent(this, WynncraftMapWebView.class);
            this.startActivity(map);
        }
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
        private ProgressDialog mDialog;
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
            user.loadPlayerIcon(playerImage, 256);

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
            mDialog = ProgressDialog.show(getActivity(), getString(R.string.loading), getString(R.string.wait));
            new PlayerStatsFetcher(mDialog, getActivity().getApplicationContext(),
                    (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE), getActivity(), holder).execute(user.getPlayerName());

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeScreen) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}
