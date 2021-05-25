package com.jinbo.sunflower.livedata

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.jinbo.sunflower.R
import com.jinbo.sunflower.databinding.ActivityLivedataBinding

/**
 * @author   houjinbo
 * @date  2021/5/21
 */

class LiveDataActivity : AppCompatActivity() {

//    private var laterinit tv_city :TextView;

    private lateinit var tv_city: TextView

    private val viewModel: LiveDataViewModel by viewModels { LiveDataVmFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLivedataBinding>(
            this,
            R.layout.activity_livedata
        )


        //手动绑定 observer
        tv_city = findViewById(R.id.tv_city)
        var observer = Observer<String> {
            tv_city.text = "我是手动观察的$it"
        }
        viewModel.currentCity.observe(this, observer)

        binding.lifecycleOwner = this
        binding.viewmodel = viewModel


        //手动修改liveData数值, mutableLiveData支持
        val button = findViewById<Button>(R.id.btn_manul_change)
        var telVersion = 0
        button.setOnClickListener {
            viewModel.multaLiveData.value = "click times ${telVersion++}"
        }

        viewModel.multaLiveData.observe(this,
            Observer { t -> findViewById<Button>(R.id.btn_manul_change).text = t })
    }

}
