package cn.pumpkin.zhehu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
                // loginNetSim();
            }
            break;
            case R.id.youkelogin: {

            }
            break;
            default:
                break;
        }
    }

}
