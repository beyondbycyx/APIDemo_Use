/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.view;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;

import com.example.android.apis.R;

/**
 * Demonstrates expandable lists using a custom {@link ExpandableListAdapter}
 * from {@link BaseExpandableListAdapter}.
 */

/*
*  用到了系统提供的两大类:
*  BaseExpandableListAdapter 适配器
* ExpandableListActivity  自带的listactivity
* */
public class ExpandableList1 extends ExpandableListActivity {

    ExpandableListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up our adapter
        //1.创建adapter
        mAdapter = new MyExpandableListAdapter();
        //2.设置adapter
        setListAdapter(mAdapter);
        //3.注册请求为该listview 显示上下文菜单
        registerForContextMenu(getExpandableListView());
    }

    //长按后创建每个item（或子item）的上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Sample menu");
        menu.add(0, 0, 0, R.string.expandable_list_sample_action);
    }

    //item点击后的回调
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //在item中获取额外的menuinfo
        ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item.getMenuInfo();
        //获取该menu
        String title = ((TextView) info.targetView).getText().toString();
        
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        //判断是组id还是子id被点击了
        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
            int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition); 
            Toast.makeText(this, title + ": Child " + childPos + " clicked in group " + groupPos,
                    Toast.LENGTH_SHORT).show();
            return true;
        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition); 
            Toast.makeText(this, title + ": Group " + groupPos + " clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        
        return false;
    }

    /**
     * A simple adapter which maintains an ArrayList of photo resource Ids. 
     * Each photo is displayed as an image. This adapter supports clearing the
     * list of photos and adding a new photo.
     *
     */
    public class MyExpandableListAdapter extends BaseExpandableListAdapter {
        // Sample data set.  children[i] contains the children (String[]) for groups[i].
        private String[] groups = { "People Names", "Dog Names", "Cat Names", "Fish Names" };
        private String[][] children = {
                { "Arnold", "Barry", "Chuck", "David" },
                { "Ace", "Bandit", "Cha-Cha", "Deuce" },
                { "Fluffy", "Snuggles" },
                { "Goldy", "Bubbles" }
        };
        //获取某个组下的某个特定的item
        public Object getChild(int groupPosition, int childPosition) {
            return children[groupPosition][childPosition];
        }
        //获取组下的某个item的值
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
        //获取某个组的长度
        public int getChildrenCount(int groupPosition) {
            return children[groupPosition].length;
        }

        //创建泛化的view,这里是指组下的item，还没开始绑定数据
        public TextView getGenericView() {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 64);

            TextView textView = new TextView(ExpandableList1.this);
            textView.setLayoutParams(lp);
            // Center the text vertically
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            textView.setPadding(36, 0, 0, 0);
            return textView;
        }
        //绑定数据与子view
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                View convertView, ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getChild(groupPosition, childPosition).toString());
            return textView;
        }
        //获取组的数据
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }
        //获取组的长度
        public int getGroupCount() {
            return groups.length;
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
        //获取组的view并绑定数据
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getGroup(groupPosition).toString());

            return textView;
        }
        //子item可选择
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
        //数据与id稳定的绑定
        public boolean hasStableIds() {
            return true;
        }

    }

}
