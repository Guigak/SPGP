package kr.ac.tukorea.rhythmstair.rhythmstair.objects;

import android.graphics.Canvas;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;
import kr.ac.tukorea.rhythmstair.framework.objects.Sprite;

public class Character implements IGameObject {
    private static String TAG = Character.class.getSimpleName();

    private Sprite tsprite = null;
    private Sprite sprite0 = null;
    private Sprite sprite1 = null;
    private Sprite sprite2 = null;

    private float oldX = 0.5f, oldY = 0.625f;
    private float width = 0.4f, height = 0.3f;

    private float animationFPS = 10.0f;
    private float animatedTime = 10.0f;

    public Character(int num) {
        setCharacter(num);
    }

    private void setCharacter(int num) {
        switch (num) {
            case 0:
                sprite0 = new Sprite(R.mipmap.character_00, oldX, oldY, width, 0.0f);
                sprite1 = new Sprite(R.mipmap.character_01, oldX, oldY, width, 0.0f);
                sprite2 = new Sprite(R.mipmap.character_02, oldX, oldY, width, 0.0f);
                break;
            case 1:
                sprite0 = new Sprite(R.mipmap.character_10, oldX, oldY, width, 0.0f);
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

        float newX = oldX + (Camera.nowX * Stair.width - Camera.nowOffsetX);
        float newY = oldY - (Camera.nowY * Stair.height - Camera.nowOffsetY);

        switch (animationNum) {
            case 0:
                tsprite = sprite1;
                newX = oldX + (Camera.oldX - Camera.nowX) * Stair.width + (Camera.nowX * Stair.width - Camera.nowOffsetX);
                newY = oldY - (Camera.oldY - Camera.nowY) * Stair.height - (Camera.nowY * Stair.height - Camera.nowOffsetY);
                break;
            case 1:
                tsprite = sprite2;
                break;
            default:
                tsprite = sprite0;
                break;
        }

        tsprite.setDstRect(newX, newY, width, height);
    }

    @Override
    public void draw(Canvas canvas) {
        tsprite.draw(canvas);
    }
}
