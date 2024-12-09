package laiss.workoutdiary.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import laiss.workoutdiary.android.ui.viewModels.WorkoutsViewModel

@Composable
fun WorkoutsScreen(
    navHostController: NavHostController,
    viewModel: WorkoutsViewModel = viewModel(),
    modifier: Modifier
) {
    viewModel.launch()
    val state = viewModel.uiState.collectAsState().value

    Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
        when {
            state.isLoading -> {
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Loading...")
                }
            }

            else -> {
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Nothing to see here yet")
                }
            }
        }
    }
}