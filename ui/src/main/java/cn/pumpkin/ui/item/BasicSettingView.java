package cn.pumpkin.ui.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.pumpkin.ui.R;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/27 20:52
 * @des:
 * @see {@link }
 */

public class BasicSettingView extends LinearLayout {

    private TextView mTextView;

    public BasicSettingView(Context context) {
        super(context);
    }

    public BasicSettingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.basic_setting_view_layout, this);
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER_VERTICAL);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BasicSettingViewStyle);
        String name = typedArray.getString(R.styleable.BasicSettingViewStyle_title);
        String valueHint = typedArray.getString(R.styleable.BasicSettingViewStyle_hint);
        typedArray.recycle();

        mTextView = (TextView) this.findViewById(R.id.title);

    }

}
