package com.acode.selectedview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.acode.selectedview.adapter.SelectedHorizontalAdapter;
import com.acode.selectedview.adapter.SelectedVerticalAdapter;
import com.acode.selectedview.bean.BaseSelectBean;
import com.acode.selectedview.bean.BaseSelectedBean;
import com.acode.selectedview.utils.DimenUtils;
import com.acode.selectedview.utils.SelectedHorizontalListener;
import com.acode.selectedview.utils.SelectedVerticalListener;
import com.acode.selectedview.utils.SpaceItemDecoration;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;

/**
 * user:yangtao
 * date:2018/7/90955
 * email:yangtao@bjxmail.com
 * introduce:自定义选择器view
 */
public class SelectedView<T extends BaseSelectBean> extends LinearLayout {
    private Context context;
    //存放水平适配器
    private ArrayList<SelectedHorizontalAdapter> selectedHorizontalAdapters = new ArrayList<>();
    //存放垂直适配器
    private ArrayList<SelectedVerticalAdapter> selectedVerticalAdapters = new ArrayList<>();
    //水平回调监听
    private OnHorizontalListener onHorizontalListener;
    //垂直
    private OnVerticalListener onVerticalListener;
    //比例
    private int[] wights;
    //背景
    private int[] bgres;
    //选中的数量
    private int[] selectedNum;
    //item的字体颜色
    private int[] itemTextColor;
    //item的背景颜色
    private int[] itemBgColor;
    //item的背景颜色
    private int[] itemBgDrawable;
    //第一列的item的背景颜色
    private int[] firstItemBgColor;
    //是否展示选中icon
    private boolean isShowIcon;
    //是否初始化所有的列表数据
    private boolean isInitListData;
    //每列的选中项
    private int[] selectIndex;

    public SelectedView(Context context) {
        this(context, null);
    }

    public SelectedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {

    }

    /**
     * 设置水平排列方式
     *
     * @param selectedHorizontalListener
     * @return
     */
    public SelectedView setSelectedHorizontalListener(SelectedHorizontalListener selectedHorizontalListener) {
        setWeight(selectedHorizontalListener.getWeigt());
        setIsInitListData(selectedHorizontalListener.isInitAllData());
        setBg(selectedHorizontalListener.getBgColor());
        setIsShowIcon(selectedHorizontalListener.isShowIcon());
        setOrientation(selectedHorizontalListener.getOrientation());
        setSelectedIndexForHorizontal(selectedHorizontalListener.getSelectedIndex());
        setItemTextColor(selectedHorizontalListener.getItemTextColor());
        setItemBgColor(selectedHorizontalListener.getItemBgColor());
        setFirstItemBgColor(selectedHorizontalListener.getFirstItemBgColor());
        setData(selectedHorizontalListener.getData());
        return this;
    }

    /**
     * 设置垂直排列方式
     *
     * @param selectedVerticalListener
     * @return
     */
    public SelectedView selectedVerticalListener(SelectedVerticalListener selectedVerticalListener) {
        setWeight(selectedVerticalListener.getWeigt());
        setBg(selectedVerticalListener.getBgColor());
        setIsShowIcon(selectedVerticalListener.isShowIcon());
        setOrientation(selectedVerticalListener.getOrientation());
        setSelectedIndexForVertical(selectedVerticalListener.getSelectedIndex());
        setSelectedNum(selectedVerticalListener.getSelectedNum());
        setItemBgDrawable(selectedVerticalListener.getItemBgDrawable());
        setItemBgColor(selectedVerticalListener.getItemBgColor());
        setItemTextColor(selectedVerticalListener.getItemTextColor());
        setData(selectedVerticalListener.getData());
        return this;
    }

    /**
     * 列表权重比列
     *
     * @param wights
     * @return
     */
    public SelectedView setWeight(int[] wights) {
        this.wights = wights;
        return this;
    }

    /**
     * 列表背景
     *
     * @param bgres
     * @return
     */
    public SelectedView setBg(int[] bgres) {
        this.bgres = bgres;
        return this;
    }

