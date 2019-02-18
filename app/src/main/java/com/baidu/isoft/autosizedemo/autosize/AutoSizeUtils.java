package com.baidu.isoft.autosizedemo.autosize;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by v_liwei10 on 2019/2/18.
 */

public class AutoSizeUtils {
    public static final String TAG = AutoSizeUtils.class.getSimpleName();
    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;


    private static class Holder {
        private static AutoSizeUtils singleton = new AutoSizeUtils();
    }

    private AutoSizeUtils(){}

    public static AutoSizeUtils getSingleton(){
        return Holder.singleton;
    }


    public void setCustomDensity(@NonNull final Application application, @NonNull Activity activity){
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if(sNoncompatDensity == 0){
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if(newConfig!=null&&newConfig.fontScale>0){
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity = appDisplayMetrics.widthPixels / (float)360;
        final float targetScaledDensity = targetDensity*(sNoncompatScaledDensity/sNoncompatDensity);
        final int targetDensityDpi = (int) (targetDensity*160);
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;

        Log.e(TAG, "setCustomDensity: "+"density=="+targetDensity+"scaledDensity=="+targetScaledDensity+"densityDpi"
                + "=="+targetDensityDpi+"widthPixels=="+appDisplayMetrics.widthPixels );
    }


}
