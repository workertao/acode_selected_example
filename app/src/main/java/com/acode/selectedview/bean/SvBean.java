package com.acode.selectedview.bean;

import java.util.ArrayList;

/**
 * user:yangtao
 * date:2018/7/191726
 * email:yangtao@bjxmail.com
 * introduce:测试实体
 */
public class SvBean extends BaseSelectBean {
    private int id;
    private String name;
    private ArrayList<SvBean> svBeans;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<BaseSelectBean> getNextBeans() {
        ArrayList<BaseSelectBean> baseSelectBeans = new ArrayList<>();
        for (SvBean svBean : svBeans) {
            baseSelectBeans.add(svBean);
        }
        return baseSelectBeans;
    }

    public SvBean setId(int id) {
        this.id = id;
        return this;
    }

    public SvBean setName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<SvBean> getSvBeans() {
        return svBeans;
    }

    public SvBean setSvBeans(ArrayList<SvBean> svBeans) {
        this.svBeans = svBeans;
        return this;
    }
}
