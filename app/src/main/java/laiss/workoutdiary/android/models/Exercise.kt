package laiss.workoutdiary.android.models

import java.util.UUID
import kotlin.time.Duration

abstract class Exercise(override val id: UUID, val name: String) : IId

class ExerciseWithApproaches(id: UUID, name: String, val countByApproach: List<Int>) :
    Exercise(id, name)

class ExerciseWithDuration(id: UUID, name: String, val duration: Duration) : Exercise(id, name)

class ExerciseWithDistance(id: UUID, name: String, val distanceKm: Double) : Exercise(id, name)
