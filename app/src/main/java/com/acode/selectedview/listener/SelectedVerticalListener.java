package com.acode.selectedview.listener;

import java.util.ArrayList;

/**
 * user:yangtao
 * date:2018/7/241422
 * email:yangtao@bjxmail.com
 * introduce:垂直列表接口配置
 */
public interface SelectedVerticalListener {
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
     * 设置每列选中的下标
     * 垂直
     *
     * @return
     */
    String getSelectedIndex();

    /**
     * 设置每列的选中数量
     */
    int[] getSelectedNum();

    /**
     * item背景色
     *
     * @return
     */
    int[] getItemBgColor();

    /**
     * item背景
     *
     * @return
     */
    int[] getItemBgDrawable();

    /**
     * item字体颜色
     *
     * @return
     */
    int[] getItemTextColor();

    /**
     * 设置每列选中的下标
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

}
