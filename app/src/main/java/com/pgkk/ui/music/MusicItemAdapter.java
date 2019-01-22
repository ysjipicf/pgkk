package com.pgkk.ui.music;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgkk.R;
import com.pgkk.data.model.Music;

import java.util.List;

/**
 * Created by tanxueze on 2018/1/12.
 */

public class MusicItemAdapter

        extends BaseQuickAdapter<Music.TracksBean,BaseViewHolder> {

    public MusicItemAdapter(@Nullable List<Music.TracksBean> data) {
        super(R.layout.item_music_detial, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Music.TracksBean item) {
        helper.setText(R.id.title_num,helper.getPosition()+" ");
        helper.setText(R.id.title,item.getSongname());
        helper.setText(R.id.title_dis,item.getSonger());
    }


//       extends BaseRecycerViewAdapter<Music.TracksBean,RecyclerView.ViewHolder> {
//
//    public MusicItemAdapter(Context context, List<Music.TracksBean> list) {
//        super(context, list);
//    }
//
//    @Override
//    public RecyclerView.ViewHolder getCreateViewHolder(ViewGroup parent, int viewType) {
//        return new MusicItemAdapter.MyViewHolder(                                                                                                                                                                                                                                                                                                                                        i    nflater.inflate(R.layout.item_music_detial, parent, false));
//    }
//
//    @Override
//    public void getBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        MyViewHolder viewHolder = (MyViewHolder) holder;
//        Music.TracksBean tracksBean = list.get(position);
//
//
//        viewHolder.num.setText((position + 1) + "");
//        viewHolder.title.setText(tracksBean.getSongname());
//        viewHolder.dis.setText(tracksBean.getSonger());
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//
//        private final TextView num;
//        private final TextView title;
//        private final TextView dis;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            num = ((TextView) itemView.findViewById(R.id.title_num));
//            title = ((TextView) itemView.findViewById(R.id.title));
//            dis = ((TextView) itemView.findViewById(R.id.title_dis));
//        }
//    }
}
