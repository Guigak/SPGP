package kr.ac.tukorea.rhythmstair.rhythmstair.game;

import android.view.MotionEvent;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.objects.DiagonalScrollBackground;
import kr.ac.tukorea.rhythmstair.framework.objects.HorizontalScrollableSprites;
import kr.ac.tukorea.rhythmstair.framework.objects.Sprite;
import kr.ac.tukorea.rhythmstair.framework.scene.Scene;
import kr.ac.tukorea.rhythmstair.framework.view.Metrics;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();

    private final HorizontalScrollableSprites titleSprites = new HorizontalScrollableSprites();
    private float titlesPositionY = 0.1f;
    private float titlesHeight = 0.2f;

    private final HorizontalScrollableSprites difficultySprites = new HorizontalScrollableSprites();
    private float difficultyPositionY = 0.3f;
    private float difficultyHeight = 0.05f;

    private final HorizontalScrollableSprites charactersSprites = new HorizontalScrollableSprites();
    private float charactersPositionY = 0.4f;
    private float charactersWidth = 0.5f;
    private float charactersHeight = 0.5f;

    public enum Layer {
        titles, difficulty, characters, COUNT
    }

    public MainScene() {
        initLayers(Layer.COUNT);

        titleSprites.add(R.mipmap.hr_title);
        titleSprites.add(R.mipmap.ts_title);
        titleSprites.setPositionY(titlesPositionY);
        titleSprites.setHeight(titlesHeight);
        add(Layer.titles, titleSprites);

        difficultySprites.add(R.mipmap.easy);
        difficultySprites.add(R.mipmap.hard);
        difficultySprites.setPositionY(difficultyPositionY);
        difficultySprites.setHeight(difficultyHeight);
        add(Layer.difficulty, difficultySprites);

        charactersSprites.add(R.mipmap.character_00);
        charactersSprites.add(R.mipmap.character_10);
        charactersSprites.setPositionY(charactersPositionY);
        charactersSprites.setWidth(charactersWidth);
        charactersSprites.setHeight(charactersHeight);
        add(Layer.characters, charactersSprites);
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    private HorizontalScrollableSprites targetSprites = null;

    @Override
    public boolean onTouch(MotionEvent event) {
        float[] tempPoints = Metrics.fromScreen(event.getX(), event.getY());
        float inputY = tempPoints[1] / Metrics.height;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (inputY > titlesPositionY && inputY < titlesPositionY + titlesHeight) {
                    targetSprites = titleSprites;
                    return titleSprites.onTouch(event);
                }
                if (inputY > difficultyPositionY && inputY < difficultyPositionY + difficultyHeight) {
                    targetSprites = difficultySprites;
                    return difficultySprites.onTouch(event);
                }
                if (inputY > charactersPositionY && inputY < charactersPositionY + charactersHeight) {
                    targetSprites = charactersSprites;
                    return charactersSprites.onTouch(event);
                }
            case MotionEvent.ACTION_MOVE:
                if (targetSprites == null) {
                    return true;
                }

                return targetSprites.onTouch(event);
            case MotionEvent.ACTION_UP:
                if (targetSprites == null) {
                    return true;
                }

                targetSprites.onTouch(event);
                targetSprites = null;
                return true;
        }

        return false;
    }
}
