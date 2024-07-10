package com.pgkk.ui.news;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pgkk.R;
import com.pgkk.di.news.NewsComponent;
import com.pgkk.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsTabFragment extends BaseFragment {

    TabFragmentAdapter tabFragmentAdapter;

    @BindView(R.id.tab_layout_news)
    TabLayout tab_layout_news;

    @BindView(R.id.container)
    ViewPager mViewPager;

    List<Fragment> fragments;

    String[] titles=new String[]{"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
    String[] titles_type=new String[]{"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_news_tab, null);

        getActivity().setTitle(R.string.menu_news_title);

        ButterKnife.bind(this, contentView);

        initView();

        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getComponent(NewsComponent.class).inject(this);
    }


    void initView(){
        initData();
        tab_layout_news.setupWithViewPager(mViewPager);
        tab_layout_news.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabFragmentAdapter = new TabFragmentAdapter(fragments,titles,getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(tabFragmentAdapter);

        tab_layout_news.setTabTextColors(getResources().getColor(R.color.gray), Color.WHITE);
    }

    void initData(){
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new NewsBatFragment().newInstance(titles_type[i]));
        }
    }


    public class TabFragmentAdapter extends FragmentPagerAdapter {

        private final String[] titles;
        private List<Fragment> fragments;

        public TabFragmentAdapter(List<Fragment> fragments, String[] titles, FragmentManager fm) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
