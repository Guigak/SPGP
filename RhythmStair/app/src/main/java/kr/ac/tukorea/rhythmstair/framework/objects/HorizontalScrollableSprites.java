package kr.ac.tukorea.rhythmstair.framework.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;
import kr.ac.tukorea.rhythmstair.framework.resource.BitmapLoader;
import kr.ac.tukorea.rhythmstair.framework.view.Metrics;

public class HorizontalScrollableSprites implements IGameObject {

    private String TAG = HorizontalScrollableSprites.class.getSimpleName();
    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private int maxNum;
    private int currNum = 0;
    private float lagNum = 3.0f;

    private float oldX, x, y;

    private float[] oldPositions = {0.0f, 0.0f};
    private boolean touched = false;

    private RectF dstRect = new RectF();
    private float widthOffset, height;

    public void add(int mipmapId) {
        if (mipmapId != 0) {
            bitmaps.add(BitmapLoader.get(mipmapId));
            maxNum = bitmaps.size();
        }
    }

    public  void setPositionY(float y) {
        this.y = y * Metrics.height;
    }

    public void setWidth(float width) {
        this.widthOffset = (1 - width) / 2;
    }

    public void setHeight(float height) {
        if (height == 0) {
            this.height = Metrics.height;
        }
        else {
            this.height = height * Metrics.height;
        }
    }

    @Override
    public void update(float elapsedSeconds) {
        if (!touched) {
            x += -x / lagNum;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = -1; i <= 1; ++i) {
            int tartgetNum = (currNum + i);
            if (tartgetNum < 0) {
                tartgetNum += maxNum;
            }
            tartgetNum = tartgetNum % maxNum;

            dstRect.set(x - widthOffset + i * Metrics.width, y,
                    x + widthOffset + (i + 1) * Metrics.width, y + this.height);
            canvas.drawBitmap(bitmaps.get(tartgetNum), null, dstRect, null);
        }
    }

    private void down(float[] points) {
        oldPositions[0] = points[0];
        oldX = x;
    }

    private void move(float[] points) {
        x = oldX + points[0] - oldPositions[0];
    }

    private void up() {
        if (x < -Metrics.width / 2) {
            x += Metrics.width;
            currNum = (currNum + 1) % maxNum;
        }
        else if (x > Metrics.width / 2) {
            x -= Metrics.width;
            currNum = currNum - 1;
            if (currNum < 0) {
                currNum += maxNum;
            }
        }
    }

    public boolean onTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float[] tempPoints = Metrics.fromScreen(event.getX(), event.getY());
                down(tempPoints);
                touched = true;
                return true;
            case MotionEvent.ACTION_MOVE:
                float[] points = Metrics.fromScreen(event.getX(), event.getY());
                move(points);

                return true;
            case MotionEvent.ACTION_UP:
                up();

                touched = false;
                return true;
        }

        return false;
    }

    public int getCurrNum() {
        return currNum;
    }
}
