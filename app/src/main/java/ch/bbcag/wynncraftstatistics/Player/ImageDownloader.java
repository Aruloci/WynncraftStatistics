package ch.bbcag.wynncraftstatistics.Player;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

import ch.bbcag.wynncraftstatistics.Player.LruCacheUserIcons;

/**
 * Created by zdomaa on 18.06.2015.
 */
public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    private LruCacheUserIcons cache;
    private String username;
    private ImageView imageView;

    public  ImageDownloader (String username, LruCacheUserIcons cache, ImageView imageView){
        this.username = username;
        this.cache = cache;
        this.imageView = imageView;
    }
    protected Bitmap doInBackground(String... params) {
        Bitmap mIcon = null;
        try {
            InputStream in = new java.net.URL(params[0]).openStream();
            mIcon = BitmapFactory.decodeStream(in);
            cache.addBitmapToMemoryCache(params[1] + params[2], mIcon);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return mIcon;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
