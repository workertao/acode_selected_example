package com.acode.selectedview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.acode.selectedview.listener.OnVerticalListener;
import com.acode.selectedview.R;
import com.acode.selectedview.SelectedView;
import com.acode.selectedview.bean.BaseSelectedBean;
import com.acode.selectedview.bean.SvBean;
import com.acode.selectedview.listener.SelectedVerticalListener;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;

/**
 * user:yangtao
 * date:2018/7/201050
 * email:yangtao@bjxmail.com
 * introduce:垂直列表
 */
public class SelectedVerticalActivity extends Activity {
    private SelectedView sv;
    private Button btn;
    private ArrayList<ArrayList<BaseSelectedBean>> selectBeans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_vertical);
        sv = (SelectedView) findViewById(R.id.sv);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.getSelectedData();
            }
        });

        final String json = getIntent().getStringExtra("json");
        if (!TextUtils.isEmpty(json)) {
            selectBeans = new ArrayList<>();
            JSONArray jsonArray = JSON.parseArray(json);
            for (int i = 0; i < jsonArray.size(); i++) {
                ArrayList<BaseSelectedBean> beans = (ArrayList<BaseSelectedBean>) JSON.parseArray(jsonArray.getString(i), BaseSelectedBean.class);
                selectBeans.add(beans);
            }
        }
        sv.selectedVerticalListener(new SelectedVerticalListener() {
            @Override
            public int getOrientation() {
                //排列方式
                return LinearLayout.VERTICAL;
            }

            @Override
            public int[] getWeigt() {
                //排列比例
                return new int[]{1, 1, 1, 1, 1, 1};
            }

            @Override
            public int[] getBgColor() {
                //背景色
                return new int[]{R.color.cffffff, R.color.cffffff, R.color.cffffff, R.color.cffffff, R.color.cffffff, R.color.cffffff};
            }

            @Override
            public String getSelectedIndex() {
                //选中的数据
                return json;
            }

            @Override
            public int[] getSelectedNum() {
                //每列可选中的数量
                return new int[]{2, 3, 4, 5, 6, 7};
            }

            @Override
            public int[] getItemBgColor() {
                //item背景色
                return new int[0];
            }

            @Override
            public int[] getItemBgDrawable() {
                //item背景
                return new int[]{R.drawable.shape_num_border_radio2, R.drawable.shape_template_border_r2};
            }

            @Override
            public int[] getItemTextColor() {
                //item字体颜色
                return new int[]{R.color.c333333, R.color.cff4400};
            }

            @Override
            public ArrayList[] getData() {
                //数据源
                return new ArrayList[]{
                        getListData(0),
                        getListData(1),
                        getListData(2),
                        getListData(3),
                        getListData(4),
                        getListData(5)
                };
            }

            @Override
            public boolean isShowIcon() {
                //是否展示选中icon
                return true;
            }
        });
        sv.setOnVerticalListener(new OnVerticalListener() {
            @Override
            public void onComplete(String json) {
                Intent intent = new Intent();
                intent.putExtra("json", json);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private ArrayList<SvBean> getListData(int cloumNum) {
        ArrayList<SvBean> data = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            SvBean svBean = new SvBean();
            svBean.setId(i);
            svBean.setName("acode " + i);
            if (selectBeans != null) {
                ArrayList<BaseSelectedBean> currentSelectBeans = selectBeans.get(cloumNum);
                for (int j = 0; j < currentSelectBeans.size(); j++) {
                    if (svBean.getId() == currentSelectBeans.get(j).getId()) {
                        svBean.setSelect(true);
                    }
                }
            }
            data.add(svBean);
        }
        return data;
    }
}
