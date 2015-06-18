package ch.bbcag.wynncraftstatistics;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by zpfisd on 18.06.2015.
 */
public class Player {
    private String playerName;

    public Player() {
    }

    public Drawable getPlayerIcon() {
        String url = "https://api.wynncraft.com/avatar/" + this.playerName + "/256.png";
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable image = Drawable.createFromStream(is, "src name");
            return image;
        } catch (Exception e) {
            Log.e("Player.java", e.toString());
        }
        return null;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }
}
