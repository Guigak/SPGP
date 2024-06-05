package kr.ac.tukorea.rhythmstair.framework.objects;

import android.graphics.Canvas;
import android.view.MotionEvent;

import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;
import kr.ac.tukorea.rhythmstair.framework.view.Metrics;

public class Button extends Sprite implements IGameObject {
    private static final String TAG = Button.class.getSimpleName();

    public Button(int bitmapResId, float cx, float cy, float width, float height) {
        super(bitmapResId, cx, cy, width, height);
    }

    public boolean onTouch(MotionEvent event) {
        return false;
    }
}
