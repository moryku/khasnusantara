package com.akuarintar.ui.quiz

import android.animation.Animator
import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.akuarintar.data.model.question.Question
import com.akuarintar.ui.quiz.result.QuizResultFragment
import com.akuarintar.util.custom_dialogs.customAlertDialog
import kotlinx.android.synthetic.main.quiz_fragment.*
import com.um.puak.R
import com.um.puak.quiz.model.Quiz
import java.util.*
import kotlin.collections.ArrayList

class QuizActivity : AppCompatActivity() {

    companion object {
        fun newInstance() = QuizActivity()
    }

    private val handler = Handler()
    private var correctAnswers: Int = 0
    private lateinit var customDialog: AlertDialog
    private lateinit var categoryName: String
    private var position: Int = 0;
    private var animation: ObjectAnimator? = null
    var questionNoList = listOf<TextView>()
    var onClickOption = false

    var dataQuestion: MutableList<Question> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_fragment)

        generateQuestion()
        questionNoList = listOf(
                tv_i1!!,
                tv_i2!!,
                tv_i3!!,
                tv_i4!!,
                tv_i5!!,
                tv_i6!!,
                tv_i7!!,
                tv_i8!!,
                tv_i9!!,
                tv_i10!!
        )

        customDialog = customAlertDialog(
                this@QuizActivity,
                "Apakah anda yakin mau keluar?",
                {
                    startActivity(Intent(this, QuizResultFragment::class.java).putExtra("score", correctAnswers))
                    finish()
                },
                "Ya",
                {},
                "Tidak",
                false
        )
        setupListener()
        subscribeUI()
        tvQuestion.setMovementMethod(ScrollingMovementMethod());
    }



    fun setupListener(){
        card_option1.setOnClickListener {
            optionClicked(
                tv_option1,
                tv_a,
                card_option1,
                dataQuestion.get(position).answers.get(0),
                dataQuestion.get(position).correct,
                position
            )
        }
        card_option2.setOnClickListener {
            optionClicked(
                tv_option2,
                tv_b,
                card_option2,
                dataQuestion.get(position).answers.get(1),
                dataQuestion.get(position).correct,
                position
            )
        }
        card_option3.setOnClickListener {
            optionClicked(
                tv_option3,
                tv_c,
                card_option3,
                dataQuestion.get(position).answers.get(2),
                dataQuestion.get(position).correct,
                position
            )
        }
        card_option4.setOnClickListener {
            optionClicked(
                tv_option4,
                tv_d,
                card_option4,
                dataQuestion.get(position).answers.get(3),
                dataQuestion.get(position).correct,
                position
            )
        }

        //Quit Button
        btn_quit.setOnClickListener {
            btnQuitClicked()
        }
    }

    private fun quizOptionClicked(position: Int, optionSelected: Quiz?) {
        try {
            if (optionSelected == dataQuestion.get(position).correct)
                correctAnswers++
            if (position != dataQuestion.size - 1) {
                handler.postDelayed({
                    this.position++
                    subscribeUI()
                }, 300)
            } else {
                startActivity(Intent(this, QuizResultFragment::class.java).putExtra("score", correctAnswers))
                finish()
            }
        } catch (e: Exception) {

        }
    }

    private fun btnQuitClicked() {
        customDialog.show()
    }


    private fun optionClicked(
        tvOption: TextView,
        tvOptionName: TextView,
        optionCard: CardView,
        selectedOption: Quiz,
        correctAnswer: Quiz,
        position: Int
    ) {
        onClickOption = true;
        animation?.cancel()
        tvOption.setTextColor(resources.getColor(R.color.white))
        tvOptionName.setTextColor(resources.getColor(R.color.white))
        if (correctAnswer == selectedOption)
            optionCard.backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.lightGreen))
        else
            optionCard.backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.darkRed1))
        quizOptionClicked(position, selectedOption)
        onClickOption = false;
    }

    fun subscribeUI(){
        if (dataQuestion.size > 0) {
            reset()
            var question = dataQuestion.get(position)

            val randomValues = (0..1).random()

            //Progress Bar
            if (animation == null) {
                animation = ObjectAnimator.ofInt(pbTimer, "progress", 0, 500);
                animation?.duration = 20000
                animation?.addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        if (!onClickOption)
                            quizOptionClicked(position, null)
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationStart(animation: Animator?) {
                    }
                })
            }
            animation?.start()


            //Question Number
            questionNoList[position].setBackgroundResource(R.drawable.round_solid)
            questionNoList[position].backgroundTintList =
                ColorStateList.valueOf(resources.getColor(R.color.yellow))
            questionNoList[position].setTextColor(resources.getColor(R.color.white))

            //Question Category
            tv_categoryName.text = "Pilihan Ganda"

            //Question
            if (question.questionText != null) {
                tvQuestion.visibility = View.VISIBLE
                ivQuestionImage.visibility = View.GONE
                tvQuestion.text = question.questionText
            }

            if (question.questionImage != 0){
                ivQuestionImage.visibility = View.VISIBLE
                ivQuestionImage.setImageResource(question.questionImage)
            }

            //Options
            if (question.answers!![0].text != null) {
                tv_option1.visibility = View.VISIBLE
                iv_option1.visibility = View.GONE
                tv_option1.text = question.answers!![0].text
            } else {
                tv_option1.visibility = View.GONE
                iv_option1.visibility = View.VISIBLE
                iv_option1.setImageResource(question.answers!![0].image)
            }

            if (question.answers!![1].text != null) {
                tv_option2.visibility = View.VISIBLE
                iv_option2.visibility = View.GONE
                tv_option2.text = question.answers!![1].text
            } else {
                tv_option2.visibility = View.GONE
                iv_option2.visibility = View.VISIBLE
                iv_option2.setImageResource(question.answers!![1].image)
            }

            if (question.answers!![2].text != null) {
                tv_option3.visibility = View.VISIBLE
                iv_option3.visibility = View.GONE
                tv_option3.text = question.answers!![2].text
            } else {
                tv_option3.visibility = View.GONE
                iv_option3.visibility = View.VISIBLE
                iv_option3.setImageResource(question.answers!![2].image)
            }

            if (question.answers!![3].text != null) {
                tv_option4.visibility = View.VISIBLE
                iv_option4.visibility = View.GONE
                tv_option4.text = question.answers!![3].text
            } else {
                tv_option4.visibility = View.GONE
                iv_option4.visibility = View.VISIBLE
                iv_option4.setImageResource(question.answers!![3].image)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    fun reset(){
        card_option1.backgroundTintList = null
        card_option2.backgroundTintList = null
        card_option3.backgroundTintList = null
        card_option4.backgroundTintList = null
        tv_option1.setTextColor(resources.getColor(R.color.lightGray))
        tv_option2.setTextColor(resources.getColor(R.color.lightGray))
        tv_option3.setTextColor(resources.getColor(R.color.lightGray))
        tv_option4.setTextColor(resources.getColor(R.color.lightGray))
        tv_a.setTextColor(resources.getColor(R.color.lightGray))
        tv_b.setTextColor(resources.getColor(R.color.lightGray))
        tv_c.setTextColor(resources.getColor(R.color.lightGray))
        tv_d.setTextColor(resources.getColor(R.color.lightGray))
    }

    fun generateQuestion() {
        // soal1
        var dataPilihan:MutableList<Quiz> = ArrayList()
        var pilihanA = Quiz()
        pilihanA.text = "Jawa Tengah"
        dataPilihan.add(pilihanA)
        var pilihanB = Quiz()
        pilihanB.text = "Jawa Barat"
        dataPilihan.add(pilihanB)
        var pilihanC = Quiz()
        pilihanC.text = "Jawa Timur"
        dataPilihan.add(pilihanC)
        var pilihanD = Quiz()
        pilihanD.text = "Yogyakarta"
        dataPilihan.add(pilihanD)
        var question = Question(1, dataPilihan, pilihanD, 0, "Tari Gambyong merupakan tari tradisional dari provinsi.....")
        dataQuestion.add(question)

        // soal 2
        dataPilihan = ArrayList()
        pilihanA = Quiz()
        pilihanA.text = "Suku Toraja"
        dataPilihan.add(pilihanA)
        pilihanB = Quiz()
        pilihanB.text = "Suku Asmat"
        dataPilihan.add(pilihanB)
        pilihanC = Quiz()
        pilihanC.text = "Suku Sunda"
        dataPilihan.add(pilihanC)
        pilihanD = Quiz()
        pilihanD.text = "Suku Betawi"
        dataPilihan.add(pilihanD)
        question = Question(2, dataPilihan, pilihanA, 0, "Salah satu suku yang berasal dari provinsi Sulawesi Selatan")
        dataQuestion.add(question)

        // soal 3
        dataPilihan = ArrayList()
        pilihanA = Quiz()
        pilihanA.text = "Sumatera Utara"
        dataPilihan.add(pilihanA)
        pilihanB = Quiz()
        pilihanB.text = "Sumatera Selatan"
        dataPilihan.add(pilihanB)
        pilihanC = Quiz()
        pilihanC.text = "Sumatera Barat"
        dataPilihan.add(pilihanC)
        pilihanD = Quiz()
        pilihanD.text = "Bandar Lampung"
        dataPilihan.add(pilihanD)
        question = Question(3, dataPilihan, pilihanC, R.drawable.soal_3_tari_piring, "Perhatikan gambar di atas, Tari piring merupakan tarian tradisional yang berasal dari provinsi.....\n")
        dataQuestion.add(question)

        // soal 4
        dataPilihan = ArrayList()
        pilihanA = Quiz()
        pilihanA.text = "Bika"
        dataPilihan.add(pilihanA)
        pilihanB = Quiz()
        pilihanB.text = "Papeda"
        dataPilihan.add(pilihanB)
        pilihanC = Quiz()
        pilihanC.text = "Rujak Cingur"
        dataPilihan.add(pilihanC)
        pilihanD = Quiz()
        pilihanD.text = "Barobok"
        dataPilihan.add(pilihanD)
        question = Question(4, dataPilihan, pilihanB, 0, "Makanan Tradisional Suku Dani adalah....")
        dataQuestion.add(question)

        dataPilihan = ArrayList()
        pilihanA = Quiz()
        pilihanA.text = "Jawa Barat"
        dataPilihan.add(pilihanA)
        pilihanB = Quiz()
        pilihanB.text = "Jawa tengah"
        dataPilihan.add(pilihanB)
        pilihanC = Quiz()
        pilihanC.text = "Jakarta"
        dataPilihan.add(pilihanC)
        pilihanD = Quiz()
        pilihanD.text = "Papua"
        dataPilihan.add(pilihanD)
        question = Question(5, dataPilihan, pilihanA, 0, "Lagu daerah dengan judul Tokecang berasal dari  berasal dari daerah.....")
        dataQuestion.add(question)

        dataPilihan = ArrayList()
        pilihanA = Quiz()
        pilihanA.image = R.drawable.soal_5_pil_1
        dataPilihan.add(pilihanA)
        pilihanB = Quiz()
        pilihanB.image = R.drawable.soal_5_pil_2
        dataPilihan.add(pilihanB)
        pilihanC = Quiz()
        pilihanC.image = R.drawable.soal_5_pil_3
        dataPilihan.add(pilihanC)
        pilihanD = Quiz()
        pilihanD.image = R.drawable.soal_5_pil_4
        dataPilihan.add(pilihanD)
        question = Question(6, dataPilihan, pilihanD, 0, "Berikut ini adalah alat musik tradisional dari Provinsi  Betawi..")
        dataQuestion.add(question)

        dataPilihan = ArrayList()
        pilihanA = Quiz()
        pilihanA.text = "Memberi pakaian suku Dani dan melarangnya untuk menggunakan pakaian adat yang terbuka."
        dataPilihan.add(pilihanA)
        pilihanB = Quiz()
        pilihanB.text = "Meminta suku Dani untuk tinggal di desanya saja dan mengurangi kegiatan berkumpul dengan suku lain karena bajunya yang terbuka."
        dataPilihan.add(pilihanB)
        pilihanC = Quiz()
        pilihanC.text = "Mengagumi pakaian adat suku Dani dengan menghargai perbedaan dalam hal pakaian adat."
        dataPilihan.add(pilihanC)
        pilihanD = Quiz()
        pilihanD.text = "Menjadikan pakaian adat suku Dani sebagai bahan ejekan"
        dataPilihan.add(pilihanD)
        question = Question(7, dataPilihan, pilihanC, 0, "Suku Bangsa Indonesia sangat beragam dan berbeda dari segi kebudayaan. Seperti yang kalian ketahui bahwa pakaian adat suku Dani cukup terbuka dibandingkan suku lainnya. Melihat hal tersebut, bagaimana sikap yang harus kita lakukan terhadap adanya perbedaan.")
        dataQuestion.add(question)

        dataPilihan = ArrayList()
        pilihanA = Quiz()
        pilihanA.text = "Memuji bahwa baju yang dikenakan Putri sangat bagus"
        dataPilihan.add(pilihanA)
        pilihanB = Quiz()
        pilihanB.text = "Meminta Putri untuk menggunakan baju adat Bugis sesuai suku asalnya."
        dataPilihan.add(pilihanB)
        pilihanC = Quiz()
        pilihanC.text = "Mengajak Putri foto bersama dan menyimpannya sebagai kenangan"
        dataPilihan.add(pilihanC)
        pilihanD = Quiz()
        pilihanD.text = "Ani juga ingin menggunakan baju adat suku Bugis"
        dataPilihan.add(pilihanD)
        question = Question(8, dataPilihan, pilihanB, 0, "Ani berasal dari suku Jawa dan memiliki teman bernama Putri yang berasal dari suku Bugis. Suatu hari, terdapat peringatan hari Kartini dan siswa diminta untuk menggunakan baju adat salah satu provinsi di Indonesia. Putri ternyata menggunakan kebaya dan bersarung batik. Melihat hal tersebut tanggapan Ani yang tidak tepat")
        dataQuestion.add(question)

        dataPilihan = ArrayList()
        pilihanA = Quiz()
        pilihanA.text = "Mengajak Ali makan Paniki bersama karena rasanya yang lezat "
        dataPilihan.add(pilihanA)
        pilihanB = Quiz()
        pilihanB.text = "Meminta Ali untuk mencicipi Paniki meskipun hanya sedikit"
        dataPilihan.add(pilihanB)
        pilihanC = Quiz()
        pilihanC.text = "Membelikan Ali bakso dan mengajaknya untuk makan bersama"
        dataPilihan.add(pilihanC)
        pilihanD = Quiz()
        pilihanD.text = "Kecewa terhadap Ali yang tidak mau makan Paniki bersama"
        dataPilihan.add(pilihanD)
        question = Question(9, dataPilihan, pilihanC, R.drawable.soal_9_makanan, "Pada saat Ali bermain di rumah Bobi, ibunya sedang memasak Paniki yaitu makanan dari daerah Manado yang terbuat dari daging kelelawar. Bobi tahu jika Ali beragam Islam dan tidak memakan daging keleawar. Sikap Bobi terhadap Ali yang tepat adalah....")
        dataQuestion.add(question)

        dataPilihan = ArrayList()
        pilihanA = Quiz()
        pilihanA.text = "Rumah Limas"
        dataPilihan.add(pilihanA)
        pilihanB = Quiz()
        pilihanB.text = "Rumah Lamin"
        dataPilihan.add(pilihanB)
        pilihanC = Quiz()
        pilihanC.text = "Rumah Kebaya"
        dataPilihan.add(pilihanC)
        pilihanD = Quiz()
        pilihanD.text = "Rumah Gadang"
        dataPilihan.add(pilihanD)
        question = Question(10, dataPilihan, pilihanC, 0, "Rumah adat terbuat dari kayu dan dapat ditinggali oleh keluarga besar. Atapnya berbentuk menonjol seperti tanduk kerbau dan biasa disebut dengan gonjong. Rumah adat yang dimaksud adalah....")
        dataQuestion.add(question)
    }
}
