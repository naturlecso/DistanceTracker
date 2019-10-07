package hu.naturlecso.distancetracker.data.store

import com.f2prateek.rx.preferences2.RxSharedPreferences
import hu.naturlecso.distancetracker.data.util.Preferences.DISTANCE_UNIT
import hu.naturlecso.distancetracker.data.util.toFlowable
import hu.naturlecso.distancetracker.domain.model.DistanceUnit
import hu.naturlecso.distancetracker.domain.store.SettingsStore
import io.reactivex.Flowable

class DefaultSettingsStore(
    private val rxSharedPreferences: RxSharedPreferences
) : SettingsStore {

    override fun getDistanceUnit(): Flowable<DistanceUnit> = rxSharedPreferences
        .getBoolean(DISTANCE_UNIT, false)
        .toFlowable()
        .map { if(it) { DistanceUnit.IMPERIAL } else { DistanceUnit.METRIC }
    }
}
