package cn.pumpkin.ui.item;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import cn.pumpkin.ui.R;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/29 15:58
 * @des:
 * @see {@link }
 */

public class BasicEditVerifyView extends LinearLayout {

    public BasicEditVerifyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.basic_edit_verify_view_layout, this);
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER_VERTICAL);

    }

}
