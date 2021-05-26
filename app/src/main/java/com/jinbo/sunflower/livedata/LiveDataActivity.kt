package com.jinbo.sunflower.livedata

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jinbo.sunflower.R
import com.jinbo.sunflower.databinding.ActivityLivedataBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext

/**
 * @author   houjinbo
 * @date  2021/5/21
 */

class LiveDataActivity : AppCompatActivity() {

//    private var laterinit tv_city :TextView;

    private lateinit var tv_city: TextView
    private lateinit var tv_manual_observe: TextView

    private val viewModel: LiveDataViewModel by viewModels { LiveDataVmFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLivedataBinding>(
            this,
            R.layout.activity_livedata
        )

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel


        initManualLiveData()//不用DataBinding，代码形式绑定

        initMutableLiveData()// 验证mutalbeLiveData  可以直接赋值更新的 LiveData

        initMediatorLiveData()//测试MediatorLiveData 组合形式LiveData


    }

    /**
     * 不用DataBinding，代码形式绑定
     */
    private fun initManualLiveData() {

        //手动绑定 observer
        tv_city = findViewById(R.id.tv_city)
        var observer = Observer<String> {
            tv_city.text = "我是手动观察的$it"
        }
        viewModel.currentCity.observe(this, observer)
    }

    /**
     * 验证mutalbeLiveData  可变的LiveData
     */
    private fun initMutableLiveData() {

        //不使用dataBinding手动修改liveData数值, mutableLiveData支持
        tv_manual_observe = findViewById(R.id.tv_manual_observe)
        val button = findViewById<Button>(R.id.btn_manul_change)
        var telVersion = 0
        button.setOnClickListener {
            viewModel.multaLiveData.value = "不使用databinding 收到的点击次数click times ${telVersion++}"
        }

        viewModel.multaLiveData.observe(this,
            Observer { t -> tv_manual_observe.text = t })
    }

    /**
     * 测试MediatorLiveData 积木式拼装LiveData
     */
    private fun initMediatorLiveData() {
        var tv_mediator_live_data = findViewById<TextView>(R.id.tv_mediator_live_data)
        viewModel.mediatorLiveData.observe(this,
            Observer { t -> tv_mediator_live_data.text = t+"hello" })

        val button = findViewById<Button>(R.id.btn_mediator_live_data)
        button.setOnClickListener {
            viewModel.refreshMediatorData()
        }
    }

}
