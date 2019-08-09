package com.pgkk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pgkk.PgkkApplication;
import com.pgkk.R;
import com.pgkk.common.utils.SnackbarUtil;
import com.pgkk.di.HasComponent;
import com.pgkk.di.module.ActivityModule;
import com.pgkk.di.news.DaggerNewsComponent;
import com.pgkk.di.news.NewsComponent;
import com.pgkk.di.news.NewsModule;
import com.pgkk.ui.caipu.CaiPuMainFragment;
import com.pgkk.ui.meizi.MeiziFragment;
import com.pgkk.ui.music.MusicFragment;
import com.pgkk.ui.news.NewsTabFragment;
import com.pgkk.ui.read.ReadFragment;
import com.pgkk.ui.video.VideoFragment;
import com.pgkk.ui.video.VideoListPlayFragment;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HasComponent<NewsComponent> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    /**
     * 双击退出标识
     **/
    public static Boolean isExit = false;

    Fragment mCurrentFragment;
    FragmentManager mFragmentManager;

    private int REQUEST_CODE = 1000;

    private MenuItem menuItem;

    @Optional
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ZXingLibrary.initDisplayOpinion(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        initView();

    }

    void initView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();
        getComponent().inject(this);

        //选中页面
        switchMenu(getFragmentName(R.id.nav_caipu));
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switchMenu(getFragmentName(item.getItemId()));
        changeTitle(item.getItemId());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void switchMenu(String fragmentName) {
        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentName);
        if (fragment != null) {
            if (fragment == mCurrentFragment) return;
            mFragmentManager.beginTransaction().show(fragment).commit();
        } else {
            fragment = Fragment.instantiate(this, fragmentName);
            mFragmentManager.beginTransaction().add(R.id.content_frame, fragment, fragmentName).commit();
        }
        if (mCurrentFragment != null) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).commit();
        }
        mCurrentFragment = fragment;
        return;
    }


    private String getFragmentName(int menuId) {
        switch (menuId) {
            case R.id.nav_caipu:
                return CaiPuMainFragment.class.getName();
            case R.id.nav_news:
                return NewsTabFragment.class.getName();
            case R.id.nav_video:
                return VideoFragment.class.getName();
            case R.id.nav_music:
                return MusicFragment.class.getName();
            case R.id.nav_read:
                return ReadFragment.class.getName();
            case R.id.nav_slideshow:
                return MeiziFragment.class.getName();
            default:
                return null;
        }
    }

    private void changeTitle(int menuId) {
        switch (menuId) {
            case R.id.nav_caipu:
                setTitle(R.string.menu_caipu_title);
                menuItem.setVisible(false);
                break;
            case R.id.nav_news:
                setTitle(R.string.menu_news_title);
                menuItem.setVisible(false);
                break;
            case R.id.nav_video:
                setTitle(R.string.menu_video_title);
                menuItem.setVisible(true);
                break;
            case R.id.nav_music:
                setTitle(R.string.menu_music_title);
                menuItem.setVisible(false);
                break;
            case R.id.nav_read:
                setTitle(R.string.menu_read_title);
                menuItem.setVisible(false);
                break;
            case R.id.nav_slideshow:
                setTitle(R.string.menu_slideshow_title);
                menuItem.setVisible(false);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    SnackbarUtil.ShortSnackbar(drawer, "解析结果:" + result, SnackbarUtil.blue).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    SnackbarUtil.ShortSnackbar(drawer, "解析二维码失败", SnackbarUtil.blue).show();
                }
            }
        }
    }

    /**
     * @Description 实现双击返回键退出应用
     */
    public void exitBy2Click() {
        if (isExit == false) {
            isExit = true; // 准备退出
            SnackbarUtil.ShortSnackbar(drawer, "再按一次返回键退出程序", SnackbarUtil.blue).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            this.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.video_menu, menu);
        menuItem = menu.findItem(R.id.table_play);
        menuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.table_play:
                CheckFragment();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void CheckFragment() {
        if (getVisibleFragment().getClass().getName().equals(VideoFragment.class.getName())) {
            switchMenu("" + VideoListPlayFragment.class.getName());
        } else if (getVisibleFragment().getClass().getName().equals(VideoListPlayFragment.class.getName())) {
            switchMenu("" + VideoFragment.class.getName());
        }

    }

    public Fragment getVisibleFragment() {
        List<Fragment> fragments = mFragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }


    /**
     * 返回
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                exitBy2Click();
            } catch (Exception e) {
                this.finish();
            }
            return true;
        }
        return true;
    }

    @Override
    public NewsComponent getComponent() {
        return DaggerNewsComponent.builder()
                .applicationComponent(PgkkApplication.get(this).getComponent())
                .activityModule(new ActivityModule(this))
                .newsModule(new NewsModule())
                .build();
    }
}
