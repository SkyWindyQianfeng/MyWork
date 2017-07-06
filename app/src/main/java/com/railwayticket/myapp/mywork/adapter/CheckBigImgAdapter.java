package com.railwayticket.myapp.mywork.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lidroid.xutils.HttpUtils;
import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.utils.LoaderImage;

import java.util.List;

/**
 * Created by tianyuanyuan on 2016/10/19.
 */
public class CheckBigImgAdapter extends BaseAdapter {

    private List<String> list;
    private Context con;

    public List<String> getList() {
        return list;
    }

    public CheckBigImgAdapter(Context con) {
        this.con = con;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = View.inflate(con, R.layout.item_grid_view, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.img);
        LoaderImage.loaderImage(list.get(position),imageView);
        return convertView;
    }
}
