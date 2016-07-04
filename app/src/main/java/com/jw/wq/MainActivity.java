package com.jw.wq;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.jw.sight.ui.SwipeRefreshBaseActivity;
import com.jw.sight.utlis.Logger;
import com.jw.sight.view.MultiSwipeRefreshLayout;
import com.jw.wq.adpter.MeizhiListAdapter;
import com.jw.wq.bean.HomeData;
import com.jw.wq.bean.HomeRespone;
import com.jw.wq.bean.ViedoData;
import com.jw.wq.bean.ViedoResponse;
import com.jw.wq.http.ServerApi;
import com.jw.wq.http.ServerFactory;
import com.jw.wq.util.Dates;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends SwipeRefreshBaseActivity {

    @Bind(R.id.rv_meizhi)
    RecyclerView mRecyView;
    @Bind(R.id.swipe_refresh_layout)
    MultiSwipeRefreshLayout swipeRefreshLayout;

    public static ServerApi serverApi = ServerFactory.getServerApi();

    private List<HomeData> mMeizhiList;
    private MeizhiListAdapter meizhiListAdapter;
    private static final int PRELOAD_SIZE = 6;

    private boolean mIsFirstTimeTouchBottom = true;
    private int mPage = 1;
    private int mLastVideoIndex = 0;

    @Override
    protected int provideContentViewId() {
        return R.layout.swipe_recyview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getSwipeRefreshLayout();
        setupRecyclerView();
    }


    @Override
    public void getSwipeRefreshLayout() {
        mSwipeRefreshLayout = (MultiSwipeRefreshLayout) findViewById(com.jw.sight.R.id.swipe_refresh_layout);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        new Handler().postDelayed(() -> setRefresh(true), 358);
        loadData(true);

    }

    /**
     * 初始化 View
     */
    private void setupRecyclerView() {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager
                .VERTICAL);
        mRecyView.setLayoutManager(layoutManager);
        mMeizhiList = new ArrayList<>();
        meizhiListAdapter = new MeizhiListAdapter(this, mMeizhiList);
        mRecyView.setAdapter(meizhiListAdapter);

        mRecyView.addOnScrollListener(getOnBottomListener(layoutManager));


    }


    RecyclerView.OnScrollListener getOnBottomListener(StaggeredGridLayoutManager layoutManager) {


        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                boolean isBottom = layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1] >=
                        meizhiListAdapter
                        .getItemCount() - PRELOAD_SIZE;

                Logger.e("is bootm" + isBottom);
                if (!mSwipeRefreshLayout.isRefreshing() && isBottom) {
                    if (!mIsFirstTimeTouchBottom) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        mPage += 1;
                        loadData(false);
                    } else {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
    }


    /**
     * 获取服务数据
     *
     * @param clean 清除来自数据库缓存或者已有数据。
     */
    private void loadData(boolean clean) {
        mLastVideoIndex = 0;

        Subscription s = Observable
                .zip(serverApi.getData(mPage),
                        serverApi.getViedoData(mPage),
                        this::crateMzhiDtaa)
                .map(meizhiData -> meizhiData.getData())
                .flatMap(Observable::from)
                .toSortedList((meizhi1, meizhi2) ->
                        meizhi2.publishedAt.compareTo(meizhi1.publishedAt))
                /*.doOnNext(this::saveMeizhis)*/
                .observeOn(AndroidSchedulers.mainThread())
                .finallyDo(() -> setRefresh(false))
                .subscribe(meizhis -> {
                    if (clean) mMeizhiList.clear();
                    mMeizhiList.addAll(meizhis);
                    meizhiListAdapter.notifyDataSetChanged();
                    setRefresh(false);
                }, throwable -> loadError(throwable));

        addSubscription(s);
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Snackbar.make(mRecyView, R.string.snap_load_fail, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, v -> {
                    requestDataRefresh();
                })
                .show();
    }

    private HomeRespone crateMzhiDtaa(HomeRespone data, ViedoResponse viedoData) {
        Logger.e("Home data " + data.getData());
        Logger.e("ViedoResponse data " + viedoData.getData());
        for (HomeData dra : data.getData()
                ) {
            dra.setDesc(dra.getDesc() + getFirstVideoDesc(dra.publishedAt, viedoData.getData()));

        }
        return data;
    }

    private String getFirstVideoDesc(Date publishedAt, List<ViedoData> results) {
        String videoDesc = "";
        for (int i = mLastVideoIndex; i < results.size(); i++) {
            ViedoData video = results.get(i);
            if (video.publishedAt == null) video.publishedAt = video.createdAt;
            if (Dates.isTheSameDay(publishedAt, video.publishedAt)) {
                videoDesc = video.desc;
                mLastVideoIndex = i;
                break;
            }
        }
        return videoDesc;
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        mPage = 1;
        loadData(true);
    }

}
