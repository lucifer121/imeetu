package com.meetu.activity.homepage;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.meetu.R;
import com.meetu.R.layout;
import com.meetu.R.menu;
import com.meetu.adapter.BookingListViewAdapter;
import com.meetu.entity.Booking;
import com.meetu.tools.DensityUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Loader;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class JoinActivity extends Activity implements OnClickListener ,OnItemClickListener{
	private RelativeLayout backLayout;
	private ListView listView;
	private List<Booking> booklist=new ArrayList<Booking>();
	private BookingListViewAdapter adapter;
	private LinearLayout payLayout;
	private RelativeLayout paylRelativeLayout,payweixin,payzhifubao;
	private RelativeLayout visibleCenten4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		super.getWindow();
		setContentView(R.layout.activity_join);
		//数据加载
		loaddate();
				
		//事件绑定处理
		initView();
		
		
	}

	

	private void initView() {
		backLayout = (RelativeLayout) super
				.findViewById(R.id.back_join_homepager_rl);
		backLayout.setOnClickListener(this);
		listView=(ListView) super.findViewById(R.id.listview_piao_join_homepager);
		adapter=new BookingListViewAdapter(this, booklist);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		
		//给listview动态设置高度
		RelativeLayout.LayoutParams params=(LayoutParams) listView.getLayoutParams();
		params.height=booklist.size()*DensityUtil.dip2px(this, 70);
		listView.setLayoutParams(params);
		payLayout=(LinearLayout) super.findViewById(R.id.pay_join_homepager_ll);
		payLayout.setOnClickListener(this);
		paylRelativeLayout=(RelativeLayout) super.findViewById(R.id.pay_join_homepager_rl);
		paylRelativeLayout.setOnClickListener(this);
		payweixin=(RelativeLayout) super.findViewById(R.id.pay_weixin_rl);
		payweixin.setOnClickListener(this);
		payzhifubao=(RelativeLayout) super.findViewById(R.id.pay_zhifubao_rl);
		payzhifubao.setOnClickListener(this);
		visibleCenten4=(RelativeLayout) super.findViewById(R.id.center4_join_rl);
		
	}
	
	private void loaddate() {
		booklist=new ArrayList<Booking>();
		booklist.add(new Booking(11,"在校生",0,"99"));
		booklist.add(new Booking(22,"校友",10,"199"));
		booklist.add(new Booking(33,"投资人士",10,"免费"));
		booklist.add(new Booking(33,"其他",0,"999"));
		
		
	}

	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.back_join_homepager_rl :
				finish();

				break;
			case R.id.pay_join_homepager_rl:
				Toast.makeText(this, "点击了免费参加活动", Toast.LENGTH_SHORT).show();
				break;
			case R.id.pay_weixin_rl:
				Toast.makeText(this, "点击了微信支付", Toast.LENGTH_SHORT).show();
				break;
		 case R.id.pay_zhifubao_rl:
			 Toast.makeText(this, "点击了支付宝支付", Toast.LENGTH_SHORT).show();
				break;
			default :
				break;
		}
	}



	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		Toast.makeText(this,"position "+position+" id "+id, Toast.LENGTH_SHORT).show();
		adapter.setSelectedPosition(position);
		if(booklist.get(position).getPrice().equals("免费")){
			payLayout.setVisibility(View.INVISIBLE);
			paylRelativeLayout.setVisibility(View.VISIBLE);
			visibleCenten4.setVisibility(View.INVISIBLE);
		}else{
			payLayout.setVisibility(View.VISIBLE);
			paylRelativeLayout.setVisibility(View.INVISIBLE);
			visibleCenten4.setVisibility(View.VISIBLE);
		}
		
		adapter.notifyDataSetChanged();
		
		
		
	}

}
