package hu.naturlecso.distancetracker.features.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import hu.naturlecso.distancetracker.R
import hu.naturlecso.distancetracker.common.presentation.BindableRecyclerViewAdapter
import hu.naturlecso.distancetracker.databinding.ListItemTripBinding
import hu.naturlecso.distancetracker.domain.model.Trip

class TripAdapter : BindableRecyclerViewAdapter<Trip>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ListItemTripBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_trip,
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun getItemViewModel(item: Trip): ViewModel = TripListItemViewModel(item)

    override val diffCallback: DiffUtil.ItemCallback<Trip> = object : DiffUtil.ItemCallback<Trip>() {
        override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean = oldItem == newItem
    }
}
