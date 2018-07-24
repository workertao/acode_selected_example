package com.acode.selectedview;

import com.acode.selectedview.bean.BaseSelectedBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * user:yangtao
 * date:2018/7/62049
 * email:yangtao@bjxmail.com
 * introduce:垂直选择器
 */
public interface OnVerticalListener {
    /**
     * 返回选中的json集合
     * @param json
     */
    void onComplete(String json);
}
