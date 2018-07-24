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
 * introduce:省市区
 */
public class SelectedHorizontalAdapter<T extends BaseSelectBean> extends RecyclerView.Adapter {
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
    //未选中背景
    private int noFirstSelectBgColor;
    //选中背景
    private int firstselectBgColor;
    //未选中背景
    private int noSelectBgColor;
    //选中背景
    private int selectBgColor;
    //未选中字体
    private int noSelectTextColor;
    //选中字体
    private int selectTextColor;

    public int getSelectIndex() {
        return selectIndex;
    }

    public SelectedHorizontalAdapter(int index, Context context) {
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

    /**
     * 设置每列item的字体颜色
     *
     * @param itemTextColor
     * @return
     */
    public SelectedHorizontalAdapter setItemTextColor(int[] itemTextColor) {
        noSelectTextColor = itemTextColor[0];
        selectTextColor = itemTextColor[1];
        return this;
    }

    /**
     * 设置每列item的背景颜色
     *
     * @param itemBgColor
     * @return
     */
    public SelectedHorizontalAdapter setItemBgColor(int[] itemBgColor) {
        noSelectBgColor = itemBgColor[0];
        selectBgColor = itemBgColor[1];
        return this;
    }

    /**
     * 设置第一列的选中颜色
     *
     * @param firstselectBgColors
     */
    public void setFirstselectBgColor(int[] firstselectBgColors) {
        noFirstSelectBgColor = firstselectBgColors[0];
        firstselectBgColor = firstselectBgColors[1];
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
        if (selectIndex == position) {
            if (firstselectBgColor != 0 && index == 0) {
                popCityViewHolder.itemView.setBackgroundColor(context.getResources().getColor(firstselectBgColor));
            } else {
                popCityViewHolder.itemView.setBackgroundColor(context.getResources().getColor(selectBgColor));
            }
            popCityViewHolder.tvIndustryName.setTextColor(context.getResources().getColor(selectTextColor));
            if (isShowIcon && index == columnNum - 1) {
                popCityViewHolder.ivIndustryNameState.setVisibility(View.VISIBLE);
            }
        } else {
            if (firstselectBgColor != 0 && index == 0) {
                popCityViewHolder.itemView.setBackgroundColor(context.getResources().getColor(noFirstSelectBgColor));
            } else {
                popCityViewHolder.itemView.setBackgroundColor(context.getResources().getColor(noSelectBgColor));
            }
            popCityViewHolder.ivIndustryNameState.setVisibility(View.GONE);
            popCityViewHolder.tvIndustryName.setTextColor(context.getResources().getColor(noSelectTextColor));
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

    public SelectedHorizontalAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
