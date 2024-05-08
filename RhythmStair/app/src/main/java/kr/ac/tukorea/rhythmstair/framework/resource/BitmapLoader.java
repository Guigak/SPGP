package kr.ac.tukorea.rhythmstair.framework.resource;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

import kr.ac.tukorea.rhythmstair.framework.view.GameView;
public class BitmapLoader {
    private static final String TAG = BitmapLoader.class.getSimpleName();
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<>();
    private static BitmapFactory.Options options;

    public static Bitmap get(int mipmapResId) {
        Bitmap bitmap = bitmaps.get(mipmapResId);

        if (bitmap == null) {
            if (options == null) {
                options = new BitmapFactory.Options();
                options.inScaled = false;
            }

            bitmap = BitmapFactory.decodeResource(GameView.res, mipmapResId, options);
            bitmaps.put(mipmapResId, bitmap);
        }

        return bitmap;
    }
}
