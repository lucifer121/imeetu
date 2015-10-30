package com.meetu.tools;

import android.content.Context;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DenUtil {

	/** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dp2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dp(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    
    /**
	 * 获得屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context)
	{
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}
	/**
	 * 获得String的显示宽度值
	 * @param 要计算的字符串
	 * @param 字体大小
	 * @return 长度值
	 */
	public static int GetTextWidth(String text, float Size) 
    { 
         TextPaint FontPaint = new TextPaint();
         FontPaint.setTextSize(Size);
         return (int) FontPaint.measureText(text);
    }
	
}
