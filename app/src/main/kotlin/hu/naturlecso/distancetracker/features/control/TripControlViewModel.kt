package hu.naturlecso.distancetracker.features.control

import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import hu.naturlecso.distancetracker.common.binding.command
import hu.naturlecso.distancetracker.domain.action.TripAction
import hu.naturlecso.distancetracker.domain.store.SettingsStore
import hu.naturlecso.distancetracker.domain.store.TripStore

class TripControlViewModel(
    tripStore: TripStore,
    tripAction: TripAction,
    settingsStore: SettingsStore
) : ViewModel() {

    val ongoingTrip = tripStore.tripInProgress()
        .toLiveData()

    val distance = tripStore.getActualDistance()
        .toLiveData()

    val distanceUnit = settingsStore.getDistanceUnit()
        .toLiveData()

    val tripCommand = command {
        if (ongoingTrip.value!!) {
            tripAction.endTrip()
        } else {
            tripAction.startTrip()
        }.subscribe()
    }
}
