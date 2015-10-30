package com.meetu.db;



import com.meetu.R;
import com.meetu.fragment.Miliaofragment;
import com.meetu.fragment.Minefragment;
import com.meetu.fragment.HomePagefragment;
import com.meetu.fragment.Messagefragment;



public class TabDb {
	public static String[] getTabsTxt(){
		String[] tabs={"首页","觅聊","消息"," 我"};
		return tabs;
	}
	public static int[] getTabsImg(){
		int[] ids={R.drawable.tabbar_btn_acty_nor,R.drawable.tabbar_btn_chat_nor,R.drawable.tabbar_btn_massage_nor,R.drawable.tabbar_btn_mine_nor};
		return ids;
	}
	public static int[] getTabsImgLight(){
		int[] ids={R.drawable.tabbar_btn_acty_hl,R.drawable.tabbar_btn_chat_hl,R.drawable.tabbar_btn_massage_hl,R.drawable.tabbar_btn_mine_hl};
		return ids;
	}
	public static Class[] getFragments(){
		Class[] clz={HomePagefragment.class,Miliaofragment.class,Messagefragment.class,Minefragment.class};
		return clz;
	}

}
