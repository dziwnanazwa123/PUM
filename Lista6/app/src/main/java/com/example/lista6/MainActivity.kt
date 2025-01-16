package com.example.lista6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lista6.model.Exercise
import com.example.lista6.model.ExerciseList
import com.example.lista6.model.Grade
import com.example.lista6.ui.theme.Lista6Theme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lista6Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

sealed class Screens(val route: String) {
    data object AssignmentsListScreen : Screens("assignmentsListScreen")
    data object GradesListScreen : Screens("gradesListScreen")
    data object ExercisesListScreen : Screens("exercisesScreen")
}


val dummyData = DummyData()
val assignmentList : List<ExerciseList> = dummyData.initializeExerciseList()
val gradeList : List<Grade> = dummyData.initializeGradeList()

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomMenu(navController = navController)},
        content = { NavGraph(navController = navController) }
    )
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.AssignmentsListScreen.route
    ) {
        composable(route = Screens.AssignmentsListScreen.route){ AssignmentsListScreen(navController) }
        composable(route = Screens.GradesListScreen.route){ GradesListScreen() }
        composable(
            route = Screens.ExercisesListScreen.route + "/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            ExercisesListScreen(index)
        }
    }
}

@Composable
fun BottomMenu(navController: NavHostController) {
    val screens = listOf(
        BottomBar.AssignmentsList, BottomBar.GradesList
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{
        screens.forEach{screen ->
            NavigationBarItem(
                label = { Text(text = screen.title)},
                icon = {Icon(imageVector = screen.icon, contentDescription = "icon")},
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {navController.navigate(screen.route)}
            )
        }
    }
}

@Composable
fun AssignmentsListScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 14.dp, end = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(0.04f))
        Text(
            text = "Moje Listy zadań",
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 14.dp)
                .weight(0.12f)
        )
        Spacer(Modifier.weight(0.04f))
        LazyColumn(Modifier.weight(0.6f)) {
            itemsIndexed(assignmentList) { index, exerciseList ->
                AssignmentElement(exerciseList, index, navController)

            }
        }
        Spacer(Modifier.weight(0.2f))
    }
}

@Composable
fun AssignmentElement(exerciseList: ExerciseList, index : Int,
                      navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, bottom = 10.dp)
            .border(width = 1.dp, color = Color(0xFFf7e6c0), shape = CircleShape)
            .background(Color(0xFFf7e6c0))
            .padding(16.dp)
            .clickable { navController.navigate("${Screens.ExercisesListScreen.route}/$index") },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = exerciseList.subject.name, textAlign = TextAlign.Left, fontSize = 22.sp)
            Text(text = "Liczba zadań: ${exerciseList.exercises.size}", textAlign = TextAlign.Left)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = exerciseList.name, textAlign = TextAlign.Left, fontSize = 22.sp)
            Text(text = "Ocena: ${exerciseList.grade}", textAlign = TextAlign.Left)
        }
    }
}

@Composable
fun GradesListScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 14.dp, end = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(0.04f))
        Text(
            text = "Moje Oceny",
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 14.dp)
                .weight(0.12f)
        )
        Spacer(Modifier.weight(0.04f))
        LazyColumn(Modifier.weight(0.6f)) {
            items(gradeList) {
                GradeElement(it)
            }
        }
        Spacer(Modifier.weight(0.2f))
    }
}

@Composable
fun GradeElement(grade : Grade) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, bottom = 10.dp)
            .background(Color(0xFFf7e6c0))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = grade.subject.name, textAlign = TextAlign.Left, fontSize = 22.sp)
            Text(text = "Liczba zadań: ${grade.assignmentsCount}", textAlign = TextAlign.Left)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = "Średnia: ${String.format(Locale.getDefault(), "%.2f", grade.grade)}",
                textAlign = TextAlign.Left, fontSize = 22.sp)
        }
    }
}

@Composable
fun ExercisesListScreen(assignmentIndex : Int) {
    val exerciseList : ExerciseList = assignmentList[assignmentIndex]
    val exercises : List<Exercise> = exerciseList.exercises

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 14.dp, end = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(0.04f))
        Text(
            text = "${exerciseList.subject.name}\n${exerciseList.name}",
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp, bottom = 14.dp)
                .weight(0.14f)
        )
        Spacer(Modifier.weight(0.04f))
        LazyColumn(Modifier.weight(0.56f)) {
            itemsIndexed(exercises) { index, exercise ->
                ExerciseElement(exercise, index)
            }
        }
        Spacer(Modifier.weight(0.2f))
    }
}

@Composable
fun ExerciseElement(exercise: Exercise, index : Int) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, bottom = 10.dp)
            .background(Color(0xFFf7e6c0))
            .padding(16.dp),
    ) {
        Text(text = "pkt: ${exercise.points}",
            textAlign = TextAlign.Right,
            fontSize = 22.sp,
            modifier = Modifier.fillMaxWidth().align(alignment = Alignment.End)
        )
        Column {
            Text(
                text = "Zadanie ${index+1}",
                textAlign = TextAlign.Left,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth().align(Alignment.Start)
            )
            Text(
                text = exercise.content,
                textAlign = TextAlign.Left,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth().align(Alignment.Start)
            )
        }
    }
}

@Preview
@Composable
fun Previewew() {
    Lista6Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Navigation()
        }
    }
}

