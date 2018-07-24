package com.acode.selectedview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acode.selectedview.R;
import com.acode.selectedview.bean.BaseSelectBean;

import java.util.ArrayList;

/**
 * user:yangtao
 * date:2018/7/31130
 * email:yangtao@bjxmail.com
 * introduce:垂直的适配器
 */
public class SelectedVerticalAdapter<T extends BaseSelectBean> extends RecyclerView.Adapter {
    private Context context;
    //数据源
    private ArrayList<T> data;
    //当前是第几列
    private int index;
    //选中的下标
    private int selectIndex = 0;
    //是否展示选中icon
    private boolean isShowIcon;
    //一共有几列
    private int columnNum;

    public int getSelectIndex() {
        return selectIndex;
    }

    public SelectedVerticalAdapter(int index, Context context) {
        this.context = context;
        this.index = index;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    /**
     * 是否展示选中icon
     *
     * @param isShowIcon
     */
    public void setIsShowIcon(boolean isShowIcon) {
        this.isShowIcon = isShowIcon;
    }

    /**
     * 设置有几列
     *
     * @param columnNum
     */
    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public ArrayList<T> getData() {
        return data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopCityViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final BaseSelectBean baseSelectBean = data.get(position);
        final PopCityViewHolder popCityViewHolder = (PopCityViewHolder) holder;
        popCityViewHolder.tvIndustryName.setText(baseSelectBean.getName());
        if (baseSelectBean.isSelect()){
            popCityViewHolder.tvIndustryName.setTextColor(context.getResources().getColor(R.color.cff4400));
            if (isShowIcon) {
                popCityViewHolder.ivIndustryNameState.setVisibility(View.VISIBLE);
            }
        }else{
            popCityViewHolder.ivIndustryNameState.setVisibility(View.GONE);
            popCityViewHolder.tvIndustryName.setTextColor(context.getResources().getColor(R.color.c333333));
        }
        popCityViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class PopCityViewHolder extends RecyclerView.ViewHolder {
        TextView tvIndustryName;
        ImageView ivIndustryNameState;

        public PopCityViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_select_item, parent, false));
            tvIndustryName = (TextView) itemView.findViewById(R.id.tvIndustryName);
            ivIndustryNameState = (ImageView) itemView.findViewById(R.id.ivIndustryNameState);
        }
    }

    public void setSelectIndex(int index) {
        this.selectIndex = index;
    }

    public OnItemClickListener onItemClickListener;

    public SelectedVerticalAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
