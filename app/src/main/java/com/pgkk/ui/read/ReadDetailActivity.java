package com.pgkk.ui.read;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.pgkk.PgkkApplication;
import com.pgkk.R;
import com.pgkk.data.model.Read;
import com.pgkk.data.model.ReadDetail;
import com.pgkk.di.HasComponent;
import com.pgkk.di.module.ActivityModule;
import com.pgkk.di.news.DaggerNewsComponent;
import com.pgkk.di.news.NewsComponent;
import com.pgkk.di.news.NewsModule;
import com.pgkk.mvp.common.NetView;
import com.pgkk.presenter.ReadDetailPresenter;
import com.pgkk.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadDetailActivity extends BaseActivity  implements NetView<ReadDetail>,HasComponent<NewsComponent> {
    private static String DATABEAN = "dataBean";
    @Inject
    ReadDetailPresenter readPresenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.web_view)
    WebView webView;

    Read.DataBean dataBean;

    public static void launch(Context context,Read.DataBean dataBean) {
        Intent intent = new Intent(context, ReadDetailActivity.class);
        intent.putExtra(DATABEAN,dataBean);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_detail);
        getComponent().inject(this);
        readPresenter.attachView(this);
        dataBean = (Read.DataBean) getIntent().getSerializableExtra(DATABEAN);
        ButterKnife.bind(this);
        _setBack_Toolbar(toolbar,dataBean.getTitle());
        initView();
        readPresenter.loadReadDetail(Integer.parseInt(dataBean.getItem_id()),Integer.parseInt(dataBean.getId()));
    }


    @SuppressLint("NewApi")
    void  initView(){
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showContent(ReadDetail data) {
        author.setText("文 / " + data.getData().getHp_author());
        String html = data.getData().getHp_content();
        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8",
                null);
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void showEmpty(String empty) {

    }

    @Override
    public NewsComponent getComponent() {
        return DaggerNewsComponent.builder()
                .applicationComponent(PgkkApplication.get(this).getComponent())
                .activityModule(new ActivityModule(this))
                .newsModule(new NewsModule())
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        readPresenter.detachView();
    }
}
