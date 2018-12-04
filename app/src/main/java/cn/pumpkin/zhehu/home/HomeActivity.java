package cn.pumpkin.zhehu.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import cn.pumpkin.zhehu.R;
import cn.pumpkin.zhehu.activity.BaseFloatActivity;

public class HomeActivity extends BaseFloatActivity implements View.OnClickListener{

    private ImageView mBackUpView;
    @Override
    public void initActionBar(View view) {
        mBackUpView=view.findViewById(R.id.backup);
        mBackUpView.setOnClickListener(this);
    }

    @Override
    public void floatAction() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.backup:{
                finish();
            }
                break;
        }
    }
}
