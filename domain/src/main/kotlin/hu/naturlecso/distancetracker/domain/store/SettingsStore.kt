package hu.naturlecso.distancetracker.domain.store

import hu.naturlecso.distancetracker.domain.model.DistanceUnit
import io.reactivex.Flowable

interface SettingsStore {
    fun getDistanceUnit(): Flowable<DistanceUnit>
}
