package com.example.hq.apidemo;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ExpandableList1 extends ExpandableListActivity {

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_expandable_list1);
        String[] strs = {"-group1","-group2","-group3","-group4",};
        String[][] childs = {
                {"allow","amaze"},
                {"base","bye"},
                {"cursor", "circle","cell"},
                {"depend","demand","decode"},} ;
        adapter = new MyAdapter(this,strs,childs);
        setListAdapter(adapter);

        //为listview注册“onCreateContextMenu”方法
        registerForContextMenu(getExpandableListView());
    }

    //长按后的弹出上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("这是menu的title");

        ExpandableListView.ExpandableListContextMenuInfo expandMenuInfo = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;

        long packedPosition = expandMenuInfo.packedPosition;
        int type = ExpandableListView.getPackedPositionType(packedPosition);
        int groupId = ExpandableListView.getPackedPositionGroup(expandMenuInfo.packedPosition);
        menu.add("groupId"+groupId);
        switch (type) {
            case ExpandableListView.PACKED_POSITION_TYPE_GROUP:

                break;
            case ExpandableListView.PACKED_POSITION_TYPE_CHILD:
                int childId = ExpandableListView.getPackedPositionChild(expandMenuInfo.packedPosition);
                menu.add("childId" + childId);
                break;
            default:break;
        }

    }


    private class MyAdapter<T> extends BaseExpandableListAdapter {


        private  T[][] mChildSet;
        private  Context mContext;
        private   T[] mDataSet;

        public MyAdapter(Context context, T [] dataSet,T [][] childSet) {

            mContext = context;
            mDataSet = dataSet;
            mChildSet =  childSet;
        }

        @Override
        public int getGroupCount() {
            return mDataSet.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mChildSet[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mDataSet[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mChildSet[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        //生成一个textview
        private TextView getTextView() {
            TextView tv;
            tv = new TextView(mContext);
            ViewGroup.LayoutParams parms = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(parms);
            tv.setPadding(10, 10, 10, 10);

            return tv;
        }
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View tv = convertView;
            if(tv == null) {
                tv = getTextView();

                ((TextView) tv).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD) );
                //绑定数据
                ((TextView) tv).setText(mDataSet[groupPosition].toString());

            }
            return tv;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View tv = convertView;
            if (tv == null) {
                tv = getTextView();
                ((TextView) tv).setText(mChildSet[groupPosition][childPosition].toString());
            }
            return tv;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
