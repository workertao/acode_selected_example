package com.acode.selectedview.listener;

import com.acode.selectedview.bean.BaseSelectBean;

import java.util.ArrayList;

/**
 * user:yangtao
 * date:2018/7/241422
 * email:yangtao@bjxmail.com
 * introduce:水平列表接口配置
 */
public interface SelectedHorizontalListener {
    /**
     * 设置排列方式
     */
    int getOrientation();

    /**
     * 设置每列的比例
     *
     * @return
     */
    int[] getWeigt();

    /**
     * 设置每列的背景色
     *
     * @return
     */
    int[] getBgColor();

    /**
     * 设置每列的字体的选中和未选中的颜色
     *
     * @return
     */
    int[] getItemTextColor();

    /**
     * 设置每列item的背景选中和未选中的颜色
     *
     * @return
     */
    int[] getItemBgColor();

    /**
     * 设置第一列的背景色
     *
     * @return
     */
    int[] getFirstItemBgColor();

    /**
     * 设置每列选中的下标
     * 水平
     *
     * @return
     */
    int[] getSelectedIndex();

    /**
     * 设置每列的数据
     *
     * @return
     */
    ArrayList[] getData();

    /**
     * 是否展示选中的icon
     *
     * @return
     */
    boolean isShowIcon();

    /**
     * 是否初始化全部数据
     *
     * @return
     */
    boolean isInitAllData();
}
