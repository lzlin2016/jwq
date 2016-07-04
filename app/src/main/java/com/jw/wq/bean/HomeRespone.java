package com.jw.wq.bean;

import java.util.List;

/**
 * Created by Sight-WXC on 2016/7/1 0001.
 */
public class HomeRespone extends BaseData {


    List<HomeData> results;

    public List<HomeData> getData() {
        return results;
    }

    public void setData(List<HomeData> data) {
        this.results = data;
    }
}
