package laiss.workoutdiary.android.models

import java.time.Instant
import java.util.UUID

class Workout(override val id: UUID, val timestamp: Instant, val exercises: List<Exercise>) : IId