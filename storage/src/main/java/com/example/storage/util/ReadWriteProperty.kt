package com.example.storage.util

import kotlin.reflect.KProperty

/**
 * Created by ouyangshen on 2017/9/24.
 */
public interface ReadWriteProperty<in R, T> {

    public operator fun getValue(thisRef: R, property: KProperty<*>): T

    public operator fun setValue(thisRef: R, property: KProperty<*>, value: T)
}