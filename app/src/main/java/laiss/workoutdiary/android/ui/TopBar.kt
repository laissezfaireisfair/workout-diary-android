package laiss.workoutdiary.android.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import laiss.workoutdiary.android.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navHostController: NavHostController, onMenuClick: () -> Unit, onBackClick: () -> Unit) {
    TopAppBar(title = {
        Text(text = navHostController.currentDestination?.route ?: "Workout diary")
    }, navigationIcon = {
        when {
            navHostController.currentDestination?.route == Screens.Workouts.route -> IconButton(
                onMenuClick
            ) { Icon(Icons.Filled.Menu, contentDescription = "Menu") }

            else -> IconButton(onBackClick) {
                Icon(
                    Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Navigate back"
                )
            }
        }

    }, colors = with(MaterialTheme.colorScheme) {
        TopAppBarColors(
            containerColor = surfaceContainer,
            scrolledContainerColor = onSurface,
            navigationIconContentColor = onSurface,
            titleContentColor = onSurface,
            actionIconContentColor = onSurface
        )
    })
}