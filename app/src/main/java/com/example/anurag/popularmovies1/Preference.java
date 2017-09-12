package com.example.anurag.popularmovies1;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by anurag on 10/10/2016.
 */

public class Preference extends PreferenceActivity  implements android.preference.Preference.OnPreferenceChangeListener{

    private AppCompatDelegate mDelegate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        addPreferencesFromResource(R.xml.pref);
        bindPreferenceSummaryToValue(findPreference(getString(R.string.list_pref_key)));
    }

    private void bindPreferenceSummaryToValue(android.preference.Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(this);

        // Trigger the listener immediately with the preference's
        // current value.
        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }



    @Override
    public boolean onPreferenceChange(android.preference.Preference preference, Object o) {
        String stringValue = o.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }
        return true;
    }
}
