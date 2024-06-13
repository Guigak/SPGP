package kr.ac.tukorea.rhythmstair.rhythmstair.game;

import android.view.MotionEvent;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.resource.SoundPlayer;
import kr.ac.tukorea.rhythmstair.framework.scene.Scene;
import kr.ac.tukorea.rhythmstair.framework.view.Metrics;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.Background;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.Camera;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.Character;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.JudgeSprite;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.StairManager;

public class MadeScene extends Scene {
    private static final String TAG = MadeScene.class.getSimpleName();

    public enum State {
        playing, fail, clear
    }
    public static State state = State.playing;

    private int map = 0;

    public static float playTime = 0.0f;
    private float endTime = 0.0f;
    private static final float delayTime = 2.0f;    // game over

    private Background background = null;

    private final Camera camera = new Camera();

    private Character character = null;

    private StairManager stairManager = null;

    public enum Layer {
        cam, character, manager, COUNT
    }

    public MadeScene(int map) {
        this.map = map;

        initLayers(Layer.COUNT);

        add(Layer.cam, camera);

        character = new Character(0);
        add(Layer.character, character);

        stairManager = new StairManager();
        add(Layer.manager, stairManager);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);

        if (state == State.clear) {
            if (playTime - endTime > delayTime) {
                stairManager.printStairs();
                Scene.pop();
            }
        }

        playTime += elapsedSeconds;
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if (state != State.playing) {
            return true;
        }

        int count = event.getPointerCount();

        float[] tempPoints;
        float inputX;
        float inputY;

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
        case MotionEvent.ACTION_DOWN:
            tempPoints = Metrics.fromScreen(event.getX(0), event.getY(0));
            inputX = tempPoints[0] / Metrics.width;
            inputY = tempPoints[1] / Metrics.height;

            processTouchEvent(inputX, inputY);
            break;
        case MotionEvent.ACTION_POINTER_DOWN:
            for (int i = 1; i < count; ++i) {
                tempPoints = Metrics.fromScreen(event.getX(0), event.getY(0));
                inputX = tempPoints[0] / Metrics.width;
                inputY = tempPoints[1] / Metrics.height;

                processTouchEvent(inputX, inputY);
            }
            break;
        }

        return true;
    }

    private void processTouchEvent(float x, float y) {
        if (y < 0.0f) {
            return;
        }

        if (y < 0.2f) {
            state = State.clear;
            endTime = playTime;

            return;
        }

        if (x > 0.5f) {
            character.move();
            camera.move();
            stairManager.addStair(0, playTime);
        }
        else {
            character.move();
            camera.turn();
            stairManager.addStair(1, playTime);
        }
    }

    @Override
    protected void onStart() {
        state = State.playing;
        endTime = 0.0f;
        playTime = 0.0f;
        playSound(map);
    }

    private void playSound(int map) {
        switch (map) {
            case 0:
                SoundPlayer.playSound(R.raw.hyperspace_rhythm);
                break;
            case 1:
                SoundPlayer.playSound(R.raw.time_shift_edit);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        SoundPlayer.stopSound();
    }
}
