package com.acode.selectedview.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * user:yangtao
 * date:2018/7/41511
 * email:yangtao@bjxmail.com
 * introduce:选择器选中实体
 */
public class BaseSelectedBean implements Parcelable{
    //当前列
    private int currentColumn;
    //当前列选中项
    private int selectIndex;
    //id
    private int id;
    //name
    private String name;


    public BaseSelectedBean() {
    }

    protected BaseSelectedBean(Parcel in) {
        currentColumn = in.readInt();
        selectIndex = in.readInt();
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<BaseSelectedBean> CREATOR = new Creator<BaseSelectedBean>() {
        @Override
        public BaseSelectedBean createFromParcel(Parcel in) {
            return new BaseSelectedBean(in);
        }

        @Override
        public BaseSelectedBean[] newArray(int size) {
            return new BaseSelectedBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(currentColumn);
        dest.writeInt(selectIndex);
        dest.writeInt(id);
        dest.writeString(name);
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public BaseSelectedBean setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
        return this;
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public BaseSelectedBean setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
        return this;
    }

    public int getId() {
        return id;
    }

    public BaseSelectedBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BaseSelectedBean setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "BaseSelectedBean{" +
                "currentColumn=" + currentColumn +
                ", selectIndex=" + selectIndex +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
