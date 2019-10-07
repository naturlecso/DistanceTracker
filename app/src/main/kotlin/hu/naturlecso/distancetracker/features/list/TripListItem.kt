package hu.naturlecso.distancetracker.features.list

import hu.naturlecso.distancetracker.domain.model.DistanceUnit
import hu.naturlecso.distancetracker.domain.model.Trip

data class TripListItem(
    val trip: Trip,
    val distanceUnit: DistanceUnit
)
