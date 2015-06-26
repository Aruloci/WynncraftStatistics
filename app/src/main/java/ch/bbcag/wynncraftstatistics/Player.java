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
    private ImageDownloader task;

    /**
     * Cancels the loadImage task
     */
    public void cancel() {
        if (task != null) {
            task.cancel(true);
        }
    }

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

    public void loadPlayerIcon(ImageView imageView, int size) {
        final String imageKey = playerName;
        final Bitmap bitmap = cache.getBitmapFromMemCache(imageKey + size);

        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.drawable.steve_head);
            task = new ImageDownloader(this.playerName, cache, imageView);
            String[] url = {"https://api.wynncraft.com/avatar/"
                    + this.playerName + "/"
                    + size + ".png", this.playerName, String.valueOf(size)};
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
