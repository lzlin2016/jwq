package com.jw.wq.bean;

import java.util.List;

/**
 * Created by Sight-WXC on 2016/7/1 0001.
 */
public class ViedoResponse {

    List<ViedoData> results;


    public List<ViedoData> getData() {
        return results;
    }

    public void setData(List<ViedoData> data) {
        this.results = data;
    }
}
