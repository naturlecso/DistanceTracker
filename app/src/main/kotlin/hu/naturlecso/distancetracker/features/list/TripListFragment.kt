package hu.naturlecso.distancetracker.features.list

import androidx.recyclerview.widget.LinearLayoutManager
import hu.naturlecso.distancetracker.R
import hu.naturlecso.distancetracker.common.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TripListFragment : BaseFragment<TripListViewModel>() {
    override val layoutRes: Int = R.layout.fragment_list
    override val viewModel: TripListViewModel by viewModel()

    override fun afterViews() {
        tripList.apply {
            adapter = TripAdapter()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
