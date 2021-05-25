package com.jinbo.sunflower.livedata

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
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
        tv_city = findViewById(R.id.tv_city)

        var observer = Observer<String> {
            tv_city.text = "我是手动观察的$it"
        }

        viewModel.currentCity.observe(this, observer)

        binding.lifecycleOwner = this

        binding.viewmodel = viewModel
    }

}
