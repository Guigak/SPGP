package kr.ac.tukorea.rhythmstair.framework.objects;

import android.graphics.Canvas;
import android.graphics.Paint;

import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;

public class NumberSprites extends Sprite implements IGameObject {
    private static String TAG = NumberSprites.class.getSimpleName();

    private int mipmapX = 0, mipmapY = 0;

    private float x = 0.0f, y = 0.0f;
    private float width = 0.0f, height = 0.0f;

    private int number = 0;

    public NumberSprites(int mipmapId, float x, float y, float width, float height, int num) {
        super(mipmapId);

        this.mipmapX = bitmap.getWidth() / 10;
        this.mipmapY = bitmap.getHeight();

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.number = num;
    }

    @Override
    public void update(float elapsedSeconds) {}

    @Override
    public void draw(Canvas canvas) {
        String textNum = Integer.toString(number);

        float offsetX = (textNum.length() - 1) * width / 2;

        for (int i = 0; i < textNum.length(); ++i) {
            int digit = Character.getNumericValue(textNum.charAt(i));

            setSrcRect(digit * mipmapX, 0, mipmapX, mipmapY);
            setDstRect(x - offsetX + i * width, y, width, height);

            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
        }
    }
}
