package hu.naturlecso.distancetracker.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import hu.naturlecso.distancetracker.R
import hu.naturlecso.distancetracker.common.navigation.NavigationCommand
import hu.naturlecso.distancetracker.common.navigation.Navigator
import hu.naturlecso.distancetracker.common.util.disposeIfNeeded
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)

        disposable = navigator.navigationEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is NavigationCommand.To -> navController.navigate(it.directions)
                    is NavigationCommand.Back -> navController.navigateUp()
                }
            }
    }

    override fun onDestroy() {
        disposable.disposeIfNeeded()

        super.onDestroy()
    }
}
