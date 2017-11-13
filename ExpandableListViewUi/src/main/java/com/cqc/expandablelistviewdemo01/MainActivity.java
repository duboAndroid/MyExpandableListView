package com.cqc.expandablelistviewdemo01;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expanListView;
    private String[] groupArray;
    private String[][] childArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        expanListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expanListView.setAdapter(new MyExpandableAdapter());
        expanListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                ToastUtil.showShortToast(MainActivity.this, groupArray[i]);
                return false;//true/false都可以，但是true，不会展开group
            }
        });
        expanListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                ToastUtil.showShortToast(MainActivity.this, childArray[i][i1]);
                return false;//true/false都可以,
            }
        });
        expanListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                ToastUtil.showShortToast(MainActivity.this, groupArray[i]);
            }
        });
        expanListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                ToastUtil.showShortToast(MainActivity.this,  groupArray[i]);
            }
        });
    }

    private void initData() {
        groupArray = new String[]{"西游记", "水浒传", "三国演义", "红楼梦"};
        childArray = new String[][]{{"唐三藏", "孙悟空", "猪八戒", "沙和尚"}, {"宋江", "林冲", "李逵", "鲁智深"}, {"曹操", "刘备", "孙权", "诸葛亮", "周瑜"}, {"贾宝玉", "林黛玉", "薛宝钗", "王熙凤"}};
    }

//ExpandableListAdapter

    private class MyExpandableAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return groupArray.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return childArray[i].length;
        }

        @Override
        public Object getGroup(int i) {
            return groupArray[i];
        }

        @Override
        public Object getChild(int i, int i1) {
            return childArray[i][i1];
        }

        @Override
        public long getGroupId(int i) {
            return 0;
        }

        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
            GroupViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_group, null);
                holder = new GroupViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (GroupViewHolder) convertView.getTag();
            }
            holder.tv_group.setText(groupArray[i]);
            return convertView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
            ChildViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_child, null);
                holder = new ChildViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ChildViewHolder) convertView.getTag();
            }
            holder.tv_child.setText(childArray[i][i1]);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }

    public class GroupViewHolder {
        TextView tv_group;

        public GroupViewHolder(View convertView) {
            tv_group = (TextView) convertView.findViewById(R.id.tv_group);
        }
    }

    public class ChildViewHolder {
        TextView tv_child;

        public ChildViewHolder(View convertView) {
            tv_child = (TextView) convertView.findViewById(R.id.tv_child);
        }
    }
}
