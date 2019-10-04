package hu.naturlecso.distancetracker.common.presentation

import androidx.lifecycle.ViewModel

abstract class RowViewModel<T> protected constructor(val item: T) : ViewModel()
