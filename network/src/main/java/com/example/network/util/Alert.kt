package com.example.network.util

import android.content.Context
import org.jetbrains.anko.AlertDialogBuilder

/**
 * Created by ouyangshen on 2017/10/6.
 */
//Anko自带的alert只支持String类型的文本，不支持富文本的CharSequence类型，
//故此处重写alert方法，使之支持可变字符串SpannableString
fun Context.alert(
        message: CharSequence,
        title: String? = null,
        init: (AlertDialogBuilder.() -> Unit)? = null
) = AlertDialogBuilder(this).apply {
    if (title != null) title(title)
    message(message)
    if (init != null) init()
}