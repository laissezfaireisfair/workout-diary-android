package laiss.workoutdiary.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import laiss.workoutdiary.android.navigation.Screens
import laiss.workoutdiary.android.ui.TopBar
import laiss.workoutdiary.android.ui.WorkoutsScreen
import laiss.workoutdiary.android.ui.theme.WorkoutDiaryAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkoutDiaryAndroidTheme {
                val navHostController = rememberNavController()
                NavHost(
                    navController = navHostController, startDestination = Screens.Workouts.route
                ) {
                    composable(Screens.Workouts.route) {
                        Scaffold(modifier = Modifier, topBar = {
                            TopBar(navHostController,
                                onMenuClick = { onMenuClick() },
                                onBackClick = { onBackClick(navHostController) })
                        }) { innerPadding ->
                            WorkoutsScreen(
                                navHostController,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun onMenuClick() {
    // TODO: Implement
}

fun onBackClick(navHostController: NavHostController) {
    if (navHostController.previousBackStackEntry != null)
        navHostController.navigateUp()
}