package kr.ac.tukorea.rhythmstair.rhythmstair.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;

public class Camera implements IGameObject {
    private static final String TAG = Camera.class.getSimpleName();

    public static int numX = 0, numY = 0;
    private static float targetX = 0.0f, targetY = 0.0f;
    public static float nowX = 0.0f, nowY = 0.0f;
    private static float lagDegree = 0.5f;

    public Camera() {
        clear();
    }

    public void clear() {
        numX = 0;
        numY = 0;
        targetX = 0.0f;
        targetY = 0.0f;
        nowX = 0.0f;
        nowY = 0.0f;
    }

    public void moveLeft() {
        numX -= 1;
        numY += 1;
    }

    public void moveRight() {
        numX += 1;
        numY += 1;
    }

    public void moveUp() {
        targetY += 1;
    }

    @Override
    public void update(float elapsedSeconds) {
        targetX = numX * Stair.width;
        targetY = numY * Stair.height;
        nowX = nowX + (targetX - nowX) * lagDegree;
        nowY = nowY + (targetY - nowY) * lagDegree;
    }

    @Override
    public void draw(Canvas canvas) {
        return;
    }
}
