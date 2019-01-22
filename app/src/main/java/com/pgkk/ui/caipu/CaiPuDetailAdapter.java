package com.pgkk.ui.caipu;

import android.app.Activity;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgkk.R;
import com.pgkk.common.utils.image.ImageLoader;
import com.pgkk.data.model.CaiPu;

import java.util.List;


public class CaiPuDetailAdapter extends BaseQuickAdapter<CaiPu.ResultBean.DataBean.StepsBean,BaseViewHolder> {

    public CaiPuDetailAdapter(List<CaiPu.ResultBean.DataBean.StepsBean> data) {
        super(R.layout.item_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CaiPu.ResultBean.DataBean.StepsBean dataBean) {

        baseViewHolder.setText(R.id.tv_content,dataBean.getStep());

        ImageLoader.displayImage((Activity) mContext, dataBean.getImg(), (ImageView) baseViewHolder.getView(R.id.iv_content), 0, 0);

    }
}
