package kr.ac.tukorea.rhythmstair.rhythmstair.game;

import android.view.MotionEvent;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.scene.Scene;
import kr.ac.tukorea.rhythmstair.framework.view.Metrics;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.Background;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.Camera;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.Character;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.JudgeSprite;
import kr.ac.tukorea.rhythmstair.rhythmstair.objects.StairManager;

public class PlayScene extends Scene {
    private static final String TAG = PlayScene.class.getSimpleName();

    public static float playTime = 0.0f;

    private Background background = null;

    private final Camera camera = new Camera();

    private Character character = null;

    private JudgeSprite judgeSprite = null;

    private StairManager stairManager = null;

    public enum Layer {
        bg, cam, character, judgement, manager, COUNT
    }

    public PlayScene(int map, int diff, int chr) {
        initLayers(Layer.COUNT);

        addBackground(map);

        add(Layer.cam, camera);

        character = new Character(chr);
        add(Layer.character, character);

        judgeSprite = new JudgeSprite(0.5f, 0.2f, 0.4f, 0.05f);
        add(Layer.judgement, judgeSprite);

        stairManager = new StairManager();
        background.setStairsNum(stairManager.loadStairs(map, diff));
        add(Layer.manager, stairManager);
    }

    public void addBackground(int map) {
        switch (map) {
            case 0:
                background = new Background(R.mipmap.hyperspace_rhythm_bg, 0.5f, 0.5f, 1.0f, 1.0f);
                add(Layer.bg, background);
                break;
            case 1:
                background = new Background(R.mipmap.time_shift_bf_light, 0.5f, 0.5f, 1.0f, 1.0f);
                add(Layer.bg, background);
                break;
        }
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
        playTime += elapsedSeconds;
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        float[] tempPoints = Metrics.fromScreen(event.getX(), event.getY());
        float inputX = tempPoints[0] / Metrics.width;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (inputX > 0.5f) {
                character.move();
                camera.move();
            }
            else {
                character.move();
                camera.turn();
            }

            StairManager.Judgement judgement = stairManager.judge();
            judgeSprite.setJudgement(judgement);
            if (judgement == StairManager.Judgement.MISS) {
                new EndScene().push();
            }
        }
        return true;
    }

    @Override
    protected void onStart() {
        playTime = 0.0f;
    }
}
