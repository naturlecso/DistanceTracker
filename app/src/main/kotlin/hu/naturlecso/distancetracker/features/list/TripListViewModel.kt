package hu.naturlecso.distancetracker.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import hu.naturlecso.distancetracker.domain.store.SettingsStore
import hu.naturlecso.distancetracker.domain.store.TripStore
import io.reactivex.rxkotlin.Flowables

class TripListViewModel(
    tripStore: TripStore,
    settingsStore: SettingsStore
) : ViewModel() {

    val trips = Flowables.combineLatest(
        tripStore.getAll(),
        settingsStore.getDistanceUnit()) { trips, distanceUnit ->
            trips.map { TripListItem(it, distanceUnit) } }
        .toLiveData()
}
