package com.meetu.tools;

import android.content.Context;
import android.content.res.Resources;

public class StringToDrawbleId {
	
	/**
	 * 通过String id 获取资源的id
	 * 
	 * @param context
	 * @param string id名
	 * @return 资源的id
	 */
	public static int getDrawableId(Context context,String string){
		Resources resources = context.getResources();
		 int resID = context.getResources().getIdentifier(string,
					"drawable", context.getPackageName());
		return resID;
	}

}
