package ch.bbcag.wynncraftstatistics;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ch.bbcag.wynncraftstatistics.JSON.PlayerStatsFetcher;
import ch.bbcag.wynncraftstatistics.Player.Player;
import ch.bbcag.wynncraftstatistics.Player.PlayerStatsHolder;


public class PlayerStatsActivity extends Activity {

    private ProgressDialog mDialog;


    public void onCreateView(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_home);
        Player user = new Player();
        if(this.getIntent().getStringExtra("mode").equals("ownName")){
            user.setPlayerName(this.getIntent().getStringExtra("username"));
        } else if (this.getIntent().getStringExtra("mode").equals("friendName")){
            user.setPlayerName(this.getIntent().getStringExtra("friendName"));
        }


        ImageView playerImage = (ImageView) findViewById(R.id.userIcon);
        user.loadPlayerIcon(playerImage, 256);

        TextView username = (TextView) findViewById(R.id.username);
        username.setText(user.getPlayerName());

        PlayerStatsHolder holder = new PlayerStatsHolder (
                (ImageView)findViewById(R.id.userIcon),
                (TextView) findViewById(R.id.rank),
                (TextView) findViewById(R.id.playtimeText),
                (TextView) findViewById(R.id.totallevelText),
                (TextView) findViewById(R.id.mageLabel1),
                (TextView) findViewById(R.id.mageLabel2),
                (TextView) findViewById(R.id.archerLabel1),
                (TextView) findViewById(R.id.archerLabel2),
                (TextView) findViewById(R.id.warriorLabel1),
                (TextView) findViewById(R.id.warriorLabel2),
                (TextView) findViewById(R.id.assassinLabel1),
                (TextView) findViewById(R.id.assassinLabel2)
        );
        mDialog = ProgressDialog.show(this, "Loading", "Please wait...");
        new PlayerStatsFetcher(mDialog, this.getApplicationContext(),  (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE), this,holder).execute(user.getPlayerName());



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
