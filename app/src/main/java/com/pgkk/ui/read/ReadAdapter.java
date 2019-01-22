package com.pgkk.ui.read;

import android.app.Activity;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgkk.R;
import com.pgkk.common.utils.DateUtils;
import com.pgkk.common.utils.image.ImageLoader;
import com.pgkk.data.model.Music;
import com.pgkk.data.model.Read;

import java.util.List;

/**
 * Created by tanxueze on 2017/12/28.
 */

public class ReadAdapter extends BaseQuickAdapter<Read.DataBean, BaseViewHolder> {

    public ReadAdapter(List<Read.DataBean> data) {
        super(R.layout.item_read, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Read.DataBean dataBean) {
        baseViewHolder.setText(R.id.title, dataBean.getTitle());
        baseViewHolder.setText(R.id.type, dataBean.getTag_list().size() == 0?"- 阅读 -":"- "+dataBean.getTag_list().get(0).getTitle()+" -");
        baseViewHolder.setText(R.id.author,dataBean.getAuthor() !=null ? "文 / "+dataBean.getAuthor().getUser_name() : "佚名");
        baseViewHolder.setText(R.id.subhead, dataBean.getForward());
        baseViewHolder.setText(R.id.post_time, DateUtils.formatDate2String(dataBean.getPost_date()));
        ImageLoader.displayImage((Activity) mContext, dataBean.getImg_url(), (ImageView) baseViewHolder.getView(R.id.image));
    }

}
