package hu.naturlecso.distancetracker.data

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.google.android.gms.location.LocationServices
import hu.naturlecso.distancetracker.data.action.DefaultTripAction
import hu.naturlecso.distancetracker.data.cache.DistanceTrackerDatabase
import hu.naturlecso.distancetracker.data.service.LocationUpdatesBroadcastReceiver
import hu.naturlecso.distancetracker.data.service.NotificationService
import hu.naturlecso.distancetracker.data.store.DefaultTripStore
import hu.naturlecso.distancetracker.data.util.Actions
import hu.naturlecso.distancetracker.domain.action.TripAction
import hu.naturlecso.distancetracker.domain.store.TripStore
import org.koin.dsl.module

val dataModule = module {
    val databaseName = "distancetracker.db"

    // location
    single { LocationServices.getFusedLocationProviderClient(get<Context>()) }

    factory { Intent(get(), LocationUpdatesBroadcastReceiver::class.java).apply {
        action = Actions.LOCATION_UPDATES
    }.let {
        PendingIntent.getBroadcast(get(), 0, it, PendingIntent.FLAG_UPDATE_CURRENT)
    } }

    // notification
    single { NotificationService(get(), get()) }

    // system services
    single { get<Context>().getSystemService(NOTIFICATION_SERVICE) as NotificationManager }

    // cache
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
    single { RxSharedPreferences.create(get()) }

    single { Room.databaseBuilder(get(), DistanceTrackerDatabase::class.java, databaseName).build() }
    single { get<DistanceTrackerDatabase>().tripDao() }

    // action
    single<TripAction> { DefaultTripAction(get(), get(), get(), get(), get()) }

    // store
    single<TripStore> { DefaultTripStore(get(), get()) }
}