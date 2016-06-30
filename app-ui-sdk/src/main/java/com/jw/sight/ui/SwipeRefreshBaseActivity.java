package com.jw.sight.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.jw.sight.R;
import com.jw.sight.view.MultiSwipeRefreshLayout;

import butterknife.ButterKnife;

/**
 * Created by Sight-WXC on 2016/6/30 0030.
 * 支持下拉刷新的布局
 * 引用 google SwipeLayout布局
 */
public abstract class SwipeRefreshBaseActivity extends ToolbarActivity implements SwipeRefreshLayer {


    public MultiSwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsRequestDataRefresh = false;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ButterKnife.bind(this);
        mSwipeRefreshLayout = (MultiSwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        trySetupSwipeRefresh();
    }

    void trySetupSwipeRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_3,
                    R.color.refresh_progress_2, R.color.refresh_progress_1);
            // Do not use lambda here!
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    requestDataRefresh();
                }
            });
        }
    }

    @Override
    public void requestDataRefresh() {
        mIsRequestDataRefresh = true;
    }


    public void setRefresh(boolean requestDataRefresh) {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        if (!requestDataRefresh) {
            mIsRequestDataRefresh = false;
            // 防止刷新消失太快，让子弹飞一会儿.
            mSwipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mSwipeRefreshLayout != null) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }
            }, 1000);
        } else {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void setProgressViewOffset(boolean scale, int start, int end) {
        mSwipeRefreshLayout.setProgressViewOffset(scale, start, end);
    }


    @Override
    public void setCanChildScrollUpCallback(MultiSwipeRefreshLayout.CanChildScrollUpCallback canChildScrollUpCallback) {
        mSwipeRefreshLayout.setCanChildScrollUpCallback(canChildScrollUpCallback);
    }


    public boolean isRequestDataRefresh() {
        return mIsRequestDataRefresh;
    }

}