package com.pgkk.ui.video;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.pgkk.R;
import com.pgkk.common.utils.DateUtils;
import com.pgkk.common.utils.image.ImageLoader;
import com.pgkk.data.model.Video;
import com.pgkk.ui.base.adapter.BaseRecycerViewAdapter;

import java.util.List;

/**
 * Created by tanxueze on 2017/12/28.
 */
public class VideoAdapter extends BaseRecycerViewAdapter<Video.ItemListBean, RecyclerView.ViewHolder> {

    public VideoAdapter(Context context, List<Video.ItemListBean> list) {
        super(context, list);
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType().equals("video")) {
            return R.layout.item_video;
        } else {
            return R.layout.item_null;
        }
    }

    @Override
    public RecyclerView.ViewHolder getCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == R.layout.item_video) {
            return new MyHolder(inflater.inflate(viewType, parent, false));
        }
        return new TopHolder(inflater.inflate(viewType, parent, false));
    }

    @Override
    public void getBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Video.ItemListBean itemListBean = list.get(position);
        if (holder instanceof MyHolder) {
            MyHolder myHolder = (MyHolder) holder;
            ViewCompat.setTransitionName(myHolder.imageView, String.valueOf(position) + "_image");
            Video.ItemListBean.DataBean data = itemListBean.getData();

            ImageLoader.displayImage((Activity) context, data.getCover().getDetail(), myHolder.imageView);

            myHolder.itemView.setOnClickListener(v -> {
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v, myHolder);
                }
            });

            myHolder.textView.setText(data.getTitle());
            myHolder.description.setText("#" + data.getCategory() + " " + " / " + " " + DateUtils.formatTime2(data.getDuration()));
        }
    }


    public static class MyHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView description;
        public ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = ((TextView) itemView.findViewById(R.id.video_title));
            description = ((TextView) itemView.findViewById(R.id.description));
            imageView = ((ImageView) itemView.findViewById(R.id.img));
        }
    }


    public static class TopHolder extends RecyclerView.ViewHolder {
        public TopHolder(View itemView) {
            super(itemView);
        }
    }

}