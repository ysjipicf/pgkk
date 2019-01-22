package com.pgkk.ui.meizi;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgkk.R;
import com.pgkk.common.utils.image.ImageLoader;
import com.pgkk.data.model.Meizi;

import java.util.List;

/**
 * Created by tanxueze on 2017/12/28.
 */

public class MeiziAdapter extends BaseQuickAdapter<Meizi.ResultsBean, BaseViewHolder> {

    public MeiziAdapter(List<Meizi.ResultsBean> data) {
        super(R.layout.item_feed, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Meizi.ResultsBean dataBean) {
        baseViewHolder.setText(R.id.tv_nickname, dataBean.getWho());
        baseViewHolder.getView(R.id.tv_label).setVisibility(View.GONE);
        ImageView ivImage = baseViewHolder.getView(R.id.iv_content);
        int width = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params = ivImage.getLayoutParams();
        //设置图片的相对于屏幕的宽高比
        params.width = width / 2;
        params.height = (int) (400 + Math.random() * 400);
        ivImage.setLayoutParams(params);

        ImageLoader.displayImage((Activity) mContext, dataBean.getUrl(), (ImageView) baseViewHolder.getView(R.id.iv_content), 0, 0);
    }

}
