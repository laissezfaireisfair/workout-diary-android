package laiss.workoutdiary.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import laiss.workoutdiary.android.ui.viewModels.WorkoutsViewModel
import java.time.ZoneId

@Composable
@Preview
fun WorkoutsScreenPreview() = WorkoutsScreen(navHostController = rememberNavController(),
    viewModel = viewModel<WorkoutsViewModel>().apply { setPreviewMode() })

@Composable
fun WorkoutsScreen(
    navHostController: NavHostController,
    viewModel: WorkoutsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    viewModel.launch()
    val state = viewModel.uiState.collectAsState().value

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        when {
            state.isLoading -> Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) { Text(text = "Loading...") }

            state.error != null -> Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Sorry, something went wrong.")
                Text(text = state.error)
            }

            else -> LazyColumn(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(state.workouts) { workout ->
                    ElevatedCard(colors = with(MaterialTheme.colorScheme) {
                        CardColors(
                            containerColor = secondaryContainer,
                            contentColor = onSecondaryContainer,
                            disabledContainerColor = secondaryContainer,
                            disabledContentColor = onSecondaryContainer
                        )
                    }) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            val localTime = workout.timestamp.atZone(ZoneId.systemDefault())
                            val dateText = with(localTime) { "$dayOfMonth.$monthValue.$year" }
                            val timeText = with(localTime) { "$hour:$minute" }
                            val exercisesText = workout.exercises.fold("") { a, e ->
                                if (a.isEmpty()) e.name else "$a, ${e.name}"
                            }
                            Text(text = dateText, fontSize = 30.sp)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = timeText, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(7.dp))
                            Text(
                                text = exercisesText,
                                fontSize = 15.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}