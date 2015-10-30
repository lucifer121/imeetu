package com.meetu.adapter;

import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalBitmap;
import android.R.color;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;



import com.baidu.location.e.r;
import com.meetu.R;
import com.meetu.entity.Booking;
import com.meetu.entity.Huodong;


@SuppressLint("NewApi")
public class BookingListViewAdapter  extends BaseAdapter implements OnClickListener {

	private Context mContext;
	private List<Booking> newsList;
	
	private final int TYPE_COUNT=4;
//	private FinalBitmap fianlBitmap;
//	
	private int selectedPosition = -1;
	
	public BookingListViewAdapter(Context context,List<Booking> newsList){
		this.mContext=context;
		this.newsList=newsList;


	}
	/**
	 * 为选中的item设置背景 控件设置选中
	 * @param i
	 */
	public void setSelectedPosition(int i)
    {
        selectedPosition = i;
    }

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return TYPE_COUNT;
	}



	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		Log.d("lucifer","getCount()");
//		return newsList.size();
		return newsList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		Log.d("lucifer","getItem()");
		return newsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		Log.d("lucifer","getItemId()");
		return position;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		Booking item=newsList.get(position);
		if(convertView==null){
			holder=new ViewHolder();
//			//��layout.xmlת��ΪView
//			switch(item.getStyle()){
//			case 0:
				convertView=LayoutInflater.from(mContext).inflate(R.layout.item_buypiao, null);

			holder.tvTilte=(TextView)convertView.findViewById(R.id.name_buyPiao_join_homepager);
			holder.tvPrice=(TextView) convertView.findViewById(R.id.price_baypiao_join_homepager);
			holder.tvleftPrice=(TextView) convertView.findViewById(R.id.left_price_baypiao_join_homepager);
			holder.noTickets=(ImageView) convertView.findViewById(R.id.no_tickets_join);
			
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}

		holder.tvTilte.setText(item.getName());
		holder.tvPrice.setText(item.getPrice());
		if(item.getNumber()<=0){
			holder.noTickets.setVisibility(View.VISIBLE);
			holder.tvPrice.setTextColor(mContext.getResources().getColor(R.color.text_changehui));
			holder.tvleftPrice.setTextColor(mContext.getResources().getColor(R.color.text_changehui));
		}
		
		//动态设置选中的item的背景和textview前边的图片
		Drawable drawable = mContext.getResources().getDrawable(R.drawable.acty_join_btn_point_hl);
		drawable.setBounds(drawable.getIntrinsicWidth(), 0, 0, drawable.getIntrinsicHeight());
		Drawable drawable2 = mContext.getResources().getDrawable(R.drawable.acty_join_btn_point_nor);
		drawable2.setBounds(drawable.getIntrinsicWidth(), 0, 0, drawable.getIntrinsicHeight());
		if(selectedPosition == position){
			convertView.setBackgroundColor(mContext.getResources().getColor(R.color.buyticket_check));
			holder.tvTilte.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
			
		}else {
			convertView.setBackgroundColor(Color.WHITE);
			holder.tvTilte.setCompoundDrawablesWithIntrinsicBounds(drawable2, null, null, null);
		}

		return convertView;
	}
	
	
	private class ViewHolder{
		private TextView tvTilte;
		private TextView tvPrice;
		private TextView tvleftPrice;
		private ImageView noTickets;
		
		
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	



}
