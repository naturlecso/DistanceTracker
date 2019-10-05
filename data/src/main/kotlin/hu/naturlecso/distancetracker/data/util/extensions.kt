package hu.naturlecso.distancetracker.data.util

import com.f2prateek.rx.preferences2.Preference

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

fun <T> Preference<T>.toFlowable(): Flowable<T> {
    return asObservable().toFlowable(BackpressureStrategy.BUFFER)
}
