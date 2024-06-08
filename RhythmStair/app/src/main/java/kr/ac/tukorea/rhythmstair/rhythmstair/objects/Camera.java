package kr.ac.tukorea.rhythmstair.rhythmstair.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;

public class Camera implements IGameObject {
    private static final String TAG = Camera.class.getSimpleName();

    public static int oldX = 0, oldY = 0;
    public static int nowX = 0, nowY = 0;
    public static float targetX = 0.0f, targetY = 0.0f;
    public static float nowOffsetX = 0.0f, nowOffsetY = 0.0f;
    public static float lagDegree = 0.5f;

    public Camera() {
        clear();
    }

    public void clear() {
        oldX = 0;
        oldY = 0;
        nowX = 0;
        nowY = 0;

        targetX = 0.0f;
        targetY = 0.0f;

        nowOffsetX = 0.0f;
        nowOffsetY = 0.0f;
    }

    public void moveLeft() {
        oldX = nowX;
        oldY = nowY;
        nowX -= 1;
        nowY += 1;
    }

    public void moveRight() {
        oldX = nowX;
        oldY = nowY;
        nowX += 1;
        nowY += 1;
    }

    public void moveUp() {
        oldY = nowY;
        nowY += 1;
    }

    @Override
    public void update(float elapsedSeconds) {
        targetX = nowX * Stair.width;
        targetY = nowY * Stair.height;
        nowOffsetX = nowOffsetX + (targetX - nowOffsetX) * lagDegree;
        nowOffsetY = nowOffsetY + (targetY - nowOffsetY) * lagDegree;
    }

    @Override
    public void draw(Canvas canvas) {
        return;
    }
}
