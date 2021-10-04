/*
 * This file is part of Aliucord, an Android Discord client mod.
 * Copyright (c) 2021 Juby210 & Vendicated
 * Licensed under the Open Software License version 3.0
 */

package com.aliucord.patcher;

import com.aliucord.Logger;

import java.lang.reflect.Member;
import java.util.*;

import de.robv.android.xposed.DexposedBridge;
import de.robv.android.xposed.XC_MethodHook;

public class Patcher {
    public static Logger logger = new Logger("Patcher");
    private static final ClassLoader cl = Objects.requireNonNull(Patcher.class.getClassLoader());

    /**
     * Add a patch
     * @param forClass The full name of the class to patch (e.g. com.aliucord.patcher.Patcher)
     * @param methodName The name of the method
     * @param paramTypes The types of the parameters (e.g. int.class, String.class)
     * @param hook MethodHook
     * @return Unhook function
     */
    public static XC_MethodHook.Unhook addPatch(String forClass, String methodName, Class<?>[] paramTypes, XC_MethodHook hook) {
        try {
            return addPatch(cl.loadClass(forClass), methodName, paramTypes, hook);
        } catch (Throwable e) { Patcher.logger.error(e); }
        return null;
    }

    /**
     * Add a patch
     * @param clazz Class to patch
     * @param methodName The name of the method
     * @param paramTypes The types of the parameters (e.g. int.class, String.class)
     * @param hook MethodHook
     * @return Unhook function
     */
    public static XC_MethodHook.Unhook addPatch(Class<?> clazz, String methodName, Class<?>[] paramTypes, XC_MethodHook hook) {
        try {
            return addPatch(clazz.getDeclaredMethod(methodName, paramTypes), hook);
        } catch (Throwable e) { Patcher.logger.error(e); }
        return null;
    }

    /**
     * Add a patch
     * @param member The member (method, constructor) to patch
     * @param hook MethodHook
     * @return Unhook function
     */
    public static XC_MethodHook.Unhook addPatch(Member member, XC_MethodHook hook) {
        return DexposedBridge.hookMethod(member, hook);
    }
}
