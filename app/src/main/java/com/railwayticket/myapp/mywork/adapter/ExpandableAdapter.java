package com.railwayticket.myapp.mywork.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.railwayticket.myapp.mywork.R;

import java.util.List;
import java.util.Map;

/**
 * Created by tianyuanyuan on 2016/8/24.
 */
public class ExpandableAdapter implements ExpandableListAdapter {

    private List<Map<String,String>> list_name;
    private List<Map<String,List<String>>> list_phoneNum;
    private Context context;

    public ExpandableAdapter(List<Map<String, String>> list_name, List<Map<String, List<String>>> list_phoneNum, Context context) {
        this.list_name = list_name;
        this.list_phoneNum = list_phoneNum;
        this.context = context;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return list_name.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list_phoneNum.get(groupPosition).get("phoneNum").size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list_name.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list_phoneNum.get(groupPosition).get("phoneNum").get(childPosition);
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
        return false;
    }



    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView =View.inflate(context, R.layout.item_expandable_group,null);
        TextView tv_parent= (TextView) convertView.findViewById(R.id.tv_parent);
        tv_parent.setText(list_name.get(groupPosition).get("name"));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView=View.inflate(context,R.layout.item_expandable_child,null);
        TextView tv_child= (TextView) convertView.findViewById(R.id.tv_child);
        tv_child.setText(list_phoneNum.get(groupPosition).get("phoneNum").get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
