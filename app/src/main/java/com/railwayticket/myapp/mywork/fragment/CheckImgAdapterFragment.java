package com.railwayticket.myapp.mywork.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.utils.LoaderImage;

import java.util.ArrayList;

/**
 * Created by tianyuanyuan on 2016/8/31.
 */
@SuppressLint("ValidFragment")
public class CheckImgAdapterFragment extends Fragment {

    private Context context;
    private String url;
    public CheckImgAdapterFragment(Context context,String url) {
        this.context = context;
        this.url=url;
    }
    public static CheckImgAdapterFragment create(Context context,String url) {

        CheckImgAdapterFragment checkImgAdapterFragment = new CheckImgAdapterFragment(context,url);

        return checkImgAdapterFragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.img_check, null);
        ImageView imageView= (ImageView) view.findViewById(R.id.check_img);
        LoaderImage.loaderImage(url,imageView);
        return view;
    }
}
