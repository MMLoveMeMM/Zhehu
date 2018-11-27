package cn.pumpkin.zhehu.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        activity = this;

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

}
