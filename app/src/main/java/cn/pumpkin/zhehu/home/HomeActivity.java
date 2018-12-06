package cn.pumpkin.zhehu.home;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.pumpkin.zhehu.R;
import cn.pumpkin.zhehu.activity.BaseFloatActivity;
import cn.pumpkin.zhehu.adapter.HomeRecyclerViewAdapter;
import cn.pumpkin.zhehu.adapter.RecyclerViewScrollListener;
import cn.pumpkin.zhehu.bean.TalkDatas;
import cn.pumpkin.zhehu.ui.TalkItemDecoration;

public class HomeActivity extends BaseFloatActivity
        implements View.OnClickListener, RecyclerViewScrollListener.OnRecycleRefreshListener {

    private ImageView mBackUpView;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private HomeRecyclerViewAdapter mAdapter;
    private List<TalkDatas> mTalkDatas;

    private RecyclerViewScrollListener mRecyclerViewScrollListener;
    @Override
    public void initActionBar(View view) {
        mBackUpView = view.findViewById(R.id.backup);
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
        mRecyclerView = findViewById(R.id.recyclerView);
        //设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // mRecyclerView.addItemDecoration(new TalkItemDecoration());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerViewScrollListener = new RecyclerViewScrollListener(this);
        mRecyclerView.addOnScrollListener(mRecyclerViewScrollListener);
        //初始化适配器
        mTalkDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TalkDatas datas = new TalkDatas();
            datas.setTalkType(1);
            datas.setTalkAuthor("liuzhibao");
            datas.setTalkTitle("程序");
            datas.setTalkContent("从文件内容查找不匹配指定字符串的行");
            datas.setTalkPraises(1290 + i);
            datas.setTalkComments(120 + i);
            mTalkDatas.add(datas);
        }
        mAdapter = new HomeRecyclerViewAdapter(mTalkDatas);
        //设置添加或删除item时的动画，这里使用默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE,Color.CYAN);
        mSwipeRefreshLayout.setOnRefreshListener(mRecyclerViewScrollListener);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.backup: {
                finish();
            }
            break;
        }
    }

    @Override
    public void refresh() {
        Toast.makeText(this,"refresh view",Toast.LENGTH_SHORT).show();
        //刷新的接口调
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.refresh();
                mSwipeRefreshLayout.setRefreshing(false); // 刷新进度条ui消失
                mRecyclerViewScrollListener.setLoadDataStatus(false);
            }
        },2000);
    }

    private ProgressDialog pd;
    @Override
    public void loadMore() {
        Toast.makeText(this,"load more view",Toast.LENGTH_SHORT).show();
        //加载更多的接口回调
        pd = new ProgressDialog(this);
        pd.show();
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<TalkDatas> datalists = new ArrayList<>();
                for (int i = 21; i < 30; i++) {
                    TalkDatas datas = new TalkDatas();
                    datas.setTalkType(1);
                    datas.setTalkAuthor("liuzhibao");
                    datas.setTalkTitle("增加");
                    datas.setTalkContent("从文件内容查找不匹配指定字符串的行");
                    datas.setTalkPraises(1290 + i);
                    datas.setTalkComments(120 + i);
                    datalists.add(datas);
                }
                mAdapter.addMore(datalists);
                //设置数据加载结束的监听状态
                mRecyclerViewScrollListener.setLoadDataStatus(false);
                pd.dismiss();
            }
        },2000);
    }
}
