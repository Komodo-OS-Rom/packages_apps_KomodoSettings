/*
 * Copyright (C) 2020 Komodo-OS-Rom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.komodo.settings.categories;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;

import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;

import com.android.internal.logging.nano.MetricsProto;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.search.SearchIndexable;
import com.android.settings.search.BaseSearchIndexProvider;

public class About extends SettingsPreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
            addPreferencesFromResource(R.xml.komodo_settings_about);
            PreferenceCategory maintainers = (PreferenceCategory)findPreference("maintainers");
            String[] maintainers_title = getResources().getStringArray(R.array.maintainers_title);
            String[] maintainers_devices = getResources().getStringArray(R.array.maintainers_devices);
            String[] maintainers_url = getResources().getStringArray(R.array.maintainers_url);
		for (int i = 0; i < maintainers_title.length; i++) {
            Preference maintainer = new Preference(getPrefContext());
            final String maintainer_url = maintainers_url[i];
            maintainer.setIcon(R.drawable.ic_devs_phone);
            maintainer.setTitle(maintainers_title[i]);
            maintainer.setSummary(String.format(getString(R.string.maintainer_description), maintainers_devices[i]));
            maintainer.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

     @Override
     public boolean onPreferenceClick(Preference preference) {
             getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(maintainer_url)));
             return true;
           }
     });
            maintainers.addPreference(maintainer);
        }
     }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.KOMODO_SETTINGS;
    }
    
    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider(R.xml.komodo_settings_about);
}
