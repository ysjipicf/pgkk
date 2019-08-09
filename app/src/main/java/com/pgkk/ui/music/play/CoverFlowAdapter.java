package com.pgkk.ui.music.play;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.pgkk.R;
import com.pgkk.common.utils.AppLog;
import com.pgkk.common.utils.image.ImageLoader;
import com.pgkk.data.model.Music;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanxueze on 2018/1/12.
 */

public class CoverFlowAdapter  extends PagerAdapter {
    private List<Music.TracksBean> list;
    private LayoutInflater inflater;
    public Context context;
    private List<WeakReference<View>> viewList;

    public CoverFlowAdapter(List<Music.TracksBean> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        viewList = new ArrayList<WeakReference<View>>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        // 从废弃的里去取 取到则使用 取不到则创建
        if (viewList.size() > 0) {
            if (viewList.get(0) != null) {
                view = initView(viewList.get(0).get(), position);
                viewList.remove(0);
            }
        }
        view = initView(null, position);

        container.addView(view);

        return view;
    }

    private View initView(View view, int position) {
        MyHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.item_play_music, null);
            holder = new MyHolder(view);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        /**
         * 初始化数据
         */
        if (list != null && position < list.size()) {
            Music.TracksBean musicPlayerItem = list.get(position);
            ImageLoader.displayImage((Activity) context,musicPlayerItem.getSongphoto(),holder.bgImage);
            initColor(holder, musicPlayerItem);
        }
        return view;
    }


    int color = 0xffffcc00;

    private void initColor(MyHolder holder, Music.TracksBean tracksBean) {
        ImageLoader.displayImage((Activity) context,tracksBean.getSongphoto(),new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Palette.from(resource).generate(palette -> {
                    try {
                        color = palette.getLightMutedSwatch().getRgb();
                    } catch (Exception e) {
                        AppLog.e(e.getMessage());
                    }
                    holder.container.setBackgroundColor(color);
                });
            }
        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            View view = (View) object;
            container.removeView(view);
            viewList.add(new WeakReference<View>(view));
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }


    public static final class MyHolder implements View.OnClickListener {

        @BindView(R.id.cover_img)
        ImageView bgImage;
        @BindView(R.id.container)
        LinearLayout container;
        public View view;

        public MyHolder(View itemView) {
            ButterKnife.bind(itemView);
            view = itemView;
            bgImage = ((ImageView) findViewById(R.id.cover_img));
            container = ((LinearLayout) findViewById(R.id.container));
        }

        public View findViewById(int id) {
            return view.findViewById(id);
        }

        @Override
        public void onClick(View v) {
            int positon = (int) v.getTag();
        }


    }

}
