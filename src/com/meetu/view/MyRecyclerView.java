package com.meetu.view;

import com.meetu.view.MyScrollView.OnScrollListener;
import com.meetu.view.MyScrollView.YScrollDetector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MyRecyclerView extends RecyclerView {
	private OnScrollListener onScrollListener;

	public MyRecyclerView(Context context) {
		super(context,null);
		
	}
	  public MyRecyclerView(Context context, AttributeSet attrs) {
		   super(context, attrs,0);	   
		  }
		  
		  public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
				super(context, attrs, defStyle);
			}
	
	 /** 
     * 设置滚动接口 
     * @param onScrollListener 
     */  					
    public void setOnScrollListenerBack(OnScrollListener onScrollListener) {  
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
            onScrollListener.onScrollBack(t);  
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
        public void onScrollBack(int scrollY);  
    }  
	

}
