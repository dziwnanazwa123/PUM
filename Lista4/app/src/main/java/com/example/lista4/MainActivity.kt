package com.example.lista4

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lista4.model.QuizQuestion
import com.example.lista4.ui.theme.Lista4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lista4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ){
                QuizUI()
                }
            }
        }
    }
}

val questions : List<QuizQuestion> = QuizQuestion().populateQuestionsList();

@Composable
fun QuizUI() {
    val (selectedAnswerIndex, onAnswerSelected) = rememberSaveable {
        mutableIntStateOf(0)
    }
    val currentQuestionIndex : MutableIntState = rememberSaveable() { mutableIntStateOf(0) }
    val score : MutableIntState = rememberSaveable() { mutableIntStateOf(0) };

    if (currentQuestionIndex.intValue >= 10) {
        EndScreen(score.intValue)
        return
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(0.05f))
        Text(
            text = "Pytanie ${currentQuestionIndex.intValue+1}/10",
            fontSize = 34.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.1f)
        )
        LinearProgressIndicator(
            progress = {
                (currentQuestionIndex.value + 1) / 10f
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 100.dp)
                .weight(0.01f)
        )
        Spacer(modifier = Modifier.weight(0.05f))
        Text(
            text = questions[currentQuestionIndex.value].question,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .weight(0.15f)
                .background(color = Color.Gray)
        )
        Spacer(modifier = Modifier.weight(0.05f))
        Card(
            modifier = Modifier
                .weight(0.49f)
                .padding(horizontal = 20.dp)
        ) {
            for (i in 0..3) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (selectedAnswerIndex == i),
                            onClick = { onAnswerSelected(i) }
                        )
                        .padding(horizontal = 16.dp)
                        .weight(0.25f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = (selectedAnswerIndex == i),
                        onClick = { onAnswerSelected(i) },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = questions[currentQuestionIndex.intValue].answers[i],
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
        Button(
            onClick = {
                nextQuestion(currentQuestionIndex, selectedAnswerIndex, score)
                onAnswerSelected(-1)
                Log.d("debug", score.intValue.toString());
            },
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.12f)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(text = "Następne", fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.weight(0.08f))
    }
}

fun nextQuestion(currentQuestionIndex : MutableIntState, selectedAnswerIndex : Int,
                 score : MutableIntState) {
    if (selectedAnswerIndex == questions[currentQuestionIndex.value].rightAnswerIndex) {
        score.intValue += 10
    }
    currentQuestionIndex.intValue++
}

@Composable
fun EndScreen(score: Int) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Zdobyłeś ${score} punktów!",
            textAlign = TextAlign.Center,
            fontSize = 34.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EndScreenPreview() {
    Lista4Theme {
        EndScreen(100)
    }
}

@Preview(showBackground = true)
@Composable
fun QuizUIPreview() {
    Lista4Theme {
        QuizUI()
    }
}