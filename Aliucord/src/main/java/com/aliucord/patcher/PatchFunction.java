/*
 * Copyright (c) 2021 Juby210
 * Licensed under the Open Software License version 3.0
 */

package com.aliucord.patcher;

import java.util.List;

import kotlin.jvm.functions.Function3;

@Deprecated
public interface PatchFunction extends Function3<Object, List<Object>, Object, Object> {}