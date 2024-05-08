package kr.ac.tukorea.rhythmstair.framework.scene;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.tukorea.rhythmstair.framework.interfaces.IRecyclable;

public class RecycleBin {
    private static final String TAG = RecycleBin.class.getSimpleName();
    protected static HashMap<Class, ArrayList<IRecyclable>> recycleBin = new HashMap<>();

    public static void collect(IRecyclable object) {
        Class classs = object.getClass();
        ArrayList<IRecyclable> bin = recycleBin.get(classs);

        if (bin == null) {
            bin = new ArrayList<>();
            recycleBin.put(classs, bin);
        }

        object.onRecycle();
        bin.add(object);
    }

    public static IRecyclable get(Class classs) {
        ArrayList<IRecyclable> bin = recycleBin.get(classs);

        if (bin == null) return null;
        if (bin.size() == 0) return null;

        return bin.remove(0);
    }
}
