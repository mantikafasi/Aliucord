package com.aliucord.api;

import android.content.Context;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.res.ResourcesCompat;

import com.aliucord.Utils;

public class ResourcesExt extends Resources {

    String packageName;

    /**
     * Create a new Resources object on top of an existing set of assets in an
     * AssetManager.
     *
     * @param assets  Previously created AssetManager.
     * @param metrics Current display metrics to consider when
     *                selecting/computing resource values.
     * @param config  Desired device configuration to consider when
     *                selecting/computing resource values (optional).
     * @deprecated Resources should not be constructed by apps.
     * @param packageName The name of the package this Resources object should be associated with.
     * See {@link Context#createConfigurationContext(Configuration)}.
     */
    public ResourcesExt(AssetManager assets, DisplayMetrics metrics, Configuration config, String packageName) {
        super(assets, metrics, config);
        this.packageName = packageName;
    }

    public View inflate(String defType, String name, ViewGroup root, boolean attachToRoot) {
        var xml = getLayout(getIdentifier(name, defType, packageName));

        return Utils.appActivity.getLayoutInflater().inflate(xml, root, attachToRoot);
    };

    public int getIdentifier(String name, String defType) {
        return getIdentifier(name, defType, packageName);
    };

    public Drawable getDrawable(String name) {
        return ResourcesCompat.getDrawable(this, getIdentifier(name, "drawable"), null );
    };
}
