package com.johngachihi.modi

import kotlin.reflect.KParameter

interface PropertyDataProvider {
//    fun getDataFor(mProperty: MProperty): Any
    fun getDataFor(parameter: KParameter): Any
}