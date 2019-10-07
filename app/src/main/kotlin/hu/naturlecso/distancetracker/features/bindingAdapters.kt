package hu.naturlecso.distancetracker.features

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import hu.naturlecso.distancetracker.R
import hu.naturlecso.distancetracker.domain.model.Trip
import org.threeten.bp.format.DateTimeFormatter

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

@BindingAdapter(value = ["distance", "metric"])
fun TextView.bindDistanceWithUnit(distance: Float?, metric: Boolean) {
    distance ?: return

    val unit = if (metric) {
        context.getText(R.string.trip_distance_unit_km)
    } else {
        context.getText(R.string.trip_distance_unit_mile)
    }

    val formattedDistance = if (metric) {
        distance * 0.001
    } else {
        distance * 0.000621
    }.let { "%.2f".format(it) }

    text = "$formattedDistance $unit"
}
