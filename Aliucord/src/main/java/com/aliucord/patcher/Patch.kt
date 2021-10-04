/*
 * This file is part of Aliucord, an Android Discord client mod.
 * Copyright (c) 2021 Juby210 & Vendicated
 * Licensed under the Open Software License version 3.0
 */

package com.aliucord.patcher

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XC_MethodHook.MethodHookParam
import rx.functions.Action1

/**
 * Calls the patch block **after** this method.
 * @param callback Patch block to execute.
 * @see MethodHookParam
 */
class Patch(private val callback: Action1<MethodHookParam>) : XC_MethodHook() {
    override fun afterHookedMethod(param: MethodHookParam) {
        try {
            callback.call(param)
        } catch (th: Throwable) {
            Patcher.logger.error(
                "Exception while hooking ${param.method.declaringClass.name}.${param.method.name}",
                th
            )
        }
    }
}