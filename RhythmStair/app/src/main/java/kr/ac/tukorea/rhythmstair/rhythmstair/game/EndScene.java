package kr.ac.tukorea.rhythmstair.rhythmstair.game;

import android.view.MotionEvent;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.objects.Button;
import kr.ac.tukorea.rhythmstair.framework.objects.DissolveSprite;
import kr.ac.tukorea.rhythmstair.framework.objects.HorizontalScrollableSprites;
import kr.ac.tukorea.rhythmstair.framework.objects.NumberSprites;
import kr.ac.tukorea.rhythmstair.framework.objects.Sprite;
import kr.ac.tukorea.rhythmstair.framework.resource.SoundPlayer;
import kr.ac.tukorea.rhythmstair.framework.scene.Scene;
import kr.ac.tukorea.rhythmstair.framework.view.Metrics;

public class EndScene extends Scene {
    private static final String TAG = EndScene.class.getSimpleName();

    public enum Layer {
        bg, numbers, text, COUNT
    }

    public EndScene(PlayScene.State state, int perfect, int early, int late) {
        initLayers(Layer.COUNT);

        addBackground(state);

        addNumbers(perfect, early, late);

        add(Layer.text, new DissolveSprite(R.mipmap.touch_screen, 0.5f, 0.9f, 0.5f, 0.05f, 0.25f));
    }

    private void addBackground(PlayScene.State state) {
        switch (state) {
            case clear:
                add(Layer.bg, new Sprite(R.mipmap.end_clear, 0.5f, 0.5f, 1.0f, 1.0f));
                break;
            case fail:
                add(Layer.bg, new Sprite(R.mipmap.end_fail, 0.5f, 0.5f, 1.0f, 1.0f));
                break;
            default:
                break;
        }
    }

    private void addNumbers(int perfect, int early, int late) {
        add(Layer.numbers, new NumberSprites(R.mipmap.number_sprites, 0.5f, 0.4f, 0.1f, 0.1f, perfect));
        add(Layer.numbers, new NumberSprites(R.mipmap.number_sprites, 0.5f, 0.6f, 0.1f, 0.1f, early));
        add(Layer.numbers, new NumberSprites(R.mipmap.number_sprites, 0.5f, 0.8f, 0.1f, 0.1f, late));
    }

    @Override
    public void update(float elapsedSeconds) {
        super.update(elapsedSeconds);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Scene.pop();
            Scene.pop();
            return true;
        }

        return false;
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}
