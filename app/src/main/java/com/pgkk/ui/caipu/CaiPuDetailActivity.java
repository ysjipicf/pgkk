package com.pgkk.ui.caipu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pgkk.R;
import com.pgkk.common.utils.image.ImageLoader;
import com.pgkk.data.model.CaiPu;
import com.pgkk.ui.base.BaseActivity;
import com.pgkk.ui.widget.ViewPagerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanxueze on 2017/12/21.
 */

public class CaiPuDetailActivity extends BaseActivity {

    CaiPu.ResultBean.DataBean dataBean;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar_image)
    ImageView toolbar_image;

    @BindView(R.id.tv_content)
    TextView tv_content;

    @BindView(R.id.recycler_list)
    RecyclerView recycler_list;

    CaiPuDetailAdapter detailAdapter;

    LinearLayoutManager linearLayoutManager;

    public static void launch(Context context, CaiPu.ResultBean.DataBean dataBean) {
        Intent intent = new Intent(context, CaiPuDetailActivity.class);
        intent.putExtra("dataBean", dataBean);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caipu_detail);
        ButterKnife.bind(this);
        dataBean = (CaiPu.ResultBean.DataBean) getIntent().getSerializableExtra("dataBean");
        init();
    }

    void init() {
        //设置状态栏为全透明。
//        StatusBarUtil.setTransparent(this);
        _setBack_Toolbar_CollToolBar(toolbar,dataBean.getTitle(),collapsingToolbarLayout,Color.WHITE);
        collapsingToolbarLayout.setTitle(dataBean.getTitle());

        ImageLoader.displayImage(this,dataBean.getAlbums().get(0),toolbar_image);
        _collToolbar_FloatButton_initColor(dataBean.getAlbums().get(0),collapsingToolbarLayout,null);

        String str_content="<font color='#FF0000'>tags:</font>\n" +
                dataBean.getTags() + "\n" +
                "<font color='#FF0000'>imtro:</font>\n" +
                dataBean.getImtro() + "\n" +
                "<font color='#FF0000'>ingredients:</font>\n" +
                dataBean.getIngredients() + "\n" +
                "<font color='#FF0000'>burden:</font>\n" + dataBean.getBurden() + "\n" +
                "<font color='#FF0000'>steps:</font>";

        tv_content.setText(Html.fromHtml(str_content.replace("\n","<br />")));

        detailAdapter = new CaiPuDetailAdapter(dataBean.getSteps());

        detailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                new ViewPagerActivity().launch(CaiPuDetailActivity.this,dataBean.getSteps(),position,"img","step");
            }
        });


        linearLayoutManager = new LinearLayoutManager(this);
        recycler_list.setLayoutManager(linearLayoutManager);
        recycler_list.setHasFixedSize(true);
        recycler_list.setNestedScrollingEnabled(false);

        recycler_list.setAdapter(detailAdapter);

    }

}
