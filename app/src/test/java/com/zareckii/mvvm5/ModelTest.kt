package com.zareckii.mvvm5

import org.junit.Assert.*
import org.junit.Test

class ModelTest {

    private class TestCallback: TextCallback {
        var text = ""
        override fun updateText(str: String) {
            text = str
        }
    }

    private class TestDataSource : DataSource {
        private var int = Int.MAX_VALUE
        override fun saveInt(key: String, value: Int) {
            int = value
        }
        override fun getInt(key: String) = int
    }

    @Test
    fun test_start_with_saved_value() {
        val testDataSource = TestDataSource()
        val timeTicker = TestTimeTicker()
        val model = Model(testDataSource, timeTicker)
        val callback = TestCallback()
        testDataSource.saveInt("", 5)
        model.start(callback)
        timeTicker.tick(1)
        val actual = callback.text
        val expected = "6"
        assertEquals(expected, actual)
    }

    @Test
    fun test_stop_after_2_seconds() {
        val testDataSource = TestDataSource()
        val timeTicker = TestTimeTicker()
        val model = Model(testDataSource, timeTicker)
        val callback = TestCallback()
        testDataSource.saveInt("", 0)
        model.start(callback)
        timeTicker.tick(2)
        val actual = callback.text
        val expected = "2"
        assertEquals(expected, actual)

        model.stop()
        val savedCountActual = testDataSource.getInt("")
        val savedCountExpected = 2
        assertEquals(savedCountExpected, savedCountActual)
    }

    @Test
    fun test_start_after_stop() {
        val testDataSource = TestDataSource()
        val timeTicker = TestTimeTicker()
        val model = Model(testDataSource, timeTicker)
        val callback = TestCallback()
        testDataSource.saveInt("", 10)
        model.start(callback)
        timeTicker.tick(2)
        val actual = callback.text
        val expected = "12"
        assertEquals(expected, actual)

        model.stop()
        val savedCountActual = testDataSource.getInt("")
        val savedCountExpected = 12
        assertEquals(savedCountExpected, savedCountActual)

        model.start(callback)
        timeTicker.tick(3)
        val actualText = callback.text
        val expectedText = "15"
        assertEquals(actualText, expectedText)


    }

}