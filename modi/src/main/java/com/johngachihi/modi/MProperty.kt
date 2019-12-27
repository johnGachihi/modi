package com.johngachihi.modi

import kotlin.reflect.KParameter

class MProperty (private val kParameter: KParameter) {
    val name get() = kParameter.name
    val type get() = kParameter.type
}