package cn.pumpkin.zhehu.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.pumpkin.zhehu.R;
import cn.pumpkin.zhehu.bean.TalkDatas;

/**
 * @author: zhibao.Liu
 * @version:
 * @date: 2018/12/4 15:21
 * @des:
 * @see {@link }
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static enum ITEM_TYPE {
        ITEM_TYPE_THEME,
        ITEM_TYPE_INFO
    }

    //数据集
    public List<TalkDatas> mTalkDatas;
    private TextView themeTitle;

    private OnItemClickListener mOnItemClickListener;

    public HomeRecyclerViewAdapter(List<TalkDatas> datas) {
        super();
        this.mTalkDatas = datas;
    }

    public static interface  OnItemClickListener{
        void onItemClick(View view, int positon);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_THEME.ordinal()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_themetalks_adapter, parent, false);
            return new ThemeVideoHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_INFO.ordinal()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_talks_adapter, parent, false);
            return new VideoViewHolder(view);
        }
        return null;
    }

    public int getItemViewType(int position){
        return position % 5 == 0 ? ITEM_TYPE.ITEM_TYPE_THEME.ordinal() : ITEM_TYPE.ITEM_TYPE_INFO.ordinal();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == ITEM_TYPE.ITEM_TYPE_THEME.ordinal()
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ThemeVideoHolder){
            themeTitle.setText("励志");
        }else if (holder instanceof VideoViewHolder){
            ((VideoViewHolder)holder).userIconView.setImageResource(R.drawable.ic_launcher_background);
            ((VideoViewHolder)holder).talkTitleView.setText(mTalkDatas.get(position).getTalkTitle());
            ((VideoViewHolder)holder).talkContentView.setText(mTalkDatas.get(position).getTalkContent());
            ((VideoViewHolder)holder).talkPraisesView.setText(mTalkDatas.get(position).getTalkPraises()+"");
            ((VideoViewHolder)holder).talkCommentsView.setText(mTalkDatas.get(position).getTalkComments()+"");

            if (mOnItemClickListener!=null){
                ((VideoViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(((VideoViewHolder)holder).userIconView, position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mTalkDatas.size();
    }

    public class ThemeVideoHolder extends RecyclerView.ViewHolder {

        public ThemeVideoHolder(View itemView) {
            super(itemView);
            themeTitle = (TextView) itemView.findViewById(R.id.themetitle);
        }
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public ImageView userIconView;
        public TextView talkTitleView;
        public TextView talkContentView;
        public TextView talkPraisesView;
        public TextView talkCommentsView;

        public VideoViewHolder(View itemView) {
            super(itemView);
            userIconView = (ImageView) itemView.findViewById(R.id.usericon);
            talkTitleView = (TextView) itemView.findViewById(R.id.talktitle);
            talkContentView = (TextView) itemView.findViewById(R.id.talkcontent);
            talkPraisesView = (TextView) itemView.findViewById(R.id.talkpraisecnt);
            talkCommentsView = (TextView) itemView.findViewById(R.id.talkcommentcnt);
        }
    }

    public void refresh(){

    }

    public void addMore(List<TalkDatas> datas){
        mTalkDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

}
