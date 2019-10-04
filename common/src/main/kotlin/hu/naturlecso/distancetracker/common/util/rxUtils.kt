package hu.naturlecso.distancetracker.common.util

import io.reactivex.disposables.Disposable

fun Disposable?.disposeIfNeeded() {
    if (this != null && !isDisposed) {
        dispose()
    }
}
