package hu.naturlecso.distancetracker.data.action

import android.app.PendingIntent
import android.content.SharedPreferences
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import hu.naturlecso.distancetracker.data.service.NotificationService
import hu.naturlecso.distancetracker.data.util.Preferences.ACTUAL_DISTANCE
import hu.naturlecso.distancetracker.data.util.Preferences.TRIP_IN_PROGRESS
import hu.naturlecso.distancetracker.domain.action.TripAction
import io.reactivex.Completable

class DefaultTripAction(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val locationPendingIntent: PendingIntent,
    private val sharedPreferences: SharedPreferences,
    private val notificationService: NotificationService
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
            .andThen(Completable.fromAction { notificationService.sendNotification() })
            .andThen(Completable.fromAction { sharedPreferences
                .edit()
                .putBoolean(TRIP_IN_PROGRESS, true)
                .apply()
            })
    }

    override fun endTrip(): Completable =
        Completable.fromAction { fusedLocationProviderClient.removeLocationUpdates(locationPendingIntent) }
            .andThen(Completable.fromAction { notificationService.removeNotification() })
            .andThen(Completable.fromAction { sharedPreferences
                .edit()
                .putFloat(ACTUAL_DISTANCE, 0f)
                .putBoolean(TRIP_IN_PROGRESS, false)
                .apply()
            })


    override fun updateTripName(tripId: Long, name: String): Completable {
        TODO("not implemented")
    }
}
