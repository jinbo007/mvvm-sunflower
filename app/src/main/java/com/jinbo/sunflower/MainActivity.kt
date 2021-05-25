package com.jinbo.sunflower

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jinbo.sunflower.livedata.LiveDataActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_livedata).setOnClickListener {
            startActivity(Intent(MainActivity@ this, LiveDataActivity::class.java))
        }
    }
}