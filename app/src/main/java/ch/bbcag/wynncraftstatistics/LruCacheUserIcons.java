package ch.bbcag.wynncraftstatistics;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by zdomaa on 24.06.2015.
 */
public class LruCacheUserIcons extends LruCache<String, Bitmap> {




    @Override
    public int sizeOf(String key, Bitmap bitmap) {
        return bitmap.getByteCount() / 1024;
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return this.get(key);
    }

    public LruCacheUserIcons() {
        // Ein Achtel von Maximalen Cache Size
        super(((int) Runtime.getRuntime().maxMemory() / 1024) / 8);
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            this.put(key, bitmap);
        }
    }

}
