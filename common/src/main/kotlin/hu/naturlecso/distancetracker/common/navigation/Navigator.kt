package hu.naturlecso.distancetracker.common.navigation

import io.reactivex.Flowable

interface Navigator {
    fun navigate(navigationCommand: NavigationCommand)
    fun navigationEvents(): Flowable<NavigationCommand>
}
