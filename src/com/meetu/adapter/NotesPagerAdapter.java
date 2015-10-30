package com.meetu.adapter;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



import com.meetu.R;
import com.meetu.activity.homepage.HomePageDetialActivity;
import com.meetu.entity.Notes;
import com.meetu.entity.PhotoWall;
import com.meetu.entity.Photolunbo;
import com.meetu.myapplication.MyApplication;




import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NotesPagerAdapter extends PagerAdapter {
	private Context  mContext;
	FinalBitmap finalBitmap;
	private List<Notes> Newslist;
	
	
	public NotesPagerAdapter(Context context, List<Notes> list) {
		super();
		this.mContext = context;
		this.Newslist = list;
		

	}

	@Override
	public int getCount() {

		return  Newslist.size() ;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
	
		return view==object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
	
		container.removeView((View)object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		Notes item=Newslist.get(position);
		View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_notes_channel,null);
			

		container.addView(view);
		return view;
	}
	

	

}
