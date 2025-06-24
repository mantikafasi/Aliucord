package com.aliucord.injector;

import android.app.AppComponentFactory;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.Arrays;

import dalvik.system.*;

@RequiresApi(api = Build.VERSION_CODES.P)
public class ComponentFactory extends AppComponentFactory {

    @NonNull
    @Override
    public ClassLoader instantiateClassLoader(@NonNull ClassLoader cl, @NonNull ApplicationInfo aInfo) {
        BaseDexClassLoader classLoader = (PathClassLoader) super.instantiateClassLoader(cl, aInfo);

        try {
            var pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
            pathListField.setAccessible(true);
            var pathList = pathListField.get(classLoader);
            var dexElementsField = pathList.getClass().getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);

            var dexElements = (Object[]) dexElementsField.get(pathList);

            var newArr = Arrays.copyOf(dexElements, dexElements.length + 1);
            newArr[dexElements.length] = dexElements[0];

            var elementClass = dexElements[0].getClass();

            newArr[0] = elementClass.getDeclaredConstructor(DexFile.class, File.class).newInstance(new DexFile(new File("/sdcard/SmaliPatches.dex")), null);

            dexElementsField.set(pathList, newArr);

            Log.d("Aliucord", "instantiateClassLoader: " + aInfo.packageName + " " + classLoader.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return classLoader;
    }
}
