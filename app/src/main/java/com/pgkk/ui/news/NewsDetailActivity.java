package com.pgkk.ui.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.widget.Toolbar;

import com.pgkk.R;
import com.pgkk.data.model.News;
import com.pgkk.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends BaseActivity {
    private static String DATABEAN = "dataBean";
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    News.ResultBean.DataBean dataBean;

    public static void launch(Context context,News.ResultBean.DataBean dataBean) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(DATABEAN,dataBean);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        dataBean = (News.ResultBean.DataBean) getIntent().getSerializableExtra(DATABEAN);
        ButterKnife.bind(this);
        _setBack_Toolbar(toolbar,dataBean.getAuthor_name());
        initView();
    }


    void  initView(){
        webView.loadUrl(dataBean.getUrl());
    }

}
