package com.pgkk.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.pgkk.R;
import com.pgkk.common.utils.ReflexObjectUtil;
import com.pgkk.common.utils.image.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity<T> extends Activity {

    int position = 0;
    @BindView(R.id.tv_content)
    TextView tv_content;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    List<T> list;


    String url_key = null;
    String des_key = null;

    List<ImageModel> imageModels = new ArrayList<>();

    List<String> urls = null;
    List<String> dess = null;

    public ViewPagerActivity() {
    }

    public void launch(Context context, List<T> dataBean, int position, String url_key, String des_key) {
        Intent intent = new Intent(context, ViewPagerActivity.class);
        intent.putExtra("dataBean", (Serializable) dataBean);
        intent.putExtra("position", position);
        intent.putExtra("url_key", url_key);
        intent.putExtra("des_key", des_key);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        ButterKnife.bind(this);

        list = (List<T>) getIntent().getSerializableExtra("dataBean");
        position = getIntent().getIntExtra("position", 0);
        url_key = getIntent().getStringExtra("url_key");
        des_key = getIntent().getStringExtra("des_key");

        fromImageModel();

        viewPager.setAdapter(new SamplePagerAdapter());
        viewPager.setCurrentItem(position);
        tv_content.setVisibility(View.GONE);
        if (dess != null) {
            tv_content.setVisibility(View.VISIBLE);
            tv_content.setText(imageModels.get(position).getDes());
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_content.setVisibility(View.GONE);
                if (dess != null) {
                    tv_content.setVisibility(View.VISIBLE);
                    tv_content.setText(imageModels.get(position).getDes());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageModels.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());

            ImageLoader.displayImage((Activity) ViewPagerActivity.this, imageModels.get(position).getUrl(), (ImageView) photoView, 0, 0);

            // Now just add PhotoView to ViewPager and return it
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }


    private void fromImageModel() {
        ReflexObjectUtil reflexObjectUtil = new ReflexObjectUtil();
        urls = url_key != null ? reflexObjectUtil.getValuesByKey(list, url_key) : null;

        dess = des_key != null ? reflexObjectUtil.getValuesByKey(list, des_key) : null;

        if (urls!=null && dess!=null){
            for (int i = 0; i < urls.size(); i++) {
                imageModels.add(new ImageModel(urls.get(i), dess.get(i)));
            }
        } else  if(urls!=null){
            for (int i = 0; i < urls.size(); i++) {
                imageModels.add(new ImageModel(urls.get(i), ""));
            }
        }
    }


    public class ImageModel {
        private String url;
        private String des;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public ImageModel(String url, String des) {
            this.url = url;
            this.des = des;
        }
    }

}
