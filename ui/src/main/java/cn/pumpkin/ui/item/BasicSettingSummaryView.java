package cn.pumpkin.ui.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.pumpkin.ui.R;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/29 11:55
 * @des:
 * @see {@link }
 */

public class BasicSettingSummaryView extends LinearLayout {

    private TextView mTitleView;
    private TextView mSummaryView;
    private ImageView mFrontImage;
    private ImageView mEndImage;

    public BasicSettingSummaryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.basic_setting_view_summary_layout, this);
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER_VERTICAL);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BasicSettingViewStyle);
        String title = typedArray.getString(R.styleable.BasicSettingViewStyle_substance);
        String summary = typedArray.getString(R.styleable.BasicSettingViewStyle_summary);
        Drawable frontSrc = typedArray.getDrawable(R.styleable.BasicSettingViewStyle_frontSrc);
        Drawable endSrc = typedArray.getDrawable(R.styleable.BasicSettingViewStyle_endSrc);
        int defaultTitleColor = context.getColor(R.color.titleColor);
        int defaultSummaryColor = context.getColor(R.color.summaryColor);
        int titleColor = typedArray.getColor(R.styleable.BasicSettingViewStyle_titleColor,defaultTitleColor);
        int summaryColor = typedArray.getColor(R.styleable.BasicSettingViewStyle_summaryColor,defaultSummaryColor);
        typedArray.recycle();

        mTitleView = (TextView) this.findViewById(R.id.title);
        mSummaryView = (TextView) this.findViewById(R.id.summary);

        mTitleView.setText(title);
        mSummaryView.setText(summary);
        mTitleView.setTextColor(titleColor);
        mSummaryView.setTextColor(summaryColor);
        mFrontImage = (ImageView)findViewById(R.id.frontSrc);
        if(frontSrc!=null) {
            mFrontImage.setImageDrawable(frontSrc);
        }

        mEndImage = (ImageView)findViewById(R.id.endSrc);
        if(endSrc!=null) {
            mEndImage.setImageDrawable(endSrc);
        }else{
            mEndImage.setVisibility(View.GONE);
        }
    }
}
