package kr.ac.tukorea.rhythmstair.rhythmstair.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

import kr.ac.tukorea.rhythmstair.R;
import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;
import kr.ac.tukorea.rhythmstair.framework.objects.Sprite;

public class JudgeSprite implements IGameObject {
    private static String TAG = JudgeSprite.class.getSimpleName();

    private Sprite targetSprite = null;
    private Sprite perfectSprite = null;
    private Sprite earlySprite = null;
    private Sprite lateSprite = null;
    private Sprite missSprite = null;

    private Paint paint = new Paint();

    private float delayTime = 0.5f;
    private float animatedTime = 10.0f;

    public JudgeSprite(float cx, float cy, float width, float height) {
        perfectSprite = new Sprite(R.mipmap.perfect, cx, cy, width, height);
        earlySprite = new Sprite(R.mipmap.early, cx, cy, width, height);
        lateSprite = new Sprite(R.mipmap.late, cx, cy, width, height);
        missSprite = new Sprite(R.mipmap.miss, cx, cy, width, height);

        targetSprite = perfectSprite;
    }



    public void setJudgement(StairManager.Judgement judgement) {
        animatedTime = 0.0f;

        switch (judgement) {
            case PERFECT:
                targetSprite = perfectSprite;
                break;
            case EARLY:
                targetSprite = earlySprite;
                break;
            case LATE:
                targetSprite = lateSprite;
                break;
            case MISS:
                targetSprite = missSprite;
                break;
            default:
                break;
        }
    }

    @Override
    public void update(float elapsedSeconds) {
        animatedTime += elapsedSeconds;

        if (animatedTime < delayTime) {
            paint.setAlpha((int)(256.0f * (animatedTime / delayTime)));
        }
        else {
            paint.setAlpha(0);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        targetSprite.draw(canvas, paint);
    }
}
