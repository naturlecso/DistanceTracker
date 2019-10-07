package hu.naturlecso.distancetracker.features.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import hu.naturlecso.distancetracker.R
import hu.naturlecso.distancetracker.common.presentation.BindableRecyclerViewAdapter
import hu.naturlecso.distancetracker.databinding.ListItemTripBinding

class TripAdapter : BindableRecyclerViewAdapter<TripListItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemTripBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_trip,
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun getItemViewModel(item: TripListItem): ViewModel = TripListItemViewModel(item)

    override val diffCallback: DiffUtil.ItemCallback<TripListItem> = object : DiffUtil.ItemCallback<TripListItem>() {
        override fun areItemsTheSame(oldItem: TripListItem, newItem: TripListItem): Boolean =
            oldItem.trip.id == newItem.trip.id && oldItem.distanceUnit == newItem.distanceUnit
        override fun areContentsTheSame(oldItem: TripListItem, newItem: TripListItem): Boolean =
            oldItem == newItem
    }
}
