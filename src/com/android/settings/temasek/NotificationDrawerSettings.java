/*
 * Copyright (C) 2012 Slimroms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.temasek;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.internal.util.temasek.DeviceUtils;

import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.R;

public class NotificationDrawerSettings extends SettingsPreferenceFragment
            implements OnPreferenceChangeListener  {

    public static final String TAG = "NotificationDrawerSettings";
    private static final String PREF_NOTIFICATION_OPTIONS = "options";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.notification_drawer);

        PreferenceScreen prefs = getPreferenceScreen();

        PreferenceCategory additionalOptions =
            (PreferenceCategory) prefs.findPreference(PREF_NOTIFICATION_OPTIONS);

        PackageManager pm = getPackageManager();
        boolean isMobileData = pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY);

        if (!DeviceUtils.isPhone(getActivity())
            || !DeviceUtils.deviceSupportsMobileData(getActivity())) {
            // Nothing for tablets, large screen devices and non mobile devices which doesn't show
            // information in notification drawer.....remove options
            prefs.removePreference(additionalOptions);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }

}
