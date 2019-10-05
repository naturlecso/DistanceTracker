package hu.naturlecso.distancetracker.data.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import com.google.android.gms.location.LocationResult
import hu.naturlecso.distancetracker.data.util.Actions
import hu.naturlecso.distancetracker.data.util.Preferences.ACTUAL_DISTANCE
import hu.naturlecso.distancetracker.data.util.Preferences.LAST_COORDINATES
import org.koin.core.KoinComponent
import org.koin.core.inject

// TODO live notifications
class LocationUpdatesBroadcastReceiver : BroadcastReceiver(), KoinComponent {
    private val sharedPreferences: SharedPreferences by inject()

    override fun onReceive(context: Context, intent: Intent?) {
        intent?.let {
            val action = intent.action
            if (Actions.LOCATION_UPDATES == action) {
                val result = LocationResult.extractResult(intent)

                result?.let {
                    val locations = result.locations

                    calculateActualDistance(locations.last())
                }
            }
        }
    }

    private fun calculateActualDistance(location: Location) {
        val lastCoordinates = sharedPreferences.getString(LAST_COORDINATES, null)

        if (lastCoordinates != null) {
            val latLng = lastCoordinates.split(DELIMITER)

            val previousLocation = Location("previous location").apply {
                latitude = latLng[0].toDouble()
                longitude = latLng[1].toDouble()
            }

            val distance = location.distanceTo(previousLocation)
            val previousDistance = sharedPreferences.getFloat(ACTUAL_DISTANCE, 0f)
            val actualDistance = distance + previousDistance

            sharedPreferences
                .edit()
                .putFloat(ACTUAL_DISTANCE, actualDistance)
                .apply()
        }

        sharedPreferences
            .edit()
            .putString(LAST_COORDINATES, "${location.latitude}$DELIMITER${location.longitude}")
            .apply()
    }

    companion object {
        private const val DELIMITER = ':'
    }
}