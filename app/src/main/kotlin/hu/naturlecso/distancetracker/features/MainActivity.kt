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
import android.content.pm.PackageManager
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.core.app.ActivityCompat
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.google.android.material.snackbar.Snackbar
import hu.naturlecso.distancetracker.BuildConfig
import hu.naturlecso.distancetracker.data.service.NotificationService
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private val navigator: Navigator by inject()
    private val notificationService: NotificationService by inject()

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationService.notificationIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

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

        if (!checkPermissions()) {
            requestPermissions()
        }
    }

    override fun onDestroy() {
        disposable.disposeIfNeeded()

        super.onDestroy()
    }

    private fun checkPermissions(): Boolean {
        return REQUIRED_PERMISSIONS
            .map { ActivityCompat.checkSelfPermission(this, it) }
            .map { it == PackageManager.PERMISSION_GRANTED }
            .all { it }
    }

    private fun requestPermissions() {
        val allPermissionsApproved = checkPermissions()

        if (allPermissionsApproved) {
            Snackbar.make(container, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                .setAction(android.R.string.ok) { triggerPermissionRequest() }
                .show()
        } else {
            triggerPermissionRequest()
        }
    }

    private fun triggerPermissionRequest() {
        ActivityCompat.requestPermissions(
            this,
            REQUIRED_PERMISSIONS,
            REQUEST_PERMISSIONS_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> Timber.i("User interaction was cancelled.")
                grantResults.map { it == PackageManager.PERMISSION_GRANTED }.all { it } -> {
                    // TODO start action
                }
                else -> Snackbar.make(container, R.string.permission_denied_explanation, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.settings) {
                        // Build intent that displays the App settings screen.
                        val intent = Intent().apply {
                            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            data = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        startActivity(intent)
                    }
                    .show()
            }
        }
    }

    companion object {
        private const val REQUEST_PERMISSIONS_REQUEST_CODE = 34

        // TODO maybe ACCESS_BACKGROUND_LOCATION is needed too
        private val REQUIRED_PERMISSIONS = arrayOf(
            ACCESS_FINE_LOCATION
        )
    }
}
