package cn.pumpkin.zhehu.ui.settings;

import android.os.Bundle;

import cn.pumpkin.zhehu.R;
import cn.pumpkin.zhehu.ui.activity.BaseActivity;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/21 11:53
 * @des: 设置主页面
 * @see {@link }
 */

public class SettingsActivity extends BaseActivity {

    private MainSettingsFragment mMainSettingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(DEFAULT_LAYOUT);
        initFragements();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initFragements(){
        mMainSettingsFragment = MainSettingsFragment.newInstance();
        /*getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mMainSettingsFragment, mMainSettingsFragment.getClass().getName())
                .show(mMainSettingsFragment)
                .commit();*/
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, mMainSettingsFragment).commit();
    }
}
