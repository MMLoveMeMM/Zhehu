package cn.pumpkin.zhehu.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;

import cn.pumpkin.zhehu.R;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/11/27 20:32
 * @des:
 * @see {@link }
 */

public class BaseActivity extends FragmentActivity {

    // private DialogLoading loading;
    protected Activity activity;
    protected Toast mToast = null;

    public interface OnCompletedListenter {
        void onCompleted();

        void onError();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        activity = this;

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

}
