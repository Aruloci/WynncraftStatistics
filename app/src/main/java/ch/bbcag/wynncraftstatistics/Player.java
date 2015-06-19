package ch.bbcag.wynncraftstatistics;

import android.widget.ImageView;

/**
 * Created by zpfisd on 18.06.2015.
 */
public class Player {
    private String playerName;
    private String currentServer;

    public Player() {
        this("", null);
    }

    public Player(String playerName) {
        this(playerName, null);
    }

    public Player(String playerName, String currentServer) {
        this.playerName = playerName;
        this.currentServer = currentServer;
    }

    public void getPlayerIcon(ImageView image) {
        String url = "https://api.wynncraft.com/avatar/" + this.playerName + "/256.png";
        new ImageDownloader(image).execute(url);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getCurrentServer() {
        return currentServer;
    }
}
