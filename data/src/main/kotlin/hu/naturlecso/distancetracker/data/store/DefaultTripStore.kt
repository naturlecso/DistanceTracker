package hu.naturlecso.distancetracker.data.store

import com.f2prateek.rx.preferences2.RxSharedPreferences
import hu.naturlecso.distancetracker.data.cache.TripDao
import hu.naturlecso.distancetracker.data.cache.TripDataModel
import hu.naturlecso.distancetracker.data.util.Preferences.ACTUAL_DISTANCE
import hu.naturlecso.distancetracker.data.util.Preferences.TRIP_IN_PROGRESS
import hu.naturlecso.distancetracker.data.util.toFlowable
import hu.naturlecso.distancetracker.domain.model.Trip
import hu.naturlecso.distancetracker.domain.store.TripStore
import io.reactivex.Flowable

class DefaultTripStore(
    private val rxSharedPreferences: RxSharedPreferences,
    private val tripDao: TripDao
) : TripStore {

    override fun getAll(): Flowable<List<Trip>> = tripDao.getAll()
        .map { dataModelList -> dataModelList.map { mapTripDataModelToDomainModel(it) } }

    override fun getActualDistance() = rxSharedPreferences.getFloat(ACTUAL_DISTANCE, 0f)
        .toFlowable()

    override fun tripInProgress() = rxSharedPreferences.getBoolean(TRIP_IN_PROGRESS, false)
        .toFlowable()

    private fun mapTripDataModelToDomainModel(dataModel: TripDataModel) = Trip(
        id = dataModel.id,
        name = dataModel.name,
        startDate = dataModel.startDate,
        endDate = dataModel.endDate,
        distance = dataModel.distance
    )
}
