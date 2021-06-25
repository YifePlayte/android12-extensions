package dev.kdrag0n.android12ext.core.data

import android.content.Context
import android.content.SharedPreferences
import de.Maxr1998.modernpreferences.PreferenceScreen
import de.Maxr1998.modernpreferences.helpers.screen
import dev.kdrag0n.android12ext.core.xposed.XposedPreferenceProvider

class SettingsRepository(
    context: Context,
) {
    val deviceStorageContext: Context = context.createDeviceProtectedStorageContext()

    val prefs: SharedPreferences = deviceStorageContext
        .getSharedPreferences(XposedPreferenceProvider.DEFAULT_PREFS, Context.MODE_PRIVATE)

    inline fun prefScreen(block: PreferenceScreen.Builder.() -> Unit) =
        screen(deviceStorageContext, block)
}
