package laiss.workoutdiary.android.services

import kotlinx.coroutines.delay
import laiss.workoutdiary.android.models.ExerciseWithApproaches
import laiss.workoutdiary.android.models.ExerciseWithDistance
import laiss.workoutdiary.android.models.Workout
import java.time.Instant
import java.util.UUID

object DataService {
    suspend fun getWorkouts(): List<Workout> {
        delay(300)
        return listOf(
            Workout(
                id = UUID.randomUUID(), timestamp = Instant.now(), exercises = listOf(
                    ExerciseWithApproaches(
                        id = UUID.randomUUID(),
                        name = "Push-ups",
                        countByApproach = listOf(15, 30, 20, 15)
                    )
                )
            ), Workout(
                id = UUID.randomUUID(),
                timestamp = Instant.now(),
                exercises = listOf(
                    ExerciseWithApproaches(
                        id = UUID.randomUUID(),
                        name = "Push-ups",
                        countByApproach = listOf(15, 30, 20, 15)
                    ),
                    ExerciseWithDistance(
                        id = UUID.randomUUID(),
                        name = "Threadmill",
                        distanceKm = 5.0
                    )
                )
            )
        )
    }
}