    /**
     * 设置每列选中的数量
     *
     * @param selectedNum
     * @return
     */
    public SelectedView setSelectedNum(int[] selectedNum) {
        this.selectedNum = selectedNum;
        return this;
    }

    /**
     * 水平监听
     *
     * @param onHorizontalListener
     * @return
     */

    public SelectedView setOnHorizontalListener(OnHorizontalListener onHorizontalListener) {
        this.onHorizontalListener = onHorizontalListener;
        return this;
    }

    /**
     * 垂直监听
     *
     * @param onVerticalListener
     * @return
     */
    public SelectedView setOnVerticalListener(OnVerticalListener onVerticalListener) {
        this.onVerticalListener = onVerticalListener;
        return this;
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
     * 是否展示选中icon
     *
     * @param isInitListData
     */
    public void setIsInitListData(boolean isInitListData) {
        this.isInitListData = isInitListData;
    }

    /**
     * 设置每列的选中项
     * 水平 联动
     */
    public void setSelectedIndexForHorizontal(int[] selectIndex) {
        this.selectIndex = selectIndex;
    }

    /**
     * 设置每列item的字体颜色
     *
     * @param itemTextColor
     * @return
     */
    public SelectedView setItemTextColor(int[] itemTextColor) {
        this.itemTextColor = itemTextColor;
        return this;
    }

    /**
     * 设置每列item的背景色
     *
     * @param itemBgColor
     * @return
     */
    public SelectedView setItemBgColor(int[] itemBgColor) {
        this.itemBgColor = itemBgColor;
        return this;
    }

    /**
     * 设置第一列item的背景色
     *
     * @param firstItemBgColor
     * @return
     */
    public SelectedView setFirstItemBgColor(int[] firstItemBgColor) {
        this.firstItemBgColor = firstItemBgColor;
        return this;
    }

    /**
     * 设置item的背景色
     *
     * @param itemBgDrawable
     * @return
     */
    public SelectedView setItemBgDrawable(int[] itemBgDrawable) {
        this.itemBgDrawable = itemBgDrawable;
        return this;
    }

    /**
     * 设置每列的选中项
     * 垂直
     */
    public void setSelectedIndexForVertical(String json) {
        if (TextUtils.isEmpty(json)) {
            return;
        }
        ArrayList<ArrayList<BaseSelectedBean>> list = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(json);
        for (int i = 0; i < jsonArray.size(); i++) {
            ArrayList<BaseSelectedBean> beans = (ArrayList<BaseSelectedBean>) JSON.parseArray(jsonArray.getString(i), BaseSelectedBean.class);
            list.add(beans);
        }
        setSelectedIndexForVertical(list);
    }

    /**
     * 设置每列的选中项
     */
    public void setSelectedIndexForVertical(ArrayList<ArrayList<BaseSelectedBean>> list) {
        this.list = list;
    }

    /**
     * 设置数据源
     *
     * @param data 每列的数据源
     */
    public void setData(ArrayList<T>... data) {
        if (getOrientation() == LinearLayout.HORIZONTAL) {
            //水平联动列表
            setDataForHorizontal(data);
            return;
        }
        //垂直多选列表
        setDataForVertical(data);
    }

    /**
     * 分割线
     *
     * @return
     */
    private View viewSpaceLine() {
        View view = new View(context);
        if (getOrientation() == VERTICAL) {
            LayoutParams params1 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params1.height = DimenUtils.dip2px(context, 20);
            view.setLayoutParams(params1);
        } else {
            LayoutParams params1 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params1.width = DimenUtils.dip2px(context, 1);
            view.setLayoutParams(params1);
        }
        view.setBackgroundColor(context.getResources().getColor(R.color.cf8f9fa));
        return view;
    }

    /**
     * 初始化水平adapter
     *
     * @param index       初始化的第几列
     * @param selectIndex 当前列选中的下标
     * @param columeNum   总共有列表
     * @param data        数据
     * @param <T>         BaseSelectBean的子类
     * @return
     */
    private <T extends BaseSelectBean> SelectedHorizontalAdapter initHorizontalAdapter(int index, int selectIndex, int columeNum, ArrayList<T> data) {
        SelectedHorizontalAdapter selectedAdapter = new SelectedHorizontalAdapter(index, context);
        if (index != 0 && !isInitListData) {
            return selectedAdapter;
        }
        setAdapterData(selectedAdapter, selectIndex, columeNum, data);
        return selectedAdapter;
    }

    /**
     * 设置adapter所需数据
     *
     * @param selectedAdapter
     * @param selectIndex
     * @param columeNum
     * @param data
     * @param <T>
     */
    private <T extends BaseSelectBean> void setAdapterData(SelectedHorizontalAdapter selectedAdapter, int selectIndex, int columeNum, ArrayList<T> data) {
        selectedAdapter.setSelectIndex(selectIndex);
        selectedAdapter.setColumnNum(columeNum);
        selectedAdapter.setIsShowIcon(isShowIcon);
        selectedAdapter.setItemBgColor(itemBgColor);
        selectedAdapter.setItemTextColor(itemTextColor);
        selectedAdapter.setFirstselectBgColor(firstItemBgColor);
        selectedAdapter.setData(data);
        selectedAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化横向adapter
     *
     * @param index     初始化的第几列
     * @param columeNum 总共有列表
     * @param data      数据
     * @param <T>       BaseSelectBean的子类
     * @return
     */
    private <T extends BaseSelectBean> SelectedVerticalAdapter initVerticalAdapter(int index, int columeNum, ArrayList<T> data) {
        SelectedVerticalAdapter selectedAdapter = new SelectedVerticalAdapter(context);
        selectedAdapter.setIsShowIcon(isShowIcon);
        selectedAdapter.setItemBgColor(itemBgColor);
        selectedAdapter.setItemTextColor(itemTextColor);
        selectedAdapter.setItemBgDrawable(itemBgDrawable);
        selectedAdapter.setData(data);
        selectedAdapter.notifyDataSetChanged();
        return selectedAdapter;
    }

    /**
     * 初始化recyleView
     *
     * @param i 第几列
     */
    private RecyclerView initRecyleView(int i) {
        RecyclerView recyclerView = new RecyclerView(context);
        //垂直
        if (getOrientation() == VERTICAL) {
            GridLayoutManager manager = new GridLayoutManager(context, 4) {
                @Override
                public boolean canScrollVertically() {
                    //解决ScrollView里存在多个RecyclerView时滑动卡顿的问题
                    //如果你的RecyclerView是水平滑动的话可以重写canScrollHorizontally方法
                    return false;
                }
            };
            recyclerView.setLayoutManager(manager);
            recyclerView.addItemDecoration(new SpaceItemDecoration(context, 10, 4));
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            recyclerView.setLayoutParams(params);
            if (bgres == null) {
                recyclerView.setBackgroundColor(context.getResources().getColor(R.color.cffffff));
            } else {
                recyclerView.setBackgroundColor(context.getResources().getColor(bgres[i]));
            }
            return recyclerView;
        }
        //水平
        if (getOrientation() == HORIZONTAL) {
            LinearLayoutManager manager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(manager);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(context.getResources().getDrawable(R.drawable.shape_bg_cf8f9fa_height_1));
            recyclerView.addItemDecoration(dividerItemDecoration);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = wights[i];
            recyclerView.setLayoutParams(params);
            //如果没有传背景色，则默认第一个灰色，之后白色
            if (bgres == null) {
                if (i > 0) {
                    recyclerView.setBackgroundColor(context.getResources().getColor(R.color.cffffff));
                } else {
                    recyclerView.setBackgroundColor(context.getResources().getColor(R.color.cf8f9fa));
                }
            } else {
                recyclerView.setBackgroundColor(context.getResources().getColor(bgres[i]));
            }
            return recyclerView;
        }
        return null;
    }

    /**
     * 获取选中数据
     */
    public void getSelectedData() {
        //水平
        if (getOrientation() == LinearLayout.HORIZONTAL) {
            ArrayList<BaseSelectedBean> selectedBeans = new ArrayList<>();
            for (int i = 0; i < selectedHorizontalAdapters.size(); i++) {
                if (selectedHorizontalAdapters.get(i).getData() == null || selectedHorizontalAdapters.get(i).getData().size() == 0) {
                    continue;
                }
                int selectIndex = selectedHorizontalAdapters.get(i).getSelectIndex();
                if (selectIndex == -1) {
                    Toast.makeText(context, "请选择第" + i + "列数据", Toast.LENGTH_LONG).show();
                    return;
                }
                BaseSelectBean baseSelectBean = (BaseSelectBean) selectedHorizontalAdapters.get(i).getData().get(selectedHorizontalAdapters.get(i).getSelectIndex());
                BaseSelectedBean selectedBean = new BaseSelectedBean();
                selectedBean.setCurrentColumn(i);
                selectedBean.setId(baseSelectBean.getId());
                selectedBean.setName(baseSelectBean.getName());
                selectedBean.setSelectIndex(selectedHorizontalAdapters.get(i).getSelectIndex());
                selectedBeans.add(selectedBean);
            }
            String json = JSON.toJSONString(selectedBeans);
            if (onHorizontalListener == null) {
                return;
            }
            onHorizontalListener.onComplete(json);
            return;
        }
        //垂直
        //垂直针对多选
        String json = JSON.toJSONString(list);
        if (onVerticalListener == null) {
            return;
        }
        onVerticalListener.onComplete(json);
    }

    /**
     * 处理水平的，多为联动列表
     *
     * @param data
     */
    private void setDataForHorizontal(final ArrayList<T>... data) {
        this.removeAllViews();
        selectedHorizontalAdapters.clear();
        for (int i = 0; i < data.length; i++) {
            //start == 数据转换，先将所有的T类型实体转成BaseSelectBean
            final ArrayList<BaseSelectBean> baseSelectBeans = new ArrayList<>();
            for (T t : data[i]) {
                baseSelectBeans.add(t);
            }
            //画RecyclerView
            RecyclerView recyclerView = initRecyleView(i);
            //adapter适配器
            final SelectedHorizontalAdapter selectedAdapter = initHorizontalAdapter(i, selectIndex[i], data.length, baseSelectBeans);
            recyclerView.setAdapter(selectedAdapter);
            //滚动到指定位置
            recyclerView.scrollToPosition(selectIndex[i]);
            //竖的分割线
            View viewSpaceLine = viewSpaceLine();
            //添加到布局
            this.addView(recyclerView);
            this.addView(viewSpaceLine);
            //添加到adapter集合
            selectedHorizontalAdapters.add(selectedAdapter);
            final int index = i;
            selectedAdapter.setOnItemClickListener(new SelectedHorizontalAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    BaseSelectBean selectBean = (BaseSelectBean) selectedAdapter.getData().get(position);
                    //所有级列表数据全部展示
                    if (isInitListData) {
                        //更新当前列表的ui
                        selectedAdapter.setSelectIndex(position);
                        selectedAdapter.notifyDataSetChanged();
                        //当前列
                        int currentIndex = index;
                        //下级列
                        int nextIndex = index + 1;
                        for (int i = nextIndex; i < selectedHorizontalAdapters.size(); i++) {
                            BaseSelectBean nextbean = null;
                            if (currentIndex > index) {
                                nextbean = (BaseSelectBean) selectedHorizontalAdapters.get(currentIndex).getData().get(0);
                            } else {
                                nextbean = (BaseSelectBean) selectedHorizontalAdapters.get(currentIndex).getData().get(position);
                            }
                            selectedHorizontalAdapters.get(i).setData(nextbean.getNextBeans());
                            selectedHorizontalAdapters.get(i).setSelectIndex(0);
                            selectedHorizontalAdapters.get(i).notifyDataSetChanged();
                            currentIndex++;
                        }
                        if (index == selectedHorizontalAdapters.size() - 1) {
                            getSelectedData();
                        }
                        return;
                    }

                    //一级一级展示
                    //更新当前列表的ui
                    selectedAdapter.setSelectIndex(position);
                    selectedAdapter.notifyDataSetChanged();
                    //下一级的列表下标
                    int nextIndex = index + 1;
                    if (nextIndex >= selectedHorizontalAdapters.size()) {
                        getSelectedData();
                        return;
                    }
                    //更新下一级列表
                    BaseSelectBean nextbean = (BaseSelectBean) selectedHorizontalAdapters.get(index).getData().get(position);
                    SelectedHorizontalAdapter selectedHorizontalAdapter = selectedHorizontalAdapters.get(nextIndex);
                    setAdapterData(selectedHorizontalAdapter,-1,selectedHorizontalAdapters.size(),nextbean.getNextBeans());
//                    selectedHorizontalAdapters.get(nextIndex).setData(nextbean.getNextBeans());
//                    selectedHorizontalAdapters.get(nextIndex).setSelectIndex(-1);
//                    selectedHorizontalAdapters.get(nextIndex).notifyDataSetChanged();
                }
            });
        }
    }

