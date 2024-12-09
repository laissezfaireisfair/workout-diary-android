package laiss.workoutdiary.android.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WorkoutsScreenState(
    val isLoading: Boolean = false
)

class WorkoutsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(WorkoutsScreenState(isLoading = true))
    val uiState: StateFlow<WorkoutsScreenState> = _uiState.asStateFlow()

    fun launch() {
        viewModelScope.launch {
            loadWorkouts()
        }
    }

    private suspend fun loadWorkouts() {
        delay(300)
        _uiState.update { it.copy(isLoading = false) }
    }
}