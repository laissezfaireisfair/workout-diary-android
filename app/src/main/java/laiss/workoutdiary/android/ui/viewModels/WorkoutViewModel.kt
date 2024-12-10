package laiss.workoutdiary.android.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import laiss.workoutdiary.android.models.Exercise
import laiss.workoutdiary.android.models.ExerciseWithApproaches
import laiss.workoutdiary.android.models.ExerciseWithDistance
import laiss.workoutdiary.android.services.DataService
import java.time.Instant
import java.util.UUID

data class CreateWorkoutScreenState(
    val timestamp: Instant = Instant.now(),
    val exercises: List<Exercise> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class CreateWorkoutViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CreateWorkoutScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadLastWorkout()
        }
    }

    fun setPreviewMode() {
        _uiState.update {
            CreateWorkoutScreenState(
                exercises = listOf(
                    ExerciseWithApproaches(
                        id = UUID.randomUUID(),
                        name = "Push-ups",
                        countByApproach = listOf(15, 30, 20, 15)
                    ), ExerciseWithDistance(
                        id = UUID.randomUUID(), name = "Threadmill", distanceKm = 5.0
                    )
                )
            )
        }
    }

    private suspend fun loadLastWorkout() {
        try {
            _uiState.update { CreateWorkoutScreenState(isLoading = true) }
            val last = DataService.getWorkouts().maxByOrNull { it.timestamp }
            _uiState.update { CreateWorkoutScreenState(exercises = last?.exercises ?: emptyList()) }
        } catch (exception: Exception) {
            _uiState.update { CreateWorkoutScreenState(error = exception.message) }
        }
    }
}