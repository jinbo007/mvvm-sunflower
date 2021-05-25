package com.jinbo.sunflower.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

/**
 * @author   houjinbo
 * @date  2021/5/21
 */

class LiveDataDataSource : DataSource {
    override fun getCurrentTime(): LiveData<Long> = liveData {
        while (true) {
            emit(System.currentTimeMillis())//更新当前时间
            delay(1000)//协程内部delay函数
        }
    }

    private val weatherConditions = listOf("北京", "上海", "深圳")

    override fun randomCity(): LiveData<String> = liveData {
        while (true) {

            emit(weatherConditions[(weatherConditions.indices).random()])
            delay(2000)
        }
    }

    private val _cacheData = MutableLiveData("今天天气不错")

    override val cachedData: LiveData<String> = _cacheData

    override suspend fun fetchNewWheather() {
        _cacheData.value = "稍等，主人，正在看现在天气..."
        _cacheData.value = fetchLastedWeather()

    }
    private val weatherListMock = listOf("今天是好天气，真不错", "今天可能有雨哦，记得带伞", "今天没太阳，不用怕晒黑啦")

    private suspend fun fetchLastedWeather(): String = withContext(Dispatchers.IO) {
        delay(1000)
        weatherListMock[weatherListMock.indices.random()]
    }
}


interface DataSource {

    fun getCurrentTime(): LiveData<Long>

    fun randomCity(): LiveData<String>

    val cachedData: LiveData<String>

    suspend fun fetchNewWheather()

}