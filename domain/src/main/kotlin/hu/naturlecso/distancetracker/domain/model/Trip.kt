package hu.naturlecso.distancetracker.domain.model

import org.threeten.bp.LocalDateTime

data class Trip (
    val id: Long,
    val name: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime?,
    val distance: Float
)
