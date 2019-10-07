package hu.naturlecso.distancetracker.data.action

import android.app.PendingIntent
import android.content.SharedPreferences
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import hu.naturlecso.distancetracker.data.cache.TripDao
import hu.naturlecso.distancetracker.data.cache.TripDataModel
import hu.naturlecso.distancetracker.data.service.NotificationService
import hu.naturlecso.distancetracker.data.util.Preferences.ACTUAL_DISTANCE
import hu.naturlecso.distancetracker.data.util.Preferences.LAST_COORDINATES
import hu.naturlecso.distancetracker.data.util.Preferences.TRIP_IN_PROGRESS
import hu.naturlecso.distancetracker.domain.action.TripAction
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxkotlin.Singles
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

class DefaultTripAction(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val locationPendingIntent: PendingIntent,
    private val sharedPreferences: SharedPreferences,
    private val notificationService: NotificationService,
    private val tripDao: TripDao
) : TripAction {

    override fun startTrip(): Completable {

        val locationRequest: LocationRequest = LocationRequest().apply {
            interval = 30000 // Every 30 seconds.
            fastestInterval = 10000 // Every 10 seconds
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            maxWaitTime = interval // for getting bundled locations
        }

        return Completable.fromAction {
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationPendingIntent) }
            .andThen(Completable.fromAction { tripDao.insert(createNewTrip()) })
            .andThen(Completable.fromAction { notificationService.sendNotification() })
            .andThen(Completable.fromAction { sharedPreferences
                .edit()
                .putBoolean(TRIP_IN_PROGRESS, true)
                .apply()
            })
            .subscribeOn(Schedulers.io())
    }

    override fun endTrip(): Completable =
        Completable.fromAction { fusedLocationProviderClient.removeLocationUpdates(locationPendingIntent) }
            .andThen(Completable.fromAction { notificationService.removeNotification() })
            .andThen(updateActualTrip())
            .andThen(Completable.fromAction { sharedPreferences
                .edit()
                .putFloat(ACTUAL_DISTANCE, 0f)
                .putBoolean(TRIP_IN_PROGRESS, false)
                .remove(LAST_COORDINATES)
                .apply()
            })
            .subscribeOn(Schedulers.io())


    override fun updateTripName(tripId: Long, name: String): Completable {
        TODO("not implemented")
    }

    private fun createNewTrip(): TripDataModel {
        val time = LocalDateTime.now()

        return TripDataModel(
            id = time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
            name = "",
            startDate = time,
            endDate = null,
            distance = 0f
        )
    }

    private fun updateActualTrip(): Completable {
        return Singles.zip(
            tripDao.getOngoing(),
            Single.fromCallable { sharedPreferences.getFloat(ACTUAL_DISTANCE, 0f) }
        ) { trip, distance -> trip.copy(
            distance = distance,
            endDate = LocalDateTime.now()
        ) }
            .flatMapCompletable { trip -> Completable.fromAction { tripDao.update(trip) } }
    }
}
