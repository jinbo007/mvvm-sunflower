package com.jinbo.sunflower

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jinbo.sunflower.livedata.LiveDataActivity
import com.jinbo.sunflower.viewmodel.ViewModelActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_livedata).setOnClickListener {
            startActivity(Intent(MainActivity@ this, LiveDataActivity::class.java))
        }
        findViewById<Button>(R.id.btn_view_model).setOnClickListener {
            startActivity(Intent(MainActivity@ this, ViewModelActivity::class.java))
        }
    }
}