package hu.naturlecso.distancetracker.domain.action

import io.reactivex.Completable

interface TripAction {
    fun startTrip(): Completable
    fun endTrip(): Completable
    fun updateTripName(tripId: Long, name: String): Completable
}
