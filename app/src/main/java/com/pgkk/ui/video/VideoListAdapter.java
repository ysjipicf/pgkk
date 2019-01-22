package com.pgkk.ui.video;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgkk.R;
import com.pgkk.common.utils.DateUtils;
import com.pgkk.common.utils.image.ImageLoader;
import com.pgkk.data.model.Video;
import com.pgkk.ui.base.adapter.BaseRecycerViewAdapter;
import com.pgkk.ui.video.play.SampleVideo;
import com.pgkk.ui.video.play.SwitchVideoModel;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanxueze on 2017/12/28.
 */
public class VideoListAdapter extends BaseQuickAdapter<Video.ItemListBean, BaseViewHolder> {

    OrientationUtils orientationUtils;

    public VideoListAdapter(List<Video.ItemListBean> list) {
        super(R.layout.item_video_list, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, Video.ItemListBean item) {
        com.pgkk.ui.video.play.SampleVideo video_player =helper.getView(R.id.video_player);

        //增加封面
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        ImageLoader.displayImage((Activity) mContext, item.getData().getCover().getDetail(), imageView);
        video_player.setThumbImageView(imageView);

        //增加title
        video_player.getTitleTextView().setVisibility(View.VISIBLE);
        video_player.getTitleTextView().setText(item.getData().getTitle());

        //设置返回键
        video_player.getBackButton().setVisibility(View.GONE);
        //设置旋转
        orientationUtils = new OrientationUtils((Activity) mContext, video_player);

        //设置全屏按键功能
        video_player.getFullscreenButton().setVisibility(View.GONE);
        //是否可以滑动调整
        video_player.setIsTouchWiget(true);

        new Thread(() -> {
            try {
               video_player.setUp(getRedirectUrl(item.getData().getPlayUrl()), true, "");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }

    private String getRedirectUrl(String path) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(path)
                .openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(5000);
        String headerField = conn.getHeaderField("Location");
        Log.e("oye",headerField);
        return headerField;
    }

}