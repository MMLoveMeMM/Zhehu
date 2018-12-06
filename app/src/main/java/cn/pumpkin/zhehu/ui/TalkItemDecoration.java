package cn.pumpkin.zhehu.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/6 19:20
 * @des: 自定义分割线
 * @see {@link }
 */

public class TalkItemDecoration extends RecyclerView.ItemDecoration{
    /**
     *
     * @param outRect 边界
     * @param view recyclerView ItemView
     * @param parent recyclerView
     * @param state recycler 内部数据管理
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //设定底部边距为1px
        outRect.set(0, 0, 0, 1);
    }

}
