package com.pgkk.ui.music;

import android.app.Activity;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgkk.R;
import com.pgkk.common.utils.image.ImageLoader;
import com.pgkk.data.model.Music;

import java.util.List;

/**
 * Created by tanxueze on 2017/12/28.
 */

public class MusicAdapter extends BaseQuickAdapter<Music, BaseViewHolder> {

    public MusicAdapter(List<Music> data) {
        super(R.layout.item_music, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Music dataBean) {
        baseViewHolder.setText(R.id.music_title, dataBean.getMname());
        baseViewHolder.setText(R.id.music_description, dataBean.getMdesc());
        baseViewHolder.setText(R.id.music_pay_cont,dataBean.getPlay_count()+"");
        ImageLoader.displayImage((Activity) mContext, dataBean.getOphoto(), (ImageView) baseViewHolder.getView(R.id.music_img), 0, 0);
    }

}
