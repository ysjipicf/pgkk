package com.pgkk.ui.news;

import android.app.Activity;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.pgkk.R;
import com.pgkk.common.utils.image.ImageLoader;
import com.pgkk.data.model.News;

import java.util.List;

/**
 * Created by tanxueze on 2017/12/28.
 */

public class NewsAdapter extends BaseMultiItemQuickAdapter<NewsAdapter.MultipleItem, BaseViewHolder> {


    public NewsAdapter(List data) {
        super(data);
        addItemType(MultipleItem.TIEM_1, R.layout.item_news);
        addItemType(MultipleItem.ITEM_2, R.layout.item_news_1);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MultipleItem myMultipleItem) {
        baseViewHolder.setText(R.id.title_tv, myMultipleItem.getData().getTitle());
        baseViewHolder.setText(R.id.author_name_tv, myMultipleItem.getData().getAuthor_name());
        baseViewHolder.setText(R.id.date_tv, myMultipleItem.getData().getDate());

        switch (baseViewHolder.getItemViewType()) {
            case MultipleItem.TIEM_1:
                ImageLoader.displayImage((Activity) mContext, myMultipleItem.getData().getThumbnail_pic_s(), (ImageView) baseViewHolder.getView(R.id.iv_0), 0, 0);
                break;
            case MultipleItem.ITEM_2:
                ImageLoader.displayImage((Activity) mContext, myMultipleItem.getData().getThumbnail_pic_s(), (ImageView) baseViewHolder.getView(R.id.iv_0), 0, 0);
                ImageLoader.displayImage((Activity) mContext, myMultipleItem.getData().getThumbnail_pic_s02(), (ImageView) baseViewHolder.getView(R.id.iv_1), 0, 0);
                ImageLoader.displayImage((Activity) mContext, myMultipleItem.getData().getThumbnail_pic_s03(), (ImageView) baseViewHolder.getView(R.id.iv_2), 0, 0);
                break;
        }
    }


    public static class MultipleItem implements MultiItemEntity {
        public static final int TIEM_1 = 1;
        public static final int ITEM_2 = 2;
        private int itemType;
        private News.ResultBean.DataBean data;

        public MultipleItem(int itemType, News.ResultBean.DataBean data) {
            this.itemType = itemType;
            this.data = data;
        }

        @Override
        public int getItemType() {
            return itemType;
        }

        public News.ResultBean.DataBean getData() {
            return data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MultipleItem that = (MultipleItem) o;

            if (itemType != that.itemType) return false;
            return data != null ? data.equals(that.data) : that.data == null;
        }

        @Override
        public int hashCode() {
            int result = itemType;
            result = 31 * result + (data != null ? data.hashCode() : 0);
            return result;
        }
    }


}
