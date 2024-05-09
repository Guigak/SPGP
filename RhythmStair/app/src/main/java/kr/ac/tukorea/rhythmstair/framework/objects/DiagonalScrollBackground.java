package kr.ac.tukorea.rhythmstair.framework.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.rhythmstair.framework.view.Metrics;

public class DiagonalScrollBackground extends Sprite {
    private final float speed;
    private final float width;
    private final float height;

    public DiagonalScrollBackground(int bitmapResId, float speed) {
        super(bitmapResId);

        this.width = bitmap.getWidth() * Metrics.width / bitmap.getWidth();
        this.height = bitmap.getHeight() * Metrics.height / bitmap.getHeight();

        setPosition(Metrics.width / 2, Metrics.height / 2, width, height);

        this.speed = speed;
    }

    @Override
    public void update(float elapsedSeconds) {
        this.x -= speed * elapsedSeconds;
        this.y -= speed * elapsedSeconds;
    }

    @Override
    public void draw(Canvas canvas) {
        float currY = y % height;
        float currX = x % width;
        if (currY > 0) currY -= height;
        if (currX > 0) currX -= width;
        while (currY < Metrics.height) {
            float tempX = currX;

            while (tempX < Metrics.width) {
                dstRect.set(tempX, currY, tempX + width, currY + height);
                canvas.drawBitmap(bitmap, null, dstRect, null);

                tempX += width;
            }
            currY += height;
        }
    }
}
