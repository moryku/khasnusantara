//package com.akuarintar.ui.quiz
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import com.akuarintar.data.database.entity.History
//import com.akuarintar.data.database.entity.Kosakata
//import com.akuarintar.data.model.question.Question
//import com.akuarintar.data.repository.AkuarintarRepository
//import com.akuarintar.util.enums.QuizLevelType
//import com.akuarintar.util.enums.QuizTranslateType
//import com.akuarintar.util.enums.QuizType
//import com.akuarintar.util.loading.Loading
//
//class QuizViewModel(private val repo: AkuarintarRepository) : ViewModel() {
//
//    var dataKosakata: List<Kosakata> = ArrayList()
//    var dataQuestion: MutableList<Question> = ArrayList()
//    var levelType: QuizLevelType = QuizLevelType.EASY
//
//    val kosakataList: LiveData<List<Kosakata>> by lazy {
//        repo.kosakataList
//    }
//
//    val loading: LiveData<Loading> by lazy {
//        repo.loading
//    }
//
//    fun resetLoading() = repo.resetLoading()
//
//    fun addMatchToHistory(history: History) {
//        repo.insertHistory(history)
//    }
//
//    fun getAllKosakata() {
//        repo.getAllKosakata()
//    }
//
//    fun generateQuizLevel(category: String) {
//        if (category == "easy") {
//            levelType = QuizLevelType.EASY
//        } else if (category == "medium") {
//            levelType = QuizLevelType.MEDIUM
//        } else if (category == "hard") {
//            levelType = QuizLevelType.HARD
//        }
//    }
//
//    fun generateQuetion(){
//        var dataQuestion: MutableList<Question> = ArrayList()
//        if (dataKosakata.isNotEmpty()) {
//            for (kosakata in dataKosakata) {
//                if (levelType == QuizLevelType.EASY) {
//                    if (kosakata.kategori.toLowerCase() == "warna" || kosakata.kategori.toLowerCase() == "hijaiyah" || kosakata.kategori.toLowerCase() == "angka" || kosakata.kategori.toLowerCase() == "keluarga") {
//                        var question = Question(kosakata.id, generateOptionAnser(kosakata), QuizLevelType.EASY, kosakata, "", QuizType.PILIHAN_GANDA, QuizTranslateType.ARAB_INDO)
//                        dataQuestion.add(question)
//                    }
//                } else if (levelType == QuizLevelType.MEDIUM) {
//                    if (kosakata.kategori.toLowerCase() == "keadaan" || kosakata.kategori.toLowerCase() == "sifat") {
//                        var question = Question(kosakata.id, generateOptionAnser(kosakata), QuizLevelType.MEDIUM, kosakata, "", QuizType.PILIHAN_GANDA, QuizTranslateType.ARAB_INDO)
//                        dataQuestion.add(question)
//                    }
//                } else if (levelType == QuizLevelType.HARD) {
//                    if (kosakata.kategori.toLowerCase() == "ungkapan") {
//                        var question = Question(kosakata.id, generateOptionAnser(kosakata), QuizLevelType.HARD, kosakata, "", QuizType.PILIHAN_GANDA, QuizTranslateType.ARAB_INDO)
//                        dataQuestion.add(question)
//                    }
//                }
//            }
//        }
//        dataQuestion.shuffle()
//        var tenDataQuestion: MutableList<Question> = ArrayList()
//        for (i in dataQuestion.take(10)){
//            tenDataQuestion.add(i)
//        }
//        this.dataQuestion = tenDataQuestion
//    }
//
//    fun generateOptionAnser(questionKosakata: Kosakata): List<Kosakata>{
//        var optionAnswer:  MutableList<Kosakata> = ArrayList()
//        if (dataKosakata.isNotEmpty()) {
//            for (i in 0..2) {
//                val rnds = (0..dataKosakata.size-1).random()
//                var result = dataKosakata.get(rnds)
//                if (result.id == questionKosakata.id) {
//                    if (rnds == 0) {
//                        result = dataKosakata.get(rnds+1)
//                    } else {
//                        result = dataKosakata.get(rnds-1)
//                    }
//                }
//                optionAnswer.add(result)
//            }
//        }
//        optionAnswer.add(questionKosakata)
//        optionAnswer.shuffle()
//        return optionAnswer;
//    }
//}
