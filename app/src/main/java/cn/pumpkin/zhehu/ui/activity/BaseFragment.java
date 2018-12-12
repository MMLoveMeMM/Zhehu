package cn.pumpkin.zhehu.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.githang.statusbar.StatusBarCompat;

import cn.pumpkin.zhehu.R;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/11/27 20:34
 * @des:
 * @see {@link }
 */

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(getActivity(), getActivity().getResources().getColor(R.color.colorPrimary), true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_main, container, false);
    }
}
