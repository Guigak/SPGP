package kr.ac.tukorea.rhythmstair.framework.view;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;

public class Metrics {
    private static final String TAG = Metrics.class.getSimpleName();

    public static float width = 9.0f;
    public static float height = 16.0f;

    public static final RectF screenRect = new RectF();

    public static void setGameSize(float width, float height) {
        Metrics.width = width;
        Metrics.height = height;
    }

    private static final Matrix transfromMatrix = new Matrix();
    private static final Matrix invertedMatrix = new Matrix();
    private static final float[] pointsBuffer = new float[2];

    public static void onSize(int width, int height) {
        float view_ratio = (float)width / (float)height;
        float game_ratio = Metrics.width / Metrics.height;

        if (view_ratio > game_ratio) {
            float scale = height / Metrics.height;
            transfromMatrix.setTranslate(
                    (width - height * game_ratio) / 2, 0);
            transfromMatrix.preScale(scale, scale);
        } else {
            float scale = width / Metrics.width;
            transfromMatrix.setTranslate(
                    0, (height - width / game_ratio) / 2);
            transfromMatrix.preScale(scale, scale);
        }

        transfromMatrix.invert(invertedMatrix);
        screenRect.set(0, 0, width, height);
        invertedMatrix.mapRect(screenRect);
    }

    public static float[] fromScreen(float x, float y) {
        pointsBuffer[0] = x;
        pointsBuffer[1] = y;
        invertedMatrix.mapPoints(pointsBuffer);

        return pointsBuffer;
    }

    public static float[] toScreen(float x, float y) {
        pointsBuffer[0] = x;
        pointsBuffer[1] = y;
        transfromMatrix.mapPoints(pointsBuffer);

        return pointsBuffer;
    }

    public static void concat(Canvas canvas) {
        canvas.concat(transfromMatrix);
    }
}
