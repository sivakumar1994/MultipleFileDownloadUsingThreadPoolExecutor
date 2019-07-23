

package com.filesdownloadthreadpoolexecutor;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;


/**
 * This class is used to handle app resource related operations.
 *
 */

public class ResourceHelper {

    private ResourceHelper() {
        //Default constructor.
    }

    private static Context getApplicationContext() {
        return MyApplication.getMyAppInstance();
    }

    /**
     * Get the String resource.
     *
     * @param resourceId Id of the {@link StringRes}.
     * @return String, Corresponding string value.
     */
    public static String getStringResource(@StringRes int resourceId) {
        return getApplicationContext().getString(resourceId);
    }

    /**
     * Get the String resource.
     *
     * @param resoureId Id of the {@link StringRes}.
     * @param args      The format arguments that will be used for substitution.
     * @return String, Corresponding string value.
     */
    public static String getStringResource(@StringRes int resoureId, Object... args) {
        return getApplicationContext().getString(resoureId, args);
    }

    /**
     * Get the integer resource.
     *
     * @param resourceId Id of the {@link IntegerRes}.
     * @return int, Corresponding integer value.
     */
    public static int getIntegerResource(@IntegerRes int resourceId) {
        return getApplicationContext().getResources().getInteger(resourceId);
    }

    /**
     * Get the color resource.
     *
     * @param resourceId Id of the {@link ColorRes}.
     * @return Color code, Corresponding color value.
     */
    public static int getColor(@ColorRes int resourceId) {
        return getApplicationContext().getResources().getColor(resourceId);
    }

    /**
     * Get the drawable resource.
     *
     * @param resourceId Id of the {@link DrawableRes}.
     * @return Drawable, Corresponding drawable value.
     */
    public static Drawable getDrawable(@DrawableRes int resourceId) {
        return getApplicationContext().getResources().getDrawable(resourceId);
    }
}
