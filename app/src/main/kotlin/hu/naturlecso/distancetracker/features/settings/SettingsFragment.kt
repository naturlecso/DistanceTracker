package hu.naturlecso.distancetracker.features.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import hu.naturlecso.distancetracker.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey)
    }
}
