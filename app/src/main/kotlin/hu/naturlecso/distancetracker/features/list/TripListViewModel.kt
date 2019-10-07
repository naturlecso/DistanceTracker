package hu.naturlecso.distancetracker.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import hu.naturlecso.distancetracker.domain.store.TripStore

class TripListViewModel(
    tripStore: TripStore
) : ViewModel() {
    val trips = tripStore.getAll()
        .toLiveData()
}
