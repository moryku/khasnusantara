package com.um.puak

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.akuarintar.ui.quiz.QuizActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        buttonBiodata.setOnClickListener {
            startActivity(Intent(this, DeveloperActivity::class.java))
        }
        buttonEvaluasi.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }
        buttonNasionalis.setOnClickListener {
            startActivity(Intent(this, NasionalActivity::class.java))
        }
        buttonPeta.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}