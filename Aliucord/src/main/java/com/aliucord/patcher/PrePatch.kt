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
 * Calls the patch block **before** this method.
 * @param callback Patch block to execute.
 * @see MethodHookParam
 */
class PrePatch(private val callback: Action1<MethodHookParam>) : XC_MethodHook() {
    override fun beforeHookedMethod(param: MethodHookParam) {
        try {
            callback.call(param)
        } catch (th: Throwable) {
            Patcher.logger.error(
                "Exception while preHooking ${param.method.declaringClass.name}.${param.method.name}",
                th
            )
        }
    }
}