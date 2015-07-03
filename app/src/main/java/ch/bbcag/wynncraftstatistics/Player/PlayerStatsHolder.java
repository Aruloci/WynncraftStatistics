package ch.bbcag.wynncraftstatistics.Player;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zpfisd on 24.06.2015.
 */
public class PlayerStatsHolder {
    ImageView userIcon;
    TextView rank;
    TextView playtimeText;
    TextView totallevelText;

    TextView mageLabel1;
    TextView mageLabel2;
    TextView archerLabel1;
    TextView archerLabel2;

    TextView warriorLabel1;
    TextView warriorLabel2;
    TextView assassinLabel1;
    TextView assassinLabel2;

    public PlayerStatsHolder(
            ImageView userIcon,
            TextView rank,
            TextView playtimeText,
            TextView totallevelText,

            TextView mageLabel1,
            TextView mageLabel2,
            TextView archerLabel1,
            TextView archerLabel2,
            TextView warriorLabel1,
            TextView warriorLabel2,
            TextView assassinLabel1,
            TextView assassinLabel2) {

        this.userIcon = userIcon;
        this.rank = rank;
        this.playtimeText = playtimeText;
        this.totallevelText = totallevelText;

        this.mageLabel1 = mageLabel1;
        this.mageLabel2 = mageLabel2;
        this.archerLabel1 = archerLabel1;
        this.archerLabel2 = archerLabel2;
        this.warriorLabel1 = warriorLabel1;
        this.warriorLabel2 = warriorLabel2;
        this.assassinLabel1 = assassinLabel1;
        this.assassinLabel2 = assassinLabel2;
    }

    public ImageView getUserIcon() {
        return userIcon;
    }

    public TextView getRank() {
        return rank;
    }

    public TextView getPlaytimeText() {
        return playtimeText;
    }

    public TextView getTotallevelText() {
        return totallevelText;
    }

    public TextView getMageLabel1() {
        return mageLabel1;
    }

    public TextView getMageLabel2() {
        return mageLabel2;
    }

    public TextView getArcherLabel1() {
        return archerLabel1;
    }

    public TextView getArcherLabel2() {
        return archerLabel2;
    }

    public TextView getWarriorLabel1() {
        return warriorLabel1;
    }

    public TextView getWarriorLabel2() {
        return warriorLabel2;
    }

    public TextView getAssassinLabel1() {
        return assassinLabel1;
    }

    public TextView getAssassinLabel2() {
        return assassinLabel2;
    }
}
