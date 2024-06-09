package kr.ac.tukorea.rhythmstair.rhythmstair.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;
import kr.ac.tukorea.rhythmstair.framework.objects.Sprite;

public class Stair implements IGameObject {
    private static String TAG = Stair.class.getSimpleName();

    private Sprite inSprite = null;
    private Sprite outSprite = null;

    public static float width = 0.2f, height = 0.05f;

    private float time = 0.0f;
    private float totalElapsedSeconds = 0.0f;
    private float delayTime = 1.0f;

    private int numX = 0;
    private float oldX = 0.5f, oldY = 0.8f;

    public enum Maps {
        Hyperspace_Rhythm, Time_Shift
    }

    public Stair(int numY, int numX, int map, int type, float time) {
        this.numX = numX;
        oldX = oldX + numX * width;
        oldY = oldY - numY * height;
        setResourceId(map, type);
        this.time = time;
    }

    private void setResourceId(int map, int type) {
        if(map == 0) {
            switch(type) {
                case 0:
                    inSprite = new Sprite(R.mipmap.hyperspace_rhythm_block_in_r, oldX, oldY, width, height);
                    outSprite = new Sprite(R.mipmap.hyperspace_rhythm_block_out_r, oldX, oldY, width, height);
                    break;
                case 1:
                    inSprite = new Sprite(R.mipmap.hyperspace_rhythm_block_in_g, oldX, oldY, width, height);
                    outSprite = new Sprite(R.mipmap.hyperspace_rhythm_block_out_g, oldX, oldY, width, height);
                    break;
                case 2:
                    inSprite = new Sprite(R.mipmap.hyperspace_rhythm_block_in_b, oldX, oldY, width, height);
                    outSprite = new Sprite(R.mipmap.hyperspace_rhythm_block_out_b, oldX, oldY, width, height);
                    break;
            }
        }
        else if (map == 1) {
            switch (type) {
                case 0:
                    inSprite = new Sprite(R.mipmap.time_shift_block_in_1, oldX, oldY, width, height);
                    outSprite = new Sprite(R.mipmap.time_shift_block_out_1, oldX, oldY, width, height);
                    break;
                case 1:
                    inSprite = new Sprite(R.mipmap.time_shift_block_in_2, oldX, oldY, width, height);
                    outSprite = new Sprite(R.mipmap.time_shift_block_out_2, oldX, oldY, width, height);
                    break;
                case 2:
                    inSprite = new Sprite(R.mipmap.time_shift_block_in_3, oldX, oldY, width, height);
                    outSprite = new Sprite(R.mipmap.time_shift_block_out_3, oldX, oldY, width, height);
                    break;
            }
        }
    }

    public float getTime() {
        return time;
    }

    public int getNumX() {
        return numX;
    }

    private Paint paint = new Paint();

    @Override
    public void update(float elapsedSeconds) {
        float newX = oldX - Camera.nowOffsetX;
        float newY = oldY + Camera.nowOffsetY;

        totalElapsedSeconds += elapsedSeconds;

        if (totalElapsedSeconds < time - delayTime) {
            paint.setAlpha(0);
        }
        else if (totalElapsedSeconds > time) {
            paint.setAlpha(256);

            inSprite.setDstRect(newX, newY, width, height);
            outSprite.setDstRect(newX, newY, width, height);
        }
        else {
            float percentage = (totalElapsedSeconds - (time - delayTime)) / delayTime;
            int alpha = (int)(256.0f * percentage);
            paint.setAlpha(alpha);

            inSprite.setDstRect(newX, newY, width * percentage, height * percentage);
            outSprite.setDstRect(newX, newY,
                    width * (2.0f - percentage), height * (2.0f - percentage));
        }
    }

    @Override
    public void draw(Canvas canvas) {
        inSprite.draw(canvas, paint);
        outSprite.draw(canvas, paint);
    }
}
