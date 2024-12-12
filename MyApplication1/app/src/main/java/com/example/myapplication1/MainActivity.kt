package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvQuestionCounter: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvQuestion: TextView
    private lateinit var radioGroupOptions: RadioGroup
    private lateinit var btnNext: Button

    private lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvQuestionCounter = findViewById(R.id.tvQuestionCounter)
        progressBar = findViewById(R.id.progressBar)
        tvQuestion = findViewById(R.id.tvQuestion)
        radioGroupOptions = findViewById(R.id.radioGroupOptions)
        btnNext = findViewById(R.id.btnNext)

        questions = generateQuestions()
        loadQuestion()

        btnNext.setOnClickListener {
            checkAnswer()
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                loadQuestion()
            } else {
                showResult()
            }
        }
    }

    private fun generateQuestions(): List<Question> {
        return listOf(
            Question("Jak nazywa się siła, która działa na ciało w ruchu wzdłuż okręgu?",arrayOf("Siła tarcia", "Siła grawitacyjna", "Siła dośrodkowa", "Siła odśrodkowa"), 2),
            Question("Jaką jednostkę ma prędkość w układzie SI?",arrayOf("m/s", "km/h", "m^2/s^2", "J/kg"), 0),
            Question("Co opisuje druga zasada dynamiki Newtona?",arrayOf("Ruch prostoliniowy jednostajny", "Zasada akcji i reakcji", "Proporcjonalność przyspieszenia do siły działającej na ciało", "Zasada zachowania pędu"), 2),
            Question("Jak nazywa się zjawisko, w którym fala zmienia kierunek w wyniku przejścia z jednego medium do drugiego?",arrayOf("Odbicie", "Załamanie", "Dyfrakcja", "Interferencja"), 1),
            Question("Jaką energię ma ciało w spoczynku na wysokości h?",arrayOf("Energię kinetyczną", "Energię cieplną", "Energię potencjalną", "Energię mechaniczną"), 2),
            Question("Jakie promieniowanie ma największą długość fali?",arrayOf("Promieniowanie gamma", "Promieniowanie rentgenowskie", "Promieniowanie ultrafioletowe", "Podczerwień"), 3),
            Question("Czym jest ciśnienie hydrostatyczne?",arrayOf("Ciśnieniem w gazach", "Ciśnieniem cieczy w stanie spoczynku", "Ciśnieniem cieczy w ruchu", "Ciśnieniem atmosferycznym"), 1),
            Question("Jakie zjawisko zachodzi podczas rozprężania gazu w warunkach adiabatycznych?",arrayOf("Wzrost temperatury", "Spadek temperatury", "Brak zmiany temperatury", "Ogrzewanie zewnętrzne"), 1),
            Question("Jakie zjawisko fizyczne wykorzystuje działanie lusterka wstecznego w samochodzie?",arrayOf("Odbicie", "Załamanie", "Interferencja", "Dyfrakcja"), 0),
            Question("Co to jest energia wewnętrzna?",arrayOf("Energia kinetczna cząsteczek", "Suma energii potencjalnej i kinetycznej w układzie", "Energia cieplna", "Energia związana z oddziaływaniami między cząsteczkami"), 1)
        )
    }

    private fun loadQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        tvQuestionCounter.text = "Pytanie ${currentQuestionIndex + 1}/${questions.size}"
        progressBar.progress = (currentQuestionIndex + 1) * 100 / questions.size
        tvQuestion.text = currentQuestion.question

        val options = currentQuestion.options
        (radioGroupOptions.getChildAt(0) as RadioButton).text = options[0]
        (radioGroupOptions.getChildAt(1) as RadioButton).text = options[1]
        (radioGroupOptions.getChildAt(2) as RadioButton).text = options[2]
        (radioGroupOptions.getChildAt(3) as RadioButton).text = options[3]

        radioGroupOptions.clearCheck()
    }

    private fun checkAnswer() {
        val selectedOptionIndex = radioGroupOptions.indexOfChild(findViewById(radioGroupOptions.checkedRadioButtonId))
        if (selectedOptionIndex == questions[currentQuestionIndex].correctAnswerIndex) {
            score += 10
        }
    }

    private fun showResult() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SCORE", score)
        startActivity(intent)
        finish()
    }
}