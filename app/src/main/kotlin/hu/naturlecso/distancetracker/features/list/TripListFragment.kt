package hu.naturlecso.distancetracker.features.list

import hu.naturlecso.distancetracker.R
import hu.naturlecso.distancetracker.common.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TripListFragment : BaseFragment<TripListViewModel>() {
    override val layoutRes: Int = R.layout.fragment_list
    override val viewModel: TripListViewModel by viewModel()
}
