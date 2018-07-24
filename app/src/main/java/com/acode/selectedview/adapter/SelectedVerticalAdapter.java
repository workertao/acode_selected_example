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
    //是否展示选中icon
    private boolean isShowIcon;
    //未选中背景
    private int noSelectBgColor;
    //选中背景
    private int selectBgColor;
    //未选中背景
    private int noSelectBgDrawable;
    //选中背景
    private int selectBgDrawable;
    //未选中字体
    private int noSelectTextColor;
    //选中字体
    private int selectTextColor;

    public SelectedVerticalAdapter(Context context) {
        this.context = context;
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

    public void setItemBgColor(int[] itemBgColor) {
        if (itemBgColor==null || itemBgColor.length == 0){
            return;
        }
        noSelectBgColor = itemBgColor[0];
        selectBgColor = itemBgColor[1];
    }

    public void setItemBgDrawable(int[] itemBgDrawable) {
        if (itemBgDrawable==null || itemBgDrawable.length == 0){
            return;
        }
        noSelectBgDrawable = itemBgDrawable[0];
        selectBgDrawable = itemBgDrawable[1];
    }

    public void setItemTextColor(int[] itemTextColor) {
        if (itemTextColor==null || itemTextColor.length == 0){
            return;
        }
        noSelectTextColor = itemTextColor[0];
        selectTextColor = itemTextColor[1];
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
        if (baseSelectBean.isSelect()) {
            popCityViewHolder.tvIndustryName.setTextColor(context.getResources().getColor(selectTextColor));
            if (selectBgDrawable != 0) {
                popCityViewHolder.itemView.setBackground(context.getResources().getDrawable(selectBgDrawable));
            }
            if (selectBgColor != 0) {
                popCityViewHolder.itemView.setBackgroundColor(context.getResources().getColor(selectBgColor));
            }
            if (isShowIcon) {
                popCityViewHolder.ivIndustryNameState.setVisibility(View.VISIBLE);
            }
        } else {
            popCityViewHolder.ivIndustryNameState.setVisibility(View.GONE);
            popCityViewHolder.tvIndustryName.setTextColor(context.getResources().getColor(noSelectTextColor));
            if (selectBgDrawable != 0) {
                popCityViewHolder.itemView.setBackground(context.getResources().getDrawable(noSelectBgDrawable));
            }
            if (selectBgColor != 0) {
                popCityViewHolder.itemView.setBackgroundColor(context.getResources().getColor(noSelectBgColor));
            }
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

    public OnItemClickListener onItemClickListener;

    public SelectedVerticalAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
