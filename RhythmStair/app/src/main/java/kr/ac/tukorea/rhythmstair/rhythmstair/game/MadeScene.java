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

    public MadeScene() {
        this.map = 0;

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

        float[] tempPoints = Metrics.fromScreen(event.getX(), event.getY());
        float inputX = tempPoints[0] / Metrics.width;
        float inputY = tempPoints[1] / Metrics.height;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (inputY < 0.2f) {
                state = State.clear;
                endTime = playTime;

                return true;
            }

            if (inputX > 0.5f) {
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
        return true;
    }

    @Override
    protected void onStart() {
        state = State.playing;
        playTime = 0.0f;
        playSound(map);
    }

    private void playSound(int map) {
        switch (map) {
            case 0:
                SoundPlayer.playSound(R.raw.hyperspace_rhythm);
                break;
            case 1:
                SoundPlayer.playSound(R.raw.time_shift);
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
