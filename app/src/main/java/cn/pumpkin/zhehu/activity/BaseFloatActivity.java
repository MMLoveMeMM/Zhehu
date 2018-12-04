package cn.pumpkin.zhehu.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.githang.statusbar.StatusBarCompat;

import cn.pumpkin.zhehu.R;

public abstract class BaseFloatActivity extends AppCompatActivity {

    public abstract void initActionBar(View view);
    public abstract void floatAction();

    private LinearLayout mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
        setContentView(R.layout.activity_base_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatAction();
            }
        });
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        if ( R.layout.activity_base_main == layoutResID) {
            super.setContentView(R.layout.activity_base_main);
            mContentView = (LinearLayout) findViewById(R.id.layout_center);
            mContentView.removeAllViews();

        } else if (layoutResID != R.layout.activity_base_main) {
            View addView = LayoutInflater.from(this).inflate(layoutResID, null);
            mContentView.addView(addView);
        }
    }

}
