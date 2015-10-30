package com.meetu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
/**重写ScrollView
 * 解决ScrollView嵌套ViewPager出现的滑动冲突问题
 * 计算上下滑动的距离
 *
 */

public class MyScrollView extends ScrollView {
	 private OnScrollListener onScrollListener;
	 private boolean enableScroll=true;
	 
	 
	private boolean canScroll;
	private GestureDetector mGestureDetector;
	  View.OnTouchListener mGestureListener;
	  
	  
	  public boolean isEnableScroll() {
		return enableScroll;
	}

	public void setEnableScroll(boolean enableScroll) {
		this.enableScroll = enableScroll;
	}

	public MyScrollView(Context context) {
			this(context, null);
		}
	 
	  public MyScrollView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    mGestureDetector = new GestureDetector(new YScrollDetector());
	    canScroll = true;
	   
	  }
	  
	  public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
		}
	  
	

	@Override
	  public boolean onInterceptTouchEvent(MotionEvent ev) {
	    if(ev.getAction() == MotionEvent.ACTION_UP)
	      canScroll = true;
	    return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
	  }
	 /**
	  * 通过x方向滑动的距离还是y滑动的距离，让scroll响应
	  * @author Administrator
	  *
	  */
	  class YScrollDetector extends SimpleOnGestureListener {
	    @Override
	    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
	      if(canScroll)
	        if (Math.abs(distanceY) >= Math.abs(distanceX))
	          canScroll = true;
	        else
	          canScroll = false;
	      return canScroll;
	    }
	  }
	  
	  /** 
	     * 设置滚动接口 
	     * @param onScrollListener 
	     */  
	    public void setOnScrollListener(OnScrollListener onScrollListener) {  
	        this.onScrollListener = onScrollListener;  
	    }  
	    
	    @Override  
	    public int computeVerticalScrollRange() {  
	        return super.computeVerticalScrollRange();  
	    }  
	    @Override  
	    protected void onScrollChanged(int l, int t, int oldl, int oldt) {  
	        super.onScrollChanged(l, t, oldl, oldt);  
	        if(onScrollListener != null){  
	            onScrollListener.onScroll(t);  
	        }  
	    }  
	    
	    
	    @Override
		public boolean onTouchEvent(MotionEvent ev) {
			// TODO Auto-generated method stub
	    	
			return super.onTouchEvent(ev);
		}
		/** 
	     *  
	     * 滚动的回调接口 
	     *  
	     * @author 
	     * 
	     */  
	    public interface OnScrollListener{  
	        /** 
	         * 回调方法， 返回MyScrollView滑动的Y方向距离 
	         * @param scrollY 
	         *              、 
	         */  
	        public void onScroll(int scrollY);  
	    }  
}


