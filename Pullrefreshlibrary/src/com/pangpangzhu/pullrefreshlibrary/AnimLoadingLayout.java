package com.pangpangzhu.pullrefreshlibrary;

import com.example.pullrefreshlibrary.R;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 这个类封装了下拉刷新的布局
 * 
 * @author libingnan
 */
public class AnimLoadingLayout extends LoadingLayout {
    /**旋转动画的时间*/
    static final int ROTATION_ANIMATION_DURATION = 1200;
    /**动画插值*/
    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    /**Header的容器*/
    private RelativeLayout mHeaderContainer;
    /**箭头图片*/
    private ImageView mArrowImageView;
    /**状态提示TextView*/
    private TextView mHintTextView;
    /**最后更新时间的TextView*/
    private TextView mHeaderTimeView;
    /**最后更新时间的标题*/
    private TextView mHeaderTimeViewTitle;
    /**动画*/
    private static AnimationDrawable animationDrawable;
    /**
     * 构造方法
     * 
     * @param context context
     */
    public AnimLoadingLayout(Context context) {
        super(context);
        init(context);
    }

    /**
     * 构造方法
     * 
     * @param context context
     * @param attrs attrs
     */
    public AnimLoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     * 
     * @param context context
     */
    private void init(Context context) {   	
        mHeaderContainer = (RelativeLayout) findViewById(R.id.pull_to_refresh_header_content);        
        mHintTextView = (TextView) findViewById(R.id.pull_to_refresh_header_hint_textview);
        mHeaderTimeView = (TextView) findViewById(R.id.pull_to_refresh_header_time);
        mHeaderTimeViewTitle = (TextView) findViewById(R.id.pull_to_refresh_last_update_time_text);      
        mArrowImageView = (ImageView) findViewById(R.id.pull_to_refresh_header_arrow);
        mArrowImageView.setScaleType(ScaleType.CENTER_INSIDE);
        mArrowImageView.setImageResource(R.drawable.pull_to_refresh_drawable);             
        animationDrawable = (AnimationDrawable) mArrowImageView.getDrawable();  
       
    }
    
    @Override
    protected View createLoadingView(Context context, AttributeSet attrs) {
        View container = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header2, null);
        return container;
    }

    @Override
    public void setLastUpdatedLabel(CharSequence label) {
        // 如果最后更新的时间的文本是空的话，隐藏前面的标题
        mHeaderTimeViewTitle.setVisibility(TextUtils.isEmpty(label) ? View.INVISIBLE : View.VISIBLE);
        mHeaderTimeView.setText(label);
    }

    @Override
    public int getContentSize() {
        if (null != mHeaderContainer) {
            return mHeaderContainer.getHeight();
        }
        
        return (int) (getResources().getDisplayMetrics().density * 60);
    }
    
    @Override
    protected void onStateChanged(State curState, State oldState) {
        super.onStateChanged(curState, oldState);
    }

    @Override
    protected void onReset() {
    	resetAnim();
        mHintTextView.setText(R.string.pull_to_refresh_header_hint_normal);
    }

    @Override
    protected void onReleaseToRefresh() {
        mHintTextView.setText(R.string.pull_to_refresh_header_hint_ready);
    }
    
    @Override
    protected void onPullToRefresh() {
        mHintTextView.setText(R.string.pull_to_refresh_header_hint_normal);
    }
    
    @Override
    protected void onRefreshing() {
    	resetAnim();
        animationDrawable.start();
        mHintTextView.setText(R.string.pull_to_refresh_header_hint_loading);
    }
    
    
    /**
     * 重置动画
     */
    private void resetAnim() {
        mArrowImageView.clearAnimation();
        animationDrawable.stop();
        mArrowImageView.setImageResource(R.drawable.refresh01);
        mArrowImageView.setImageResource(R.drawable.pull_to_refresh_drawable);
        animationDrawable = (AnimationDrawable) mArrowImageView.getDrawable();
    }
}
