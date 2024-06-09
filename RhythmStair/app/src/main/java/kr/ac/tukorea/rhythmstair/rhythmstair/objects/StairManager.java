package kr.ac.tukorea.rhythmstair.rhythmstair.objects;

import android.content.res.AssetManager;
import android.graphics.Canvas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import kr.ac.tukorea.rhythmstair.framework.activity.GameActivity;
import kr.ac.tukorea.rhythmstair.framework.interfaces.IGameObject;
import kr.ac.tukorea.rhythmstair.rhythmstair.game.PlayScene;

public class StairManager implements IGameObject {
    private static String TAG = StairManager.class.getSimpleName();

    private ArrayList<Stair> stairs = new ArrayList<>();

    private float perfectTime = 0.1f;
    private float farTime = 0.5f;

    public StairManager() {
    }

    public int loadStairs(int map, int diff) {
        String fileName = "";

        switch (map) {
            case 0:
                switch (diff) {
                    case 0:
                        fileName = "hyperspace_rhythm_e.txt";
                        break;
                    case 1:
                        fileName = "hyperspace_rhythm_h.txt";
                        break;
                    default:
                        break;
                }
                break;
            case 1:
                switch (diff) {
                    case 0:
                        fileName = "time_shift_e.txt";
                        break;
                    case 1:
                        fileName = "time_shift_h.txt";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        int stairNum = 0;
        AssetManager assetManager = GameActivity.activity.getAssets();
        try (InputStream inputStream = assetManager.open("maps/" + fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            int direction = 1;
            int stairX = 0;

            stairs.add(new Stair(stairNum, stairX, map, stairNum % 3, -2.0f));
            stairNum += 1;

            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] stairInfo = line.split(" ");

                switch (stairInfo[0]) {
                    case "0":
                        stairX += direction;
                        break;
                    case "1":
                        direction *= -1;
                        stairX += direction;
                        break;
                    default:
                        break;
                }

                stairs.add(new Stair(stairNum, stairX, map, stairNum % 3, Float.parseFloat(stairInfo[1])));
                stairNum += 1;
            }

        } catch (IOException e) {
            return -1;
        }

        return stairNum - 1;
    }

    public enum Judge {
        PERFECT, LATE, EARLY, MISS
    }

    public Judge judge() {
        float stairTime = stairs.get(Camera.nowY).getTime();

        if (stairTime >= PlayScene.playTime - perfectTime && stairTime <= PlayScene.playTime + perfectTime) {
            return Judge.PERFECT;
        }
        else if (stairTime >= PlayScene.playTime - farTime && stairTime <= PlayScene.playTime - perfectTime) {
            return Judge.EARLY;
        }
        else if (stairTime >= PlayScene.playTime + perfectTime && stairTime <= PlayScene.playTime + farTime) {
            return Judge.LATE;
        }
        else {
            return Judge.MISS;
        }
    }

    @Override
    public void update(float elapsedSeconds) {
        for (int i = 0; i < stairs.size(); ++i) {
            stairs.get(i).update(elapsedSeconds);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < stairs.size(); ++i) {
            stairs.get(i).draw(canvas);
        }
    }
}
