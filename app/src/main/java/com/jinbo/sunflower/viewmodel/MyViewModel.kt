package com.jinbo.sunflower.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author   houjinbo
 * @date  2021/5/26
 */
class MyViewModel : ViewModel() {
    /**
     * notice it mutablLiveData没有set方法，必须设置更新项
     */
    private val users: MutableLiveData<List<User>> = MutableLiveData<List<User>>()

    fun getUsers(): LiveData<List<User>> {
        return users
    }

    /**
     * 查询用户信息
     */
    fun queryUsers() {
//        viewModelScope.launch {
        var userList: MutableList<User> = ArrayList()
        userList.add(User("小明", 18))
        userList.add(User("小黃", 24))
        userList.add(User("小丽", 22))
        users.postValue(userList)
    }

}