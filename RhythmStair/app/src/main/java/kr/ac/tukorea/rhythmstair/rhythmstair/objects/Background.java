package kr.ac.tukorea.rhythmstair.rhythmstair.objects;

import android.graphics.Rect;

import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;
import kr.ac.tukorea.rhythmstair.framework.objects.Sprite;

public class Background extends Sprite implements IGameObject {
    private static final String TAG = Background.class.getSimpleName();

    private final int left = 0, top = 3200, right = 900, bottom = 4800;

    private int  targetY = 0;
    private float nowOffsetY = 0.0f;
    private float lagDrgree = 0.5f;

    public Background(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);
        srcRect = new Rect(left, top, right, bottom);
    }

    @Override
    public void update(float elapsedSeconds) {
        targetY = Camera.nowX;
        nowOffsetY = nowOffsetY + (targetY - nowOffsetY) * lagDrgree;

        srcRect.top = top - (int)(nowOffsetY);
        srcRect.bottom = bottom - (int)(nowOffsetY);
    }
}
