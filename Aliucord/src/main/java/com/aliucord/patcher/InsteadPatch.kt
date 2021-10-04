/*
 * This file is part of Aliucord, an Android Discord client mod.
 * Copyright (c) 2021 Juby210 & Vendicated
 * Licensed under the Open Software License version 3.0
 */

package com.aliucord.patcher

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XC_MethodHook.MethodHookParam
import de.robv.android.xposed.XC_MethodReplacement

/**
 * Calls the patch block **instead of** this method.
 * @param callback Patch block to execute.
 * @see MethodHookParam
 */
class InsteadPatch(private val callback: Function1<MethodHookParam, Any?>) : XC_MethodHook() {
    override fun beforeHookedMethod(param: MethodHookParam) {
        try {
            param.result = callback.invoke(param)
        } catch (th: Throwable) {
            Patcher.logger.error(
                "Exception while replacing ${param.method.declaringClass.name}.${param.method.name}",
                th
            )
        }
    }
}
