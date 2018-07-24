# acode_selected_example
### 自定义选择器 ###

最近做项目，发现有很多二三级联动和筛选条件的页面，仔细观察发现他们其实是一样的，只是数据和排列方式不同，
我也在寻找一种最简便的方法来处理这么多无聊和枯燥的页面，我选择封装一个选择器

    1，水平联动，级别不限制，根据数据源的长度来展示。
    2，垂直列表，级别不限制，根据数据源的长度来展示。

在项目中大多数的水平筛选条件都是单选且联动机制，垂直筛选条件都是多选且不联动，于是针对这个来封装一个选择器

### 使用方法 ###

##### xml ####
        <com.acode.selectedview.SelectedView
            android:id="@+id/sv"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
            
##### activity(水平列表) ####       

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
                
##### activity(垂直列表) ####                  

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