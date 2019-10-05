package hu.naturlecso.distancetracker.domain.store

import hu.naturlecso.distancetracker.domain.model.Trip
import io.reactivex.Flowable

interface TripStore {
    fun getAll(): Flowable<List<Trip>>
    fun getActualDistance(): Flowable<Float>
    fun tripInProgress(): Flowable<Boolean>
}
