package com.railwayticket.myapp.mywork.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.railwayticket.myapp.mywork.R;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class BaiduMapSearchPopupWindow extends PopupWindow implements View.OnClickListener{

    private PopupWindow popup;
    private Context context;
    private View showView;
    private View contentView;

    public BaiduMapSearchPopupWindow(Context context, View showView, View contentView) {
        this.context = context;
        this.showView = showView;
        this.contentView = contentView;
    }


    @Override
    public void onClick(View arg0) {

    }

    public void getPopuWindow() {

        if (popup == null) {

            popup = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            popup.setOutsideTouchable(true);
            popup.setFocusable(true);
            popup.setAnimationStyle(R.style.popwin_anim_style);
            // popup.setOnDismissListener(this);
        }
        if (popup.isShowing())
            popup.dismiss();
        else
            popup.showAtLocation(showView, Gravity.BOTTOM, 0, 0);
    }

    public void dismiss() {
        popup.dismiss();
    }


}
