package com.acode.selectedview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.acode.selectedview.activity.SelectedHorizontallActivity;
import com.acode.selectedview.activity.SelectedVerticalActivity;
import com.acode.selectedview.bean.BaseSelectedBean;
import com.acode.selectedview.utils.DLog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button tv;
    private Button tv1;
    private ArrayList<BaseSelectedBean> baseSelectedBeans;
    private String hJson;
    private String oJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (Button) findViewById(R.id.tv);
        tv1 = (Button) findViewById(R.id.tv1);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //水平
                Intent intent = new Intent(MainActivity.this, SelectedHorizontallActivity.class);
                intent.putExtra("json", hJson);
                startActivityForResult(intent, 1001);
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //垂直
                Intent intent = new Intent(MainActivity.this, SelectedVerticalActivity.class);
                intent.putExtra("json", oJson);
                startActivityForResult(intent, 1002);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String json  = data.getStringExtra("json");
        if (TextUtils.isEmpty(json)) {
            return;
        }
        DLog.i(MainActivity.class, "json:" + json);
        switch (requestCode) {
            case 1001:
                hJson = json;
                //水平-》联动
                baseSelectedBeans = (ArrayList<BaseSelectedBean>) JSON.parseArray(hJson, BaseSelectedBean.class);
                if (baseSelectedBeans == null) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < baseSelectedBeans.size(); i++) {
                    sb.append("第" + i + "列：" + baseSelectedBeans.get(i).getName() + "\n");
                }
                tv.setText(sb.toString());
                break;
            case 1002:
                //垂直
                oJson = json;
                ArrayList<ArrayList<BaseSelectedBean>> selectBeans = new ArrayList<>();
                JSONArray jsonArray = JSON.parseArray(oJson);
                for (int i = 0; i < jsonArray.size(); i++) {
                    ArrayList<BaseSelectedBean> beans = (ArrayList<BaseSelectedBean>) JSON.parseArray(jsonArray.getString(i), BaseSelectedBean.class);
                    selectBeans.add(beans);
                }
                DLog.i(MainActivity.class, "selectBeans:" + selectBeans.toString());
                StringBuilder sb1 = new StringBuilder();
                for (int i = 0; i < selectBeans.size(); i++) {
                    sb1.append("第" + i + "列：");
                    for (BaseSelectedBean bean : selectBeans.get(i)) {
                        sb1.append(bean.getName() + ",");
                    }
                    sb1.append("\n");
                }
                tv1.setText(sb1.toString());
                break;
        }
    }
}
