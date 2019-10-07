package hu.naturlecso.distancetracker.features

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import hu.naturlecso.distancetracker.R
import hu.naturlecso.distancetracker.domain.model.DistanceUnit
import hu.naturlecso.distancetracker.domain.model.Trip
import org.threeten.bp.format.DateTimeFormatter

private const val METER_TO_KM = 1000
private const val METER_TO_FT = 0.3048f
private const val METER_TO_MI = 1609.344f
private const val FT_TO_MI = 5280

@BindingAdapter("animate")
fun LottieAnimationView.bindRunAnimation(animate: Boolean?) {
    animate ?: return

    if  (animate) {
        playAnimation()
    } else {
        cancelAnimation()
        progress = 0f
    }
}

@BindingAdapter("duration")
fun TextView.bindDuration(trip: Trip?) {
    trip ?: return

    val startTime = trip.startDate.format(DateTimeFormatter.ISO_DATE_TIME)
    val endTime = trip.endDate?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

    text = if (endTime == null) {
        context.getText(R.string.trip_ongoing)
    } else {
        "$startTime - $endTime"
    }
}

@BindingAdapter(value = ["distance", "unit"])
fun TextView.bindDistanceWithUnit(distance: Float?, unit: DistanceUnit?) {
    distance ?: return
    unit ?: return

    text = when (unit) {
        DistanceUnit.METRIC -> {
            val km = distance / METER_TO_KM

            if (km < 1) {
                formatDistance(distance, context.getText(R.string.trip_distance_unit_m).toString())
            } else {
                formatDistance(km, context.getText(R.string.trip_distance_unit_km).toString())
            }
        }
        DistanceUnit.IMPERIAL -> {
            val ft = distance / METER_TO_FT

            if (ft / FT_TO_MI < 1) {
                formatDistance(ft, context.getText(R.string.trip_distance_unit_ft).toString())
            } else {
                val mi = distance / METER_TO_MI
                formatDistance(mi, context.getText(R.string.trip_distance_unit_mi).toString())
            }
        }
    }
}

private fun formatDistance(distance: Float, unit: String) = "%.2f $unit".format(distance)
