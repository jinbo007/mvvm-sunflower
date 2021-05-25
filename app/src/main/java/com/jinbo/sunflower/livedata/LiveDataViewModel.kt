package com.jinbo.sunflower.livedata

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.util.*

/**
 * @author   houjinbo
 * @date  2021/5/21
 */

class LiveDataViewModel(
    private val dataSource: DataSource
) : ViewModel() {
    var currentTime = dataSource.getCurrentTime()

    var currentTimeDes = currentTime.switchMap {
        liveData { emit(Date(it).toString()) }
    }

    var currentCity = liveData {
        emit("正在获取城市信息...")
        emitSource(dataSource.randomCity())
    }

    val cacheValue = dataSource.cachedData

    val multaLiveData = MutableLiveData<String>()

    fun refresh() {
        viewModelScope.launch {
            dataSource.fetchNewWheather()
        }
    }

}

object LiveDataVmFactory : ViewModelProvider.Factory {
    private val dataSource = LiveDataDataSource()
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LiveDataViewModel(dataSource) as T
    }

}
