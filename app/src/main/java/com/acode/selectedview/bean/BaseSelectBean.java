package com.acode.selectedview.bean;

import java.util.ArrayList;

/**
 * user:yangtao
 * date:2018/7/31412
 * email:yangtao@bjxmail.com
 * introduce:选择器基类
 */
public abstract class BaseSelectBean {
    public boolean isSelect;

    public abstract int getId();

    public abstract String getName();

    public abstract ArrayList<BaseSelectBean> getNextBeans();

    public String toString() {
        return "id:" + getId() + "  name:" + getName();
    }

    public boolean isSelect() {
        return isSelect;
    }

    public BaseSelectBean setSelect(boolean select) {
        isSelect = select;
        return this;
    }
}
