package com.pgkk.ui.caipu;

import android.app.Activity;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgkk.R;
import com.pgkk.common.utils.image.ImageLoader;
import com.pgkk.data.model.CaiPu;
import com.pgkk.data.model.CaiPu.ResultBean;

import java.util.List;



public class SuspensionAdapter extends BaseQuickAdapter<CaiPu.ResultBean.DataBean,BaseViewHolder> {

    public SuspensionAdapter(List<ResultBean.DataBean> data) {
        super(R.layout.item_feed, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ResultBean.DataBean dataBean) {
        baseViewHolder.setText(R.id.tv_nickname,dataBean.getTitle());
        baseViewHolder.setText(R.id.tv_label,dataBean.getTags());
        ImageLoader.displayImage((Activity) mContext, dataBean.getAlbums().get(0), (ImageView)baseViewHolder.getView(R.id.iv_content), 0, 0);
    }
}
