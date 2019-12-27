package com.johngachihi.modi

import java.security.InvalidParameterException
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.valueParameters

class ModelInstantiator(private val propertyDataProvider: PropertyDataProvider) {

    fun createInstanceOf(clazz: KClass<*>): Any {
        return ModelClass(clazz).getInstance()
    }

    private inner class ModelClass(private val clazz: KClass<*>) {
        private val constructor get() = clazz.primaryConstructor!!

        init {
            if (!clazz.isData) throw InvalidParameterException("clazz must be a Data class")
        }

        fun getInstance(): Any = constructor.callBy(getArguments())

        private fun getArguments(): Map<KParameter, Any> =
            constructor.valueParameters.associateWith {kParameter ->
                propertyDataProvider.getDataFor(kParameter)
            }
    }
}