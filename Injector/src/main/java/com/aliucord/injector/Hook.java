/*
 * This file is part of Aliucord, an Android Discord client mod.
 * Copyright (c) 2021 Juby210 & Vendicated
 * Licensed under the Open Software License version 3.0
 */

package com.aliucord.injector;

import android.os.Bundle;

import com.discord.app.AppActivity;
import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.*;

import java.lang.reflect.Method;

@HookClass(AppActivity.class)
public class Hook {
    private static boolean initialized = false;

    @HookMethodBackup("onCreate")
    @MethodParams(Bundle.class)
    static Method onCreateBackup;

    @HookMethod("onCreate")
    @MethodParams(Bundle.class)
    public static void onCreate(AppActivity thisObject, Bundle bundle) throws Throwable {
        if (!initialized) {
            Injector.init((AppActivity) thisObject);
            initialized = true;
        }
        SandHook.callOriginByBackup(onCreateBackup, thisObject, bundle);
    }
}
