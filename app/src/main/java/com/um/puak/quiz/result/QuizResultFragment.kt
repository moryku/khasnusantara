package com.akuarintar.ui.quiz.result

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.akuarintar.ui.quiz.QuizActivity
import com.um.puak.MainActivity
import com.um.puak.R
import kotlinx.android.synthetic.main.quiz_result_fragment.*

class QuizResultFragment : AppCompatActivity() {
    private var score: Int = 0
    private var categoryName = "RANDOM"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_result_fragment)
        score = intent.getIntExtra("score", 0)
        setupUI()
        setupButton()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI() {
//        if (Build.VERSION.SDK_INT > 24) {
//            val s = "Level Soal: <b>${categoryName.toUpperCase()}</b>"
//            tv_subText.text = Html.fromHtml(s, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
//        } else {
//            tv_subText.text = "Level Soal: ${categoryName.toUpperCase()}"
//        }
        chip_user.text = "$score benar"
        chip_opponent.text = "${10 - score} salah"

    }

    private fun setupButton() {
        btn_continue.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        btn_playAgain.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
            finish()
        }
    }
}
