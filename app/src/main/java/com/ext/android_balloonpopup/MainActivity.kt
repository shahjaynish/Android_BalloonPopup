package com.ext.android_balloonpopup

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ext.android_balloonpopup.databinding.ActivityMainBinding
import com.ext.balloonpopup.ArrowPosition
import com.ext.balloonpopup.BalloonPopup

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnShowTooltip.setOnClickListener {
            BalloonPopup(this)
                .setText("Hello from Balloon Library 🎈")
                .setArrowPosition(ArrowPosition.RIGHT)
                .setBackgroundColor(Color.parseColor("#6200EE"))
                .setTextColor(Color.YELLOW)
                .show(binding.btnShowTooltip)
        }
    }
}