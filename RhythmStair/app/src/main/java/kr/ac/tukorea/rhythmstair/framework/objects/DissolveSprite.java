package kr.ac.tukorea.rhythmstair.framework.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;

public class DissolveSprite extends Sprite implements IGameObject {
    private static String TAG = DissolveSprite.class.getSimpleName();

    private float dissolveTime = 0.0f;
    private int dissolveValue = 1;

    private float alpha = 0;
    private float maxAlpha = 256.0f;
    private Paint paint = new Paint();

    public DissolveSprite(int mipmapId, float x, float y, float width, float height, float time) {
        super(mipmapId, x, y, width, height);

        dissolveTime = time;
        alpha = 0;
        paint.setAlpha((int)alpha);
    }

    @Override
    public void update(float elapsedSeconds) {
        alpha += maxAlpha * dissolveValue * elapsedSeconds / dissolveTime;

        if (alpha < 0.0f) {
            alpha = 0.0f;
            dissolveValue *= -1;
        }
        else if (alpha > maxAlpha) {
            alpha = maxAlpha;
            dissolveValue *= -1;
        }

        paint.setAlpha((int)alpha);
    }

    @Override
    public void draw(Canvas canvas) {
        draw(canvas, paint);
    }
}
