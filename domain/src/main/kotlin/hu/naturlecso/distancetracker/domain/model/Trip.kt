package hu.naturlecso.distancetracker.domain.model

import org.threeten.bp.LocalDate

data class Trip (
    val id: Long,
    val name: String,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val distance: Float
)
