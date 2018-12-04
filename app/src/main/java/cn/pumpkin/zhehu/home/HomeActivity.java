package cn.pumpkin.zhehu.home;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.pumpkin.zhehu.R;
import cn.pumpkin.zhehu.activity.BaseFloatActivity;
import cn.pumpkin.zhehu.adapter.HomeRecyclerViewAdapter;
import cn.pumpkin.zhehu.bean.TalkDatas;

public class HomeActivity extends BaseFloatActivity implements View.OnClickListener{

    private ImageView mBackUpView;
    private RecyclerView mRecyclerView;

    private HomeRecyclerViewAdapter mAdapter;
    private List<TalkDatas> mTalkDatas;

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

        //通过findViewById拿到RecyclerView实例
        mRecyclerView =findViewById(R.id.recyclerView);
        //设置RecyclerView管理器
        // mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //2.创建一个垂直的线性布局(一个布局管理器layoutManager只能绑定一个Recyclerview)
        GridLayoutManager layoutManager1 = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false);

        //找到RecyclerView，并设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager1);
        mRecyclerView.setHasFixedSize(true);

        //初始化适配器
        mTalkDatas = new ArrayList<>();
        for (int i=0; i<20; i++){
            TalkDatas datas=new TalkDatas();
            datas.setTalkType(1);
            datas.setTalkAuthor("liuzhibao");
            datas.setTalkContent("从文件内容查找不匹配指定字符串的行");
            datas.setTalkPraises(1290+i);
            datas.setTalkComments(120+i);
            mTalkDatas.add(datas);
        }
        mAdapter = new HomeRecyclerViewAdapter(mTalkDatas);
        //设置添加或删除item时的动画，这里使用默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mRecyclerView.setAdapter(mAdapter);

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
