package com.jw.sight.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.jw.sight.R;

/**
 * Created by Sight-WXC on 2016/6/30 0030.
 */
public abstract class ToolbarActivity extends BaseActivity {


    /**
     * 需求返回一个layoutid;
     *
     * @return
     */
    protected abstract int provideContentViewId();

    /**
     * MD标题栏父布局配合使用
     */
    protected AppBarLayout mAppBar;
    /**
     * MD标题栏
     */
    protected Toolbar mToolbar;
    /**
     * 是否隐藏
     */
    protected boolean mIsHidden = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());
        mAppBar = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if (mToolbar == null || mAppBar == null) {
            throw new IllegalStateException("The subclass must contain  a toolbar");
        }
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onToolBarClick();
            }
        });
        setSupportActionBar(mToolbar);

        if (isCanGoBack()) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 21)
            mAppBar.setElevation(10.6f);//设置布局的深度值

    }

    /**
     * toolbar点击事件
     * 子类可以复写
     */
    public void onToolBarClick() {
    }

    /**
     * 设置 是否可以返回
     * 默认是 无法返回的
     *
     * @return
     */
    public boolean isCanGoBack() {
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();//返回键
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 是否显示 和隐藏ToolBar
     *
     * @param isShow
     */
    public void showOrHideToolbar(boolean isShow) {
        mAppBar.animate()
                .translationY(isShow ? -mAppBar.getHeight() : 0)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
    }


}


