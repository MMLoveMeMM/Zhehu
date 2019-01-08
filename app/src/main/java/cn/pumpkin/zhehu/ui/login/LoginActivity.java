package cn.pumpkin.zhehu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

// import com.netease.nim.uikit.NimUIKit;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

import cn.pumpkin.zhehu.R;
import cn.pumpkin.zhehu.ZHCache;
import cn.pumpkin.zhehu.ui.activity.BaseActivity;
import cn.pumpkin.zhehu.ui.home.HomeActivity;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/11/27 20:36
 * @des: APP 启动首页
 * @see {@link }
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mUserBtn;
    private ImageView mNUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        NIMClient.init(getApplicationContext(), null, null);
        initView();
    }

    void initView() {
        mUserBtn = (ImageView) findViewById(R.id.userlogin);
        mUserBtn.setOnClickListener(this);
        mNUserBtn = (ImageView) findViewById(R.id.youkelogin);
        mNUserBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.userlogin: {
                /**
                 * 登入网易云
                 * */
                loginNetSim();
            }
            break;
            case R.id.youkelogin: {

            }
            break;
            default:
                break;
        }
    }

    /**
     * 登录事件响应函数
     */
    private void loginNetSim() {
        /**
         * 这个账号信息需要在登入后,服务器下发给用户APP端来登入
         * */
        LoginInfo info = new LoginInfo("4301000"/*accountEdit.getText().toString().toLowerCase()*/, "123456"/*pswEdit.getText().toString()*/); // config...
        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo loginInfo) {
                        ZHCache.setAccount("4301000"/*accountEdit.getText().toString().toLowerCase()*/);
                        // NimUIKit.setAccount("4301000"/*accountEdit.getText().toString().toLowerCase()*/);
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailed(int i) {

                    }

                    @Override
                    public void onException(Throwable throwable) {

                    }
                    // overwrite methods
                };
        NIMClient.getService(AuthService.class).login(info)
                .setCallback(callback);
    }
}
