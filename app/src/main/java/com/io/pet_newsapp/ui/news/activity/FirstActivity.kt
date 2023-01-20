package com.io.pet_newsapp.ui.news.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.io.pet_newsapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startDelay()
    }

    private fun startDelay() {

        val btn: Button = findViewById(R.id.btn_gogo)
        lifecycleScope.launch {
            delay(10_000L)

            btn.setOnClickListener {
                startActivity(Intent(this@FirstActivity, MainActivity::class.java))

            }

        }
    }
}