package kr.ac.tukorea.rhythmstair.framework.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;
import kr.ac.tukorea.rhythmstair.framework.scene.Scene;

public class GameView extends View implements Choreographer.FrameCallback {
    private static final String TAG = GameView.class.getSimpleName();

    public GameView(Context context) {
        super(context);

        setFullScreen();

        initGame();
        scheduleUpdate();
    }

    public static Resources res;
    private final ArrayList<IGameObject> gameObjects = new ArrayList<>();

    private void initGame() {
        res = getResources();
    }

    private boolean running = true;
    private long previousNanos = 0;
    private float elapsedSeconds;
    private void scheduleUpdate() {
        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void doFrame(long nanos) {
        long elapsedNanos = nanos - previousNanos;
        elapsedSeconds = elapsedNanos / 1_000_000_000f;
        if (previousNanos != 0) {
            update();
        }
        invalidate();
        if (running) {
            scheduleUpdate();
        }
        previousNanos = nanos;
    };

    private void update() {
        Scene scene = Scene.top();
        if (scene != null) {
            scene.update(elapsedSeconds);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Scene scene = Scene.top();
        if (scene == null) {
            return;
        }
        canvas.save();
        Metrics.concat(canvas);
        scene.draw(canvas);
        canvas.restore();
    }

    public void setFullScreen() {
        int flags = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        setSystemUiVisibility(flags);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Metrics.onSize(w, h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Scene scene = Scene.top();
        if (scene != null) {
            boolean handled = scene.onTouch(event);
            if (handled) return true;
        }
        return super.onTouchEvent(event);
    }

    public void onBackPressed() {
        Scene scene = Scene.top();
        if (scene == null) {
            Scene.finishActivity();
            return;
        }
        boolean handled = scene.onBackPressed();
        if (handled) return;

        Scene.pop();
    }

    public void pauseGame() {
        running = false;
        Scene.pauseTop();
    }

    public void resumeGame() {
        if (running) return;
        running = true;
        previousNanos = 0;
        scheduleUpdate();
        Scene.resumeTop();
    }

    public void destroyGame() {
        Scene.popAll();
    }
}
