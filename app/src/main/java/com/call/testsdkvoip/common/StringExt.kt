package com.call.testsdkvoip.common

import com.google.gson.Gson

fun <A> String.fromJson(type: Class<A>): A {
    return Gson().fromJson(this, type)
}

