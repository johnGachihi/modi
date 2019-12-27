package com.johngachihi.modi

import ch.tutteli.atrium.api.fluent.en_GB.feature
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
import org.junit.Before
import org.junit.Test
import java.security.InvalidParameterException
import kotlin.reflect.KParameter

class ModelInstantiatorTest {

    lateinit var modi: ModelInstantiator

    @Before
    fun setup() {
        modi = ModelInstantiator(FakePropertyDataProvider())
    }

    @Test
    fun testGetModel() {
        val modiObject: FakeModel = modi.createInstanceOf(FakeModel::class) as FakeModel

        expect(modiObject) {
            feature { f(modiObject::paramA) }.toBe("paramA")
            feature { f(modiObject::paramB) }.toBe("paramB")
        }
    }

    @Test
    fun testGetModel_throwsException() {
        expect { modi.createInstanceOf(NotADataClass::class) }
            .toThrow<InvalidParameterException>()
    }
}


class FakePropertyDataProvider: PropertyDataProvider {
    override fun getDataFor(parameter: KParameter): Any {
        return parameter.name!!
    }
}

data class FakeModel(
    val paramA: String,
    val paramB: String
)

class NotADataClass