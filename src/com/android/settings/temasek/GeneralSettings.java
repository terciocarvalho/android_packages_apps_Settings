package com.android.settings.temasek;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.PreferenceCategory;
import android.preference.Preference.OnPreferenceChangeListener;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.util.Helpers;

public class GeneralSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String RESTART_SYSTEMUI = "restart_systemui";
    private static final String PRE_SWIPE_TO_SWITCH_SCREEN_DETECTION =
            "full_swipe_to_switch_detection";

    private Preference mRestartSystemUI;
    CheckBoxPreference mFullScreenDetection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.general_settings);

        mRestartSystemUI = findPreference(RESTART_SYSTEMUI);

        mFullScreenDetection = (CheckBoxPreference) findPreference(PRE_SWIPE_TO_SWITCH_SCREEN_DETECTION);
        mFullScreenDetection.setChecked(Settings.System.getInt(getContentResolver(),
                Settings.System.SWIPE_TO_SWITCH_SCREEN_DETECTION, 0) == 1);
        mFullScreenDetection.setOnPreferenceChangeListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mRestartSystemUI) {
            Helpers.restartSystemUI();
        }

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mFullScreenDetection) {
            Settings.System.putInt(getContentResolver(),
                    Settings.System.SWIPE_TO_SWITCH_SCREEN_DETECTION,
                    (Boolean) newValue ? 1 : 0);
            return true;
        }
        return false;
    }
}
