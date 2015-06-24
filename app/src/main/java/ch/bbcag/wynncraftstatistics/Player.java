package ch.bbcag.wynncraftstatistics;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by zpfisd on 18.06.2015.
 */
public class Player {
    private String playerName;
    private String currentServer;
    private static LruCacheUserIcons cache = new LruCacheUserIcons();

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

    public void loadPlayerIcon(ImageView imageView) {
        final String imageKey = playerName;
        final Bitmap bitmap = cache.getBitmapFromMemCache(imageKey);

        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.drawable.steve_head);
            ImageDownloader task = new ImageDownloader(this.playerName, cache, imageView);
            String[] url = {"https://api.wynncraft.com/avatar/" + this.playerName + "/256.png", this.playerName};
            task.execute(url);
        }
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
