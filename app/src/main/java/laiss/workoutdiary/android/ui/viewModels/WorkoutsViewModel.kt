package laiss.workoutdiary.android.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import laiss.workoutdiary.android.models.ExerciseWithApproaches
import laiss.workoutdiary.android.models.ExerciseWithDistance
import laiss.workoutdiary.android.models.Workout
import laiss.workoutdiary.android.services.DataService
import java.time.Instant
import java.util.UUID

data class WorkoutsScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val workouts: List<Workout> = emptyList()
)

class WorkoutsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WorkoutsScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch { loadWorkouts() }
    }

    fun setPreviewMode() {
        val workoutsExample = listOf(
            Workout(
                id = UUID.randomUUID(), timestamp = Instant.now(), exercises = listOf(
                    ExerciseWithApproaches(
                        id = UUID.randomUUID(),
                        name = "Push-ups",
                        countByApproach = listOf(15, 30, 20, 15)
                    )
                )
            ), Workout(
                id = UUID.randomUUID(), timestamp = Instant.now(), exercises = listOf(
                    ExerciseWithApproaches(
                        id = UUID.randomUUID(),
                        name = "Push-ups",
                        countByApproach = listOf(15, 30, 20, 15)
                    ), ExerciseWithDistance(
                        id = UUID.randomUUID(), name = "Threadmill", distanceKm = 5.0
                    )
                )
            )
        )

        _uiState.update { it.copy(isLoading = false, error = null, workouts = workoutsExample) }
    }

    private suspend fun loadWorkouts() = try {
        _uiState.update { WorkoutsScreenState(isLoading = true) }
        val workouts = DataService.getWorkouts()
        _uiState.update { WorkoutsScreenState(workouts = workouts) }
    } catch (exception: Exception) {
        _uiState.update { WorkoutsScreenState(error = exception.message) }
    }
}