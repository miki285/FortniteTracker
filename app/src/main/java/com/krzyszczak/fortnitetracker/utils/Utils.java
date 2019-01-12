package com.krzyszczak.fortnitetracker.utils;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

import io.reactivex.disposables.Disposable;

public class Utils {

    //https://stackoverflow.com/questions/3282390/add-floating-point-value-to-android-resources-values
    public static float getFloatResource(Context context, int resource) {
        TypedValue outValue = new TypedValue();
        context.getResources().getValue(resource, outValue, true);
        return outValue.getFloat();
    }

    public static void dispose(Disposable disposable) {
        if(disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public static void logError(String msg) {
        Log.e("error", msg);
    }
}
