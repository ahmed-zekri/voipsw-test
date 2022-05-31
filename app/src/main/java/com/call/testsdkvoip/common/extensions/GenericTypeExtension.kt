package com.call.testsdkvoip.common

import com.google.gson.Gson

fun <A> A.toJson(): String? {
    return Gson().toJson(this)
}