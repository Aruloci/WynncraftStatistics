package ch.bbcag.wynncraftstatistics.JSON;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;

import java.io.InputStream;
import java.util.Map;

import ch.bbcag.wynncraftstatistics.Player.PlayerStatsHolder;
import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by zdomaa on 03.07.2015.
 */
public class PlayerStatsFetcher extends JSONConnectionBuilder {
    PlayerStatsHolder holder;


    public PlayerStatsFetcher(ProgressDialog mDialog, Context context, ConnectivityManager connectivityManager, Activity activity, PlayerStatsHolder holder) {
        super(mDialog, context, connectivityManager, activity);
        this.holder = holder;
        API_URL = "https://api.wynncraft.com/public_api.php?action=playerStats&command=";
    }

    @Override
    public Map<String, String> executeJSONOrder(InputStream inputStream, String username, Context context) {
        return JSONParser.parsePlayerStats(inputStream);
    }

    @Override
    public void useData(Map<String, String> result) {
        String rank = result.get("rank");
        holder.getRank().setText(rank);
        if (rank.equals("Moderator")) {
            holder.getRank().setTextColor(context.getResources().getColor(R.color.orange));
        } else if (rank.equals("Administrator")) {
            holder.getRank().setTextColor(context.getResources().getColor(R.color.red));
        } else if (rank.equals("Builder") || rank.equals("Game Master")) {
            holder.getRank().setTextColor(context.getResources().getColor(R.color.aqua));
        } else if (rank.equals("VIP")) {
            holder.getRank().setTextColor(context.getResources().getColor(R.color.green));
        } else if (rank.equals("Media")) {
            holder.getRank().setTextColor(context.getResources().getColor(R.color.violet));
        }
        validatePlayerLevel(result);
        holder.getPlaytimeText().setText(result.get("playtime") + " h");
        holder.getTotallevelText().setText("Total Lvl: " + result.get("total_level"));
    }


    /**
     * Checks if any class level is null and changes it to 0
     * @param playerStats
     */
    private void validatePlayerLevel(Map<String, String> playerStats) {
        if (playerStats.get("mage_level") != null) {
            holder.getMageLabel1().setText("Lvl: " + playerStats.get("mage_level"));
        }
        if (playerStats.get("darkwizard_level") != null) {
            holder.getMageLabel2().setText("Lvl: " + playerStats.get("darkwizard_level"));
        }
        if (playerStats.get("archer_level") != null) {
            holder.getArcherLabel1().setText("Lvl: " + playerStats.get("archer_level"));
        }
        if (playerStats.get("hunter_level") != null) {
            holder.getArcherLabel2().setText("Lvl: " + playerStats.get("hunter_level"));
        }
        if (playerStats.get("warrior_level") != null) {
            holder.getWarriorLabel1().setText("Lvl: " + playerStats.get("warrior_level"));
        }
        if (playerStats.get("knight_level") != null) {
            holder.getWarriorLabel2().setText("Lvl: " + playerStats.get("knight_level"));
        }
        if (playerStats.get("assassin_level") != null) {
            holder.getAssassinLabel1().setText("Lvl: " + playerStats.get("assassin_level"));
        }
        if (playerStats.get("ninja_level") != null) {
            holder.getAssassinLabel2().setText("Lvl: " + playerStats.get("ninja_level"));
        }
     }
}
