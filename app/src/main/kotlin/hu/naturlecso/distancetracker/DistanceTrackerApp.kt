package hu.naturlecso.distancetracker

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import hu.naturlecso.distancetracker.data.dataModule
import hu.naturlecso.distancetracker.features.featureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class DistanceTrackerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        if  (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@DistanceTrackerApp)

            modules(appModule)
            modules(dataModule)
            modules(featureModule)
        }
    }
}
