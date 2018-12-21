package cn.pumpkin.zhehu.ui.settings;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
// import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.pumpkin.zhehu.R;
import cn.pumpkin.zhehu.ui.activity.BaseFragment;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/21 14:23
 * @des:
 * @see {@link }
 */

public class UserSettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_main, container, false);
    }

}
