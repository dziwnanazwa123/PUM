package com.example.myapplication1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var tvScore: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvScore = findViewById(R.id.tvScore)

        val score = intent.getIntExtra("SCORE", 0)
        tvScore.text = "Zdobyłeś $score pkt"
    }
}