package hu.naturlecso.distancetracker.features.list

import androidx.databinding.ObservableField
import hu.naturlecso.distancetracker.common.presentation.RowViewModel
import hu.naturlecso.distancetracker.domain.model.Trip

class TripListItemViewModel(trip: Trip) : RowViewModel<Trip>(trip){
    val trip = ObservableField(trip)
}
