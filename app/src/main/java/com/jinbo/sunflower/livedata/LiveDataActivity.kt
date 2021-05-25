package com.jinbo.sunflower.livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jinbo.sunflower.R
import com.jinbo.sunflower.databinding.ActivityLivedataBinding

/**
 * @author   houjinbo
 * @date  2021/5/21
 */

class LiveDataActivity : AppCompatActivity() {

    private val viewModel: LiveDataViewModel by viewModels { LiveDataVmFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLivedataBinding>(
            this,
            R.layout.activity_livedata
        )

        binding.lifecycleOwner = this

        binding.viewmodel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
