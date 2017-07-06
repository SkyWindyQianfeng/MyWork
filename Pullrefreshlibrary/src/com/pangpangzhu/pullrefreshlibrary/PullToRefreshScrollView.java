package com.pangpangzhu.pullrefreshlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * 封装了ScrollView的下拉刷新
 * 
 * @author libingnan
 */
public class PullToRefreshScrollView extends PullToRefreshBase<PullToRefreshScrollView.ObservableScrollView> {
    /**
     * 构造方法
     * 
     * @param context context
     */
    public PullToRefreshScrollView(Context context) {
        this(context, null);
    }
    
    /**
     * 构造方法
     * 
     * @param context context
     * @param attrs attrs
     */
    public PullToRefreshScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    /**
     * 构造方法
     * 
     * @param context context
     * @param attrs attrs
     * @param defStyle defStyle
     */
    public PullToRefreshScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected ObservableScrollView createRefreshableView(Context context, AttributeSet attrs) {
        ObservableScrollView scrollView = new ObservableScrollView(context);
        scrollView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        return scrollView;
    }


    @Override
    protected boolean isReadyForPullDown() {
        return mRefreshableView.getScrollY() == 0;
    }


    @Override
    protected boolean isReadyForPullUp() {
        View scrollViewChild = mRefreshableView.getChildAt(0);
        if (null != scrollViewChild) {
            return mRefreshableView.getScrollY() >= (scrollViewChild.getHeight() - getHeight());
        }
        
        return false;
    }


    public void onRefreshComplete()
    {
        onPullDownRefreshComplete();
        onPullUpRefreshComplete();
    }

    @Override
    protected LoadingLayout createHeaderLoadingLayout(Context context, AttributeSet attrs) {
        return new AnimLoadingLayout(context);
    }


    public class ObservableScrollView extends ScrollView {

        private ScrollViewListener scrollViewListener = null;

        public ObservableScrollView(Context context) {
            super(context);
        }

        public ObservableScrollView(Context context, AttributeSet attrs,
                                    int defStyle) {
            super(context, attrs, defStyle);
        }

        public ObservableScrollView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public void setScrollViewListener(ScrollViewListener scrollViewListener) {
            this.scrollViewListener = scrollViewListener;
        }

        @Override
        protected void onScrollChanged(int x, int y, int oldx, int oldy) {
            super.onScrollChanged(x, y, oldx, oldy);
            if (scrollViewListener != null) {
                scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
            }
        }

    }

    public interface ScrollViewListener {

        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);

    }

}
