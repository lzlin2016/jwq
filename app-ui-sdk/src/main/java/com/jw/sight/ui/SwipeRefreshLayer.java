package com.jw.sight.ui;

import com.jw.sight.view.MultiSwipeRefreshLayout;

/**
 * Created by Sight-WXC on 2016/6/30 0030.
 */
public interface SwipeRefreshLayer {
    void requestDataRefresh();

    void setRefresh(boolean refresh);

    void setProgressViewOffset(boolean scale, int start, int end);

    void setCanChildScrollUpCallback(MultiSwipeRefreshLayout.CanChildScrollUpCallback callback);
}
