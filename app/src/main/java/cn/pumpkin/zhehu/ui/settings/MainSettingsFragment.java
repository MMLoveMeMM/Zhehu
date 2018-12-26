package cn.pumpkin.zhehu.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.widget.Toast;

import cn.pumpkin.log.ZHLog;
import cn.pumpkin.zhehu.R;
/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/21 11:53
 * @des:
 * @see {@link }
 */
public class MainSettingsFragment extends PreferenceFragment {
    // TODO: Rename parameter arguments, choose names that match

    private PreferenceScreen mPreference;
    private PreferenceScreen mPreference1;

    // TODO: Rename and change types and number of parameters
    public static MainSettingsFragment newInstance() {
        MainSettingsFragment fragment = new MainSettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.mainsettings_preference);

        mPreference= (PreferenceScreen) findPreference("userinfo_key");
        mPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ZHLog.d("wokao_____________________________");
                return false;
            }
        });

        /*mPreference1= (PreferenceScreen) findPreference("tost_key");
        mPreference1.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getActivity(),"&&&&&&&&&&&&&",Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/
    }


}
