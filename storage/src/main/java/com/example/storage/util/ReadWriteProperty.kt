package com.example.storage.util

import kotlin.reflect.KProperty

/**
 * Created by ouyangshen on 2017/9/24.
 */
interface ReadWriteProperty<in R, T> {

    operator fun getValue(thisRef: R, property: KProperty<*>): T

    operator fun setValue(thisRef: R, property: KProperty<*>, value: T)
}