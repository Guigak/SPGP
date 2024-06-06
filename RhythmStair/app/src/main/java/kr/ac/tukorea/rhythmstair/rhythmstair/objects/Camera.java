package kr.ac.tukorea.rhythmstair.rhythmstair.objects;

public class Camera {
    private static final String TAG = Camera.class.getSimpleName();

    public static int dx = 0, dy = 0;

    public Camera() {
        clear();
    }

    public void clear() {
        dx = 0;
        dy = 0;
    }

    public void moveLeft() {
        dx -= 1;
        dy += 1;
    }

    public void moveRight() {
        dx += 1;
        dy += 1;
    }

    public void moveUp() {
        dy += 1;
    }
}
