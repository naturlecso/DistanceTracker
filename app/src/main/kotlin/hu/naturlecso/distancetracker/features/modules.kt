package hu.naturlecso.distancetracker.features

import hu.naturlecso.distancetracker.features.control.TripControlViewModel
import hu.naturlecso.distancetracker.features.list.TripListViewModel
import hu.naturlecso.distancetracker.features.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { TripControlViewModel() }
    viewModel { TripListViewModel() }
    viewModel { SettingsViewModel() }
}
