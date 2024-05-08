package kr.ac.tukorea.rhythmstair.framework.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;
import kr.ac.tukorea.rhythmstair.framework.resource.BitmapLoader;

public class Sprite implements IGameObject {
    private static final String TAG = Sprite.class.getSimpleName();

    protected Bitmap bitmap;

    protected Rect srcRect;
    protected final RectF dstRect = new RectF();

    protected float x, y, dx, dy;
    protected float width, height, radius;

    public Sprite(int mipmapId) {
        if (mipmapId != 0) {
            bitmap = BitmapLoader.get(mipmapId);
        }
    }

    public void setPosition(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.width = this.height = 2 * radius;
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
    }

    public void setPosition(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        radius = Math.min(width, height) / 2;
        dstRect.set(x - width / 2, y - height / 2, x + width / 2, y + height / 2);
    }

    @Override
    public void update(float elapsedSeconds) {
        float timeDx = dx * elapsedSeconds;
        float timeDy = dy * elapsedSeconds;
        x += timeDx;
        y += timeDy;
        dstRect.offset(timeDx, timeDy);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
