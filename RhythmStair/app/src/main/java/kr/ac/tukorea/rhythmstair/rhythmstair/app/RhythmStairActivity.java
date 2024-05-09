package kr.ac.tukorea.rhythmstair.rhythmstair.app;

import android.os.Bundle;

import kr.ac.tukorea.rhythmstair.framework.activity.GameActivity;
import kr.ac.tukorea.rhythmstair.rhythmstair.game.StartScene;

public class RhythmStairActivity extends GameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new StartScene().push();
    }
}
