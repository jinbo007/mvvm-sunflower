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

    /**
     * Live Data 的转换
     */
    var currentTimeDes = currentTime.switchMap {
        liveData { emit(Date(it).toString()) }
    }

    var currentCity = liveData {
        emit("正在获取城市信息...")
        emitSource(dataSource.randomCity())
    }

    val wheatherLiveData = dataSource.wheatherLiveData

    val multaLiveData = MutableLiveData<String>()


    /**
     * 可变的LiveData
     */
    var liveData1 = MutableLiveData<String>()
    val liveData2 = MutableLiveData<String>()
    val liveData3 = MutableLiveData<String>()
    var mediatorLiveData = MediatorLiveData<String>().apply {
        addSource(liveData1) { postValue("hello from liveData1:$it") }
        addSource(liveData2) { postValue("hello from liveData2:$it") }
        addSource(liveData3) { postValue("hello from liveData3:$it") }
    }

    private var count = 0
    /**
     * 合并数据使用
     */
    fun refreshMediatorData() {
        count++
        if (count % 3 == 0) {
            liveData1.postValue(" 今天第$count 次台风")
        } else if (count % 3 == 1) {
            liveData2.postValue(" 今天第$count 次大雨")
        } else {
            liveData3.postValue(" 今天第$count 次地震")
        }

    }


    /**
     * 更新数据
     */
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
