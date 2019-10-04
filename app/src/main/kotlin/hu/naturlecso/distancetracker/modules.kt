package hu.naturlecso.distancetracker

import hu.naturlecso.distancetracker.common.navigation.DefaultNavigator
import hu.naturlecso.distancetracker.common.navigation.Navigator
import org.koin.dsl.module

val appModule = module {
    single<Navigator> { DefaultNavigator() }
}