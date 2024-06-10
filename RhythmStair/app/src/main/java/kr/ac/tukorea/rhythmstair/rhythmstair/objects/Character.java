package kr.ac.tukorea.rhythmstair.rhythmstair.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;
import kr.ac.tukorea.rhythmstair.framework.objects.Sprite;
import kr.ac.tukorea.rhythmstair.rhythmstair.game.PlayScene;

public class Character implements IGameObject {
    private static String TAG = Character.class.getSimpleName();

    private Sprite sprite = null;
    private final int spriteWidth = 400;

    private float oldX = 0.5f, oldY = 0.625f;
    private float width = 0.4f, height = 0.3f;

    private float animationFPS = 10.0f;
    private float fullAnimationTime = 2.0f / animationFPS;
    private float animatedTime = 2.0f;
    private float delayTime = 0.5f; // game over

    public Character(int num) {
        setCharacter(num);
    }

    private void setCharacter(int num) {
        switch (num) {
            case 0:
                sprite = new Sprite(R.mipmap.character_0, oldX, oldY, width, 0.0f);
                break;
            case 1:
                sprite = new Sprite(R.mipmap.character_1, oldX, oldY, width, 0.0f);
                break;
        }
    }

    public void move() {
        animatedTime = 0.0f;
    }

    @Override
    public void update(float elapsedSeconds) {
        animatedTime += elapsedSeconds;

        int animationNum = (int)(animatedTime * animationFPS);

        int newSrcX = 0;
        int newSrcY = 0;

        float newDstX = oldX + (Camera.nowX * Stair.width - Camera.nowOffsetX);
        float newDstY = oldY - (Camera.nowY * Stair.height - Camera.nowOffsetY);

        if (PlayScene.state == PlayScene.State.fail) {
            newDstY += Math.max(0.0f, animatedTime - fullAnimationTime - delayTime);
        }

        if (Camera.oldX <= Camera.nowX) {
            newSrcY = 0;
        }
        else {
            newSrcY = spriteWidth;
        }

        switch (animationNum) {
            case 0:
                newSrcX = animationNum * spriteWidth;

                newDstX = oldX + (Camera.oldX - Camera.nowX) * Stair.width + (Camera.nowX * Stair.width - Camera.nowOffsetX);
                newDstY = oldY - (Camera.oldY - Camera.nowY) * Stair.height - (Camera.nowY * Stair.height - Camera.nowOffsetY);
                break;
            case 1:
                newSrcX = animationNum * spriteWidth;
                break;
            default:
                newSrcX = 2 * spriteWidth;
                break;
        }

        sprite.setSrcRect(newSrcX, newSrcY, spriteWidth, spriteWidth);
        sprite.setDstRect(newDstX, newDstY, width, height);
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }
}
