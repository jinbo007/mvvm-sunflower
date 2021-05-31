package com.jinbo.sunflower.viewmodel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jinbo.sunflower.R

/**
 * @author   houjinbo
 * @date  2021/5/26
 */
class ViewModelActivity : AppCompatActivity() {

    val viewModle: MyViewModel by viewModels()

    lateinit var tv_user_list: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel)
        tv_user_list = findViewById(R.id.tv_user_list)
        val users = viewModle.getUsers();
        users.observe(this, Observer {
            tv_user_list.text = it.toString()
        })
//
        findViewById<Button>(R.id.btn_load_user).setOnClickListener {
            viewModle.updateUser()
        }
    }
}