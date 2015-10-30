//package com.meetu.tools;
//
//
//import android.content.Context;
//import android.text.ClipboardManager;
//
//
//
//
//
//public class Copy {
//	/** 
//	* 实现文本复制功能 
//	* add by wangqianzhou 
//	* @param content 
//	*/  
//	public static void copy(String str, Context context)  
//	{  
//	// 得到剪贴板管理器  
//		ClipboardManager clip = (ClipboardManager)getSystemService(context.CLIPBOARD_SERVICE);
//		clip.getText(); // 粘贴
//		clip.setText(str); // 复制
//	}  
//	/** 
//	* 实现粘贴功能 
//	* add by wangqianzhou 
//	* @param context 
//	* @return 
//	*/  
//	public static String paste(Context context)  
//	{  
//	// 得到剪贴板管理器  
//	ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);  
//	return cmb.getText().toString().trim();  
//	}  
//
//}
