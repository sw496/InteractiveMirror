package com.morristaedt.mirror.configuration;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.morristaedt.mirror.BuildConfig;


import java.util.Date;

/**
 * Created by HannahMitt on 9/26/15.
 */
public class ConfigurationSettings {

    /**
     * Hardcode on to enable features outside of their regularly scheduled hours
     */
    private static final boolean DEMO_MODE = false;
    private static final String PREFS_MIRROR = "MirrorPrefs";
    private static final String TEXT_COLOR = "text_color";
    private static final String USE_MOOD_DETECTION = "mood_detection";
    private static final String SHOW_XKCD = "xkcd";
    private static final String INVERT_XKCD = "invert_xkcd";

    @NonNull
    private SharedPreferences mSharedPrefs;
    private boolean mShowMoodDetection;
    private boolean mShowXKCD;
    private boolean mInvertXKCD;
    private int mTextColor;

    public ConfigurationSettings(Context context) {
        mSharedPrefs = context.getSharedPreferences(PREFS_MIRROR, Context.MODE_PRIVATE);
        readPrefs();
    }

    private void readPrefs() {

        mTextColor = mSharedPrefs.getInt(TEXT_COLOR, Color.WHITE);
        mShowMoodDetection = mSharedPrefs.getBoolean(USE_MOOD_DETECTION, false);
        mShowXKCD = mSharedPrefs.getBoolean(SHOW_XKCD, false);
        mInvertXKCD = mSharedPrefs.getBoolean(INVERT_XKCD, false);

    }

    public void setTextColorRed(int red){
        setTextColor(Color.rgb(red, Color.green(mTextColor), Color.blue(mTextColor)));
    }

    public void setTextColorGreen(int green){
        setTextColor(Color.rgb(Color.red(mTextColor), green, Color.blue(mTextColor)));
    }

    public void setTextColorBlue(int blue){
        setTextColor(Color.rgb(Color.red(mTextColor), Color.green(mTextColor), blue));
    }

    public void setTextColor(int color){
        mTextColor = color;
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.putInt(TEXT_COLOR, color);
        editor.apply();
    }



    public void setShowMoodDetection(boolean show) {
        mShowMoodDetection = show;
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.putBoolean(USE_MOOD_DETECTION, show);
        editor.apply();
    }




    public void setXKCDPreference(boolean showXKCD, boolean invertXKCDColors) {
        mShowXKCD = showXKCD;
        mInvertXKCD = invertXKCDColors;
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.putBoolean(SHOW_XKCD, showXKCD);
        editor.putBoolean(INVERT_XKCD, invertXKCDColors);
        editor.apply();
    }


    public int getTextColor() {
        return mTextColor;
    }


    public boolean showMoodDetection() {
        return mShowMoodDetection;
    }



    public boolean showXKCD() {
        return mShowXKCD;
    }

    public boolean invertXKCD() {
        return mInvertXKCD;
    }





    public static boolean isDebugBuild() {
        return BuildConfig.DEBUG;
    }

    /**
     * Whether we're ignoring timing rules for features
     *
     * @return
     */
    public static boolean isDemoMode() {
        return DEMO_MODE;
    }
}
