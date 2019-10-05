package hu.naturlecso.distancetracker.features

import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView

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
