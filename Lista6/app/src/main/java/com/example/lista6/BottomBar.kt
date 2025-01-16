package com.example.lista6

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object AssignmentsList : BottomBar(Screens.AssignmentsListScreen.route, "Listy zadań",
        Icons.AutoMirrored.Filled.List
    )
    data object GradesList : BottomBar(Screens.GradesListScreen.route, "Oceny", Icons.Default.Info)
}