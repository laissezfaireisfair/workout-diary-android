package laiss.workoutdiary.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import laiss.workoutdiary.android.ui.viewModels.CreateWorkoutViewModel

@Composable
fun CreateWorkoutScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: CreateWorkoutViewModel = viewModel()
) {
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
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = {/*TODO: Open date picker*/ }) {
                            Text(text = "10.9.2024", fontSize = 25.sp)
                        }
                        TextButton(onClick = {/*TODO: Open time picker*/ }) {
                            Text(text = "13:37", fontSize = 25.sp)
                        }
                    }
                }

                items(state.exercises) { exercise ->
                    ElevatedCard() {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = exercise.name, fontSize = 25.sp)
                            Spacer(modifier = Modifier.height(7.dp))
                            Text(
                                text = "yadayada",
                                fontSize = 15.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        OutlinedButton(onClick = {/*TODO: Cancel*/ }) {
                            Text(text = "Cancel", fontSize = 20.sp)
                        }
                        Button(onClick = {/*TODO: Save*/ }) {
                            Text(text = "  Save  ", fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateWorkoutScreenPreview() {
    CreateWorkoutScreen(navController = rememberNavController(),
        viewModel = viewModel<CreateWorkoutViewModel>().apply { setPreviewMode() })
}