package com.pgkk.ui.caipu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pgkk.R;
import com.pgkk.di.news.NewsComponent;
import com.pgkk.ui.base.BaseFragment;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanxueze on 2017/12/18.
 * <p>
 * 悬浮条 recyclerview
 */

public class CaiPuMainFragment extends BaseFragment {

    @BindView(R.id.feed_list)
    RecyclerView mFeedList;

    StaggeredGridLayoutManager gridLayoutManager;

    CaipuMainAdapter feedAdapter;

    int[] color_title;
    String[] titls ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_caipumain, null);

        getActivity().setTitle(R.string.menu_caipu_title);

        ButterKnife.bind(this, contentView);

        color_title = getResources().getIntArray(R.array.color_bg);
        titls = getResources().getStringArray(R.array.caiput_title);

        mFeedList = contentView.findViewById(R.id.feed_list);
        initView();

        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(NewsComponent.class).inject(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    void initView() {
        gridLayoutManager = new StaggeredGridLayoutManager (3, StaggeredGridLayoutManager.VERTICAL);
        mFeedList.setLayoutManager(gridLayoutManager);
        mFeedList.setHasFixedSize(true);

        List list = Arrays.asList(titls);
        feedAdapter = new CaipuMainAdapter(list);
        mFeedList.setAdapter(feedAdapter);

        setListener();

    }

    void setListener() {
        feedAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SuspensionActivity.launch(getActivity(), (String) adapter.getItem(position));
            }
        });
    }


    public class CaipuMainAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public CaipuMainAdapter(@Nullable List data) {
            super(R.layout.item_main_caipu, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.text_content, item);
            Random rand = new Random();
            ((TextView)helper.getView(R.id.text_content)).setTextColor(color_title[rand.nextInt(6)]);
        }
    }
}
