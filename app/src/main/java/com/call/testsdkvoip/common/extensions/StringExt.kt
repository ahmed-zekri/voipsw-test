package com.call.testsdkvoip.common.extensions

import android.text.TextUtils
import com.google.gson.Gson

fun <A> String.fromJson(type: Class<A>): A {
    return Gson().fromJson(this, type)
}
fun String.isEmpty(): Boolean {
    return TextUtils.isEmpty(this.trim { it <= ' ' })
}

