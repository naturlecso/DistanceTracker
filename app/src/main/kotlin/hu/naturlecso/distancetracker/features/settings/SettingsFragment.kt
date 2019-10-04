package hu.naturlecso.distancetracker.features.settings

import hu.naturlecso.distancetracker.R
import hu.naturlecso.distancetracker.common.presentation.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : BaseFragment<SettingsViewModel>() {
    override val layoutRes: Int = R.layout.fragment_settings
    override val viewModel: SettingsViewModel by viewModel()

}
