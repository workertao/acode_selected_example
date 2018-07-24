package com.acode.selectedview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.acode.selectedview.OnHorizontalListener;
import com.acode.selectedview.R;
import com.acode.selectedview.SelectedView;
import com.acode.selectedview.bean.BaseSelectBean;
import com.acode.selectedview.bean.BaseSelectedBean;
import com.acode.selectedview.bean.SvBean;
import com.acode.selectedview.utils.SelectedHorizontalListener;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * user:yangtao
 * date:2018/7/201050
 * email:yangtao@bjxmail.com
 * introduce:水平列表
 */
public class SelectedHorizontallActivity extends Activity {
    private SelectedView sv;
    private Button btn;
    private ArrayList<BaseSelectedBean> baseSelectedBeans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_horizontall);
        String json = getIntent().getStringExtra("json");
        if (!TextUtils.isEmpty(json)) {
            baseSelectedBeans = (ArrayList<BaseSelectedBean>) JSON.parseArray(json, BaseSelectedBean.class);
        }
        sv = (SelectedView) findViewById(R.id.sv);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.getSelectedData();
            }
        });
        sv.setSelectedHorizontalListener(new SelectedHorizontalListener() {
            @Override
            public int getOrientation() {
                //排列方式
                return LinearLayout.HORIZONTAL;
            }

            @Override
            public int[] getWeigt() {
                //每列的比例
                return new int[]{1, 1, 1};
            }

            @Override
            public int[] getBgColor() {
                //每列的背景色
                return new int[]{R.color.cf8f9fa, R.color.cffffff, R.color.cffffff};
            }

            @Override
            public int[] getItemTextColor() {
                //每列的item字体颜色  选中和未选中
                return new int[]{R.color.c333333, R.color.cff4400};
            }

            @Override
            public int[] getItemBgColor() {
                //每列的item背景色  选中和未选中
                return new int[]{R.color.cffffff, R.color.cffffff};
            }

            @Override
            public int[] getFirstItemBgColor() {
                //第一列的选中和未选中的颜色
                return new int[]{R.color.cf8f9fa, R.color.cffffff};
            }

            @Override
            public int[] getSelectedIndex() {
                //选中的下标
                return new int[]{0, 0, 0};
            }

            @Override
            public ArrayList[] getData() {
                //数据源
                return new ArrayList[]{
                        getListData(),
                        getListData().get(0).getNextBeans(),
                        getListData().get(0).getNextBeans().get(0).getNextBeans()
                };
            }

            @Override
            public boolean isShowIcon() {
                //是否展示选中icon
                return true;
            }

            @Override
            public boolean isInitAllData() {
                //数据是否全部初始化
                return true;
            }
        });
        if (baseSelectedBeans != null) {
            sv.setSelectedIndexForHorizontal(new int[]{
                    baseSelectedBeans.get(0).getSelectIndex(),
                    baseSelectedBeans.get(1).getSelectIndex(),
                    baseSelectedBeans.get(2).getSelectIndex()});
            sv.setData(
                    getListData(),
                    getListData().get(baseSelectedBeans.get(0).getSelectIndex()).getNextBeans(),
                    getListData().get(baseSelectedBeans.get(0).getSelectIndex()).getNextBeans().get(baseSelectedBeans.get(1).getSelectIndex()).getNextBeans());
        }

        sv.setOnHorizontalListener(new OnHorizontalListener() {
            @Override
            public void onComplete(String json) {
                Intent intent = new Intent();
                intent.putExtra("json", json);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private ArrayList<SvBean> getListData() {
        ArrayList<SvBean> firstList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SvBean first = new SvBean();
            first.setId(i);
            first.setName("one" + i);
            ArrayList<SvBean> secondList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                SvBean second = new SvBean();
                second.setId(j);
                second.setName("second" + i + j);

                ArrayList<SvBean> threeList = new ArrayList<>();
                for (int k = 0; k < 10; k++) {
                    SvBean three = new SvBean();
                    three.setId(k);
                    three.setName("three" + i + j + k);
                    threeList.add(three);
                }
                second.setSvBeans(threeList);
                secondList.add(second);
            }
            first.setSvBeans(secondList);
            firstList.add(first);
        }
        return firstList;
    }
}
