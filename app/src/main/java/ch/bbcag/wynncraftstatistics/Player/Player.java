package ch.bbcag.wynncraftstatistics.Player;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.wynncraftstatistics.R;

/**
 * Created by zpfisd on 18.06.2015.
 */
public class Player {
    private String playerName;
    private String currentServer;
    private static boolean iconIsLoading = false;
    private static LruCacheUserIcons cache = new LruCacheUserIcons();
    private ImageDownloader task;
    private static List<ArrayList<Object>> warteListe = new ArrayList<ArrayList<Object>>();
    private Drawable hardIcon;

    public Drawable getHardIcon() {
        return hardIcon;
    }


    public void setHardIcon(Drawable hardIcon) {
        this.hardIcon = hardIcon;
    }

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
            if (iconIsLoading){
                Player redundanz = null;
                if (warteListe.size() > 0) {
                    for (ArrayList<Object> vorhandenesTrio : warteListe) {
                        Player vorhandenerPlayer = (Player) vorhandenesTrio.get(0);
                        if (vorhandenerPlayer.getPlayerName().equals(this.getPlayerName())) {
                            redundanz = vorhandenerPlayer;
                        }

                    }
                }

                if (redundanz != null){
                    warteListe.remove(redundanz);
                }

                ArrayList<Object> trio = new ArrayList<Object>();
                trio.add(0, this);
                trio.add(1, imageView);
                trio.add(2, size);

                warteListe.add(0, trio);

            } else {
                orderDownload(this, imageView, size);
                Player.iconIsLoading = true;
            }
        }
    }

    public static void orderDownload(Player player, ImageView imageView, Integer size){
        ImageDownloader task = new ImageDownloader(player.playerName, cache, imageView);
        String[] url = {"https://api.wynncraft.com/avatar/"
                + player.playerName + "/"
                + size + ".png", player.playerName, String.valueOf(size)};
        task.execute(url);

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

    public static void setIconIsLoading(boolean iconIsLoading) {
        Player.iconIsLoading = iconIsLoading;
    }

    public static List<ArrayList<Object>> getWarteListe() {
        return warteListe;
    }

    public static void setWarteListe(List<ArrayList<Object>> warteListe) {
        Player.warteListe = warteListe;
    }
}
