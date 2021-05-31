package com.jinbo.sunflower.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author   houjinbo
 * @date  2021/5/26
 */
class MyViewModel : ViewModel() {

    private var userList: MutableList<User> = ArrayList()

    init {
        userList.add(User("小明", 19))
        userList.add(User("小花", 25))
        userList.add(User("小利", 37))
    }

    /**
     * notice it mutablLiveData没有set方法，必须设置更新项
     */
    private val users: MutableLiveData<User> by lazy {
        MutableLiveData<User>().also {
            it.postValue(userList[0]);
        }
    }

    fun getUsers(): LiveData<User> {
        return users
    }


    /**
     * 查询用户信息
     */
    fun updateUser() {
        users.postValue(userList.random())
    }

}