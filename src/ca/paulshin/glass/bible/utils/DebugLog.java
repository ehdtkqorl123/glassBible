package ca.paulshin.glass.bible.utils;

import android.util.Log;

/**
 * Simple debugging utils.
 */
public class DebugLog {
    private static final String DEFAULT_TAG = "log";
    private static long startTime;
    private static long previousTime;

    public static void d(Object text) {
        Log.d(DEFAULT_TAG, text.toString());
    }

    public static void e(Object text) {
        Log.e(DEFAULT_TAG, text.toString());
    }

    public static void i(Object text) {
        Log.i(DEFAULT_TAG, text.toString());
    }

    public static void t(String text) {
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
            previousTime = startTime;
            d("------------------------------ starting time tracking: " + text);
        } else {
            long currentTime = System.currentTimeMillis();
            d(text + ": +" + (currentTime - previousTime) + " ms (total " + (currentTime - startTime) + " ms)");
            previousTime = currentTime;
        }
    }

    public static void w(Object text) {
        Log.w(DEFAULT_TAG, text.toString());
    }
}
