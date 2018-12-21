package cn.pumpkin.zhehu.ui.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import cn.pumpkin.net.RetrofitManager;
import cn.pumpkin.zhehu.R;
import rx.Subscriber;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/11/27 20:32
 * @des: 这个用于做设置界面
 * @see {@link }
 */

public class BaseActivity extends FragmentActivity {

    protected static final int DEFAULT_LAYOUT = R.layout.activity_base_fragement;
    // private DialogLoading loading;
    protected Activity activity;
    protected Toast mToast = null;
    private LinearLayout mContentView;


    public CompositeSubscription mCompositeSubscription;

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
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void setContentView(int layoutResID) {
        if(layoutResID!=DEFAULT_LAYOUT){
            super.setContentView(layoutResID);
        }else {
            super.setContentView(DEFAULT_LAYOUT);
        }
    }

    /**
     * 创建观察者
     *
     * @param onNext
     * @param <T>
     * @return
     */
    public <T> Subscriber zheHuSubscriber(final Action1<? super T> onNext, final OnCompletedListenter listenter) {
        return new Subscriber<T>() {

            @Override
            public void onCompleted() {
                // hideLoadingDialog();
                if (listenter != null) {
                    listenter.onCompleted();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof RetrofitManager.HttpException) {
                    RetrofitManager.HttpException exception = (RetrofitManager.HttpException) e;
                    showToast(exception.message);
                } else if (e instanceof SocketTimeoutException) {
                    showToast(e.getMessage());
                } else if (e instanceof ConnectException) {
                    showToast(e.getMessage());
                }
                // Logutil.e(TAG, String.valueOf(e.getMessage()), e);
                if (listenter != null) {
                    listenter.onError();
                }
                // hideLoadingDialog();
            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    onNext.call(t);
                }
            }
        };
    }

    public void showToast(String info) {
        Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();
    }
}
