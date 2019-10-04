package hu.naturlecso.distancetracker.features.control

import hu.naturlecso.distancetracker.R
import hu.naturlecso.distancetracker.common.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TripControlFragment : BaseFragment<TripControlViewModel>() {
    override val layoutRes: Int = R.layout.fragment_control
    override val viewModel: TripControlViewModel by viewModel()
}
