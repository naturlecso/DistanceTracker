package hu.naturlecso.distancetracker.features.list

import androidx.databinding.ObservableField
import hu.naturlecso.distancetracker.common.presentation.RowViewModel

class TripListItemViewModel(item: TripListItem) : RowViewModel<TripListItem>(item){
    val trip = ObservableField(item.trip)
    val distanceUnit = ObservableField(item.distanceUnit)
}
