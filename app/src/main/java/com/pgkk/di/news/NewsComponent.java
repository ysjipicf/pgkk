package com.pgkk.di.news;


import com.pgkk.di.PerActivity;
import com.pgkk.di.component.ActivityComponent;
import com.pgkk.di.component.ApplicationComponent;
import com.pgkk.di.module.ActivityModule;
import com.pgkk.ui.MainActivity;
import com.pgkk.ui.caipu.CaiPuMainFragment;
import com.pgkk.ui.caipu.SuspensionActivity;
import com.pgkk.ui.meizi.MeiziFragment;
import com.pgkk.ui.music.MusicFragment;
import com.pgkk.ui.news.NewsBatFragment;
import com.pgkk.ui.news.NewsTabFragment;
import com.pgkk.ui.read.ReadDetailActivity;
import com.pgkk.ui.read.ReadFragment;
import com.pgkk.ui.video.VideoFragment;
import com.pgkk.ui.video.VideoListPlayFragment;

import dagger.Component;

/**
 *
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, NewsModule.class})
public interface NewsComponent extends ActivityComponent {

    void inject(NewsBatFragment fragment);

    void inject(SuspensionActivity suspensionActivity);

    void inject(CaiPuMainFragment caiPuMainFragment);

    void inject(NewsTabFragment newsTabFragment);

    void inject(MainActivity mainActivity);

    void inject(MeiziFragment meiziFragment);

    void inject(VideoFragment videoFragment);

    void inject(MusicFragment musicFragment);

    void inject(ReadFragment readFragment);

    void inject(ReadDetailActivity readDetailActivity);

    void inject(VideoListPlayFragment videoListPlayFragment);

}
