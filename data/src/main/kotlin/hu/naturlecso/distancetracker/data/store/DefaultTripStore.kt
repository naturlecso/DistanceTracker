package hu.naturlecso.distancetracker.data.store

import com.f2prateek.rx.preferences2.RxSharedPreferences
import hu.naturlecso.distancetracker.data.util.Preferences.ACTUAL_DISTANCE
import hu.naturlecso.distancetracker.data.util.Preferences.TRIP_IN_PROGRESS
import hu.naturlecso.distancetracker.data.util.toFlowable
import hu.naturlecso.distancetracker.domain.model.Trip
import hu.naturlecso.distancetracker.domain.store.TripStore
import io.reactivex.Flowable

class DefaultTripStore(
    private val rxSharedPreferences: RxSharedPreferences
) : TripStore {

    override fun getAll(): Flowable<List<Trip>> {
        TODO("not implemented")
    }

    override fun getActualDistance() = rxSharedPreferences.getFloat(ACTUAL_DISTANCE, 0f)
        .toFlowable()

    override fun tripInProgress() = rxSharedPreferences.getBoolean(TRIP_IN_PROGRESS, false)
        .toFlowable()
}
