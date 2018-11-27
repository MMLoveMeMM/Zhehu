package cn.pumpkin.zhehu.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import cn.pumpkin.zhehu.R;
import cn.pumpkin.zhehu.activity.BaseActivity;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/11/27 20:36
 * @des: APP启动首页
 * @see {@link }
 */

public class AppActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mUserBtn;
    private ImageView mNUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        initView();
    }
    void initView() {
        mUserBtn=(ImageView)findViewById(R.id.userlogin);
        mUserBtn.setOnClickListener(this);
        mNUserBtn=(ImageView)findViewById(R.id.youkelogin);
        mNUserBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.userlogin: {

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