    private ArrayList<ArrayList<BaseSelectedBean>> list;

    /**
     * 垂直
     *
     * @param data
     */
    private void setDataForVertical(final ArrayList<T>... data) {
        if (list == null) {
            list = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                ArrayList<BaseSelectedBean> selectedBeans = new ArrayList<>();
                list.add(selectedBeans);
            }
        }
        this.removeAllViews();
        selectedVerticalAdapters.clear();
        for (int i = 0; i < data.length; i++) {
            //数据转换，先将所有的T类型实体转成BaseSelectBean
            final ArrayList<BaseSelectBean> baseSelectBeans = new ArrayList<>();
            for (T t : data[i]) {
                baseSelectBeans.add(t);
            }
            //RecyclerView
            RecyclerView recyclerView = initRecyleView(i);
            //adapter适配器
            final SelectedVerticalAdapter selectedAdapter = initVerticalAdapter(i, data.length, baseSelectBeans);
            recyclerView.setAdapter(selectedAdapter);
            //分割线
            View viewSpaceLine = viewSpaceLine();
            //添加到布局
            if (getOrientation() == VERTICAL) {
                RelativeLayout relativeLayout = new RelativeLayout(context);
                LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                relativeLayout.setLayoutParams(params);
                relativeLayout.addView(recyclerView);
                this.addView(relativeLayout);
            } else {
                this.addView(recyclerView);
            }
            this.addView(viewSpaceLine);
            //添加到adapter集合
            selectedVerticalAdapters.add(selectedAdapter);
            final int index = i;
            selectedAdapter.setOnItemClickListener(new SelectedVerticalAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    BaseSelectBean selectBean = (BaseSelectBean) selectedAdapter.getData().get(position);
                    if (list.get(index).size() < selectedNum[index] || selectBean.isSelect()) {
                        //转换成BaseSelectedBean
                        BaseSelectedBean baseSelectedBean = new BaseSelectedBean();
                        baseSelectedBean.setCurrentColumn(index);
                        baseSelectedBean.setId(selectBean.getId());
                        baseSelectedBean.setName(selectBean.getName());
                        baseSelectedBean.setSelectIndex(position);
                        DLog.i(SelectedView.class, "AAAAA:" + list.get(index).toString());
                        DLog.i(SelectedView.class, "AAAAA:" + baseSelectedBean.toString());
                        if (selectBean.isSelect()) {
                            for (int i = 0; i < list.get(index).size(); i++) {
                                if (list.get(index).get(i).getId() == baseSelectedBean.getId()) {
                                    list.get(index).remove(i);
                                }
                            }
                        } else {
                            list.get(index).add(baseSelectedBean);
                        }
                        //更新界面
                        selectBean.setSelect(!selectBean.isSelect());
                        selectedAdapter.notifyDataSetChanged();
                        return;
                    }
                    Toast.makeText(context, "最多选择" + selectedNum[index] + "个", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
