package cn.pumpkin.zhehu.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;

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

    public MainSettingsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainSettingsFragment newInstance() {
        MainSettingsFragment fragment = new MainSettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.mainsettings_preference);

    }


}
