package com.meetu.activity.homepage;




import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.avos.avoscloud.LogUtil.log;
import com.meetu.R;
import com.meetu.R.layout;
import com.meetu.R.menu;
import com.meetu.entity.Barrage;
import com.meetu.tools.DenUtil;
import com.meetu.tools.DensityUtil;
import com.meetu.tools.DisplayUtils;

import android.R.color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class BarrageActivity extends Activity implements OnClickListener{
	//放置弹幕的父布局
	private AbsoluteLayout barrarylayout;
	private int windowWidth;
	private float x;
	private Thread asknetThread,danmuThread;
	
	private List<Barrage> dataBarrages=new ArrayList<Barrage>();
	
	private Barrage myBarrage;
	int number=0,topHight;
	
	//父组件的高度
    private int validHeightSpace;
    //控件相关
    private ImageView headPhoto; 
    private TextView uName,uContent,topTitle;
    private RelativeLayout back,userJoinList;
    
    private int viewHight;//动态生成的view的高度 。考虑到上下间距。要稍大点
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		super.getWindow();
		setContentView(R.layout.activity_barrage);
		windowWidth = DisplayUtils.getWindowWidth(BarrageActivity.this);
		validHeightSpace=DensityUtil.dip2px(this, 350);
		loadData();
		initView();
		
		initChange();
		
//		final Message message = new Message();
//		asknetThread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				while(true){  
//	                try{  
//	                    Thread.sleep(10000);     // sleep 10ms 
//	                    message.what = 2;  
//	                    handler.sendMessage(message);  
//	                }catch (Exception e) {  
//	                }  
//	            }  
//			}
//		});
//        asknetThread.start();
        
        danmuThread =  new Thread(new MyThread());
        danmuThread.start();
	}
	private void loadData() {
		
		dataBarrages=new ArrayList<Barrage>();
		Barrage item=new Barrage();
		item.setId(0);
		item.setName("蛮子");
		item.setImg(R.drawable.mine_likelist_profile_default);
		item.setContent("我的大刀早已饥渴难耐");
		dataBarrages.add(item);
		
		Barrage item1=new Barrage();
		item1.setId(1);
		item1.setName("瑞文");
		item1.setImg(R.drawable.mine_likelist_profile_default);
		item1.setContent("我已经流浪如此之久");
		dataBarrages.add(item1);
		
		Barrage item2=new Barrage();
		item2.setId(2);
		item2.setName("赵云");
		item2.setImg(R.drawable.mine_likelist_profile_default);
		item2.setContent("一点寒茫先到 随后枪出如龙");
		dataBarrages.add(item2);
		
		Barrage item3=new Barrage();
		item3.setId(3);
		item3.setName("寒冰");
		item3.setImg(R.drawable.mine_likelist_profile_default);
		item3.setContent("你要来几发么？");
		dataBarrages.add(item3);
		
		Barrage item4=new Barrage();
		item4.setId(4);
		item4.setName("石头人");
		item4.setImg(R.drawable.mine_likelist_profile_default);
		item4.setContent("俺很生气，后果很严重");
		dataBarrages.add(item4);
		
		Barrage item5=new Barrage();
		item5.setId(5);
		item5.setName("飞机");
		item5.setImg(R.drawable.mine_likelist_profile_default);
		item5.setContent("大部分人都会打飞机！这对飞机来说很不公平!");
		dataBarrages.add(item5);
		
		
		
	}
	private void initView() {
		barrarylayout=(AbsoluteLayout) super.findViewById(R.id.top_barrage_rl);
//		RelativeLayout.LayoutParams params=(LayoutParams) barrarylayout.getLayoutParams();
//		validHeightSpace=params.height;
//		barrarylayout.setLayoutParams(params);

		 log.e("lucifer", "validHeightSpace="+validHeightSpace);
		
		headPhoto=(ImageView) super.findViewById(R.id.userHeadPhone_barrage_img);
		uName=(TextView) super.findViewById(R.id.userName_barrage_tv);
		uContent=(TextView) super.findViewById(R.id.content_bottom_barrage_tv);
		
		topTitle=(TextView) super.findViewById(R.id.title_top_barrary_tv);
		back=(RelativeLayout) super.findViewById(R.id.back_barrage_rl);
		back.setOnClickListener(this);
		userJoinList=(RelativeLayout) super.findViewById(R.id.userjoin_barrary_rl);
		userJoinList.setOnClickListener(this);
		
	}
	private void initChange() {
		viewHight=DensityUtil.dip2px(this, 70);//动态生成的view的高度 。考虑到上下间距。稍大点
		
		
	}
	/**
	 * 创建一个新的view布局
	 */
	@SuppressLint("ResourceAsColor")
	private void initAdd( final Barrage barrage) {
		topHight=(number%(validHeightSpace/viewHight))*viewHight+viewHight;
		
		number++;
		
		
		
		LinearLayout ll = new LinearLayout(this);
		
		//设置RelativeLayout布局的宽高
		
		@SuppressWarnings("deprecation")
		AbsoluteLayout.LayoutParams relLayoutParams=new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,windowWidth,topHight);
		
		
		LinearLayout.LayoutParams imageLp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		ImageView imageView = new ImageView(this);
		
		imageView.setId(1);
		imageView.setClickable(true); 
//		imageView.setImageResource(R.drawable.mine_likelist_profile_default);
		imageView.setImageResource(barrage.getImg());
		imageView.setTag(barrage.getId());
		
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(BarrageActivity.this,v.getTag()+"" , Toast.LENGTH_SHORT).show();
				headPhoto.setImageResource(barrage.getImg());
				uName.setText(barrage.getName());
				uContent.setText(barrage.getContent());
			}
		});
		
		ll.addView(imageView,imageLp);
		
		LinearLayout.LayoutParams textLp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		
		textLp.setMargins(DensityUtil.dip2px(this, 8), DensityUtil.dip2px(this, 5), 0, DensityUtil.dip2px(this, 5));
		TextView tv = new TextView(this);
		tv.setText(barrage.getContent());
//		tv.setText("    "+barrage.getContent()+"    ");//4个空格
//		tv.setMaxWidth(DisplayUtils.getWindowWidth(this)-DensityUtil.dip2px(this, 50));
		tv.setGravity(Gravity.CENTER);
		
		
		tv.setBackgroundResource(R.drawable.barrage_text_bg);
		//动态设置的padding一定要放在设置背景后面才能起作用
		tv.setPadding(24, 10, 24, 10);
		tv.setClickable(true);
		tv.setTextSize(13);
		tv.setTag(barrage.getId());
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(BarrageActivity.this,v.getTag()+"" , Toast.LENGTH_SHORT).show();
				headPhoto.setImageResource(barrage.getImg());
				uName.setText(barrage.getName());
				uContent.setText(barrage.getContent());
			}
		});
		tv.setId(2);
		ll.addView(tv,textLp);
		ll.setLayoutParams(relLayoutParams);
		
		
		barrarylayout.addView(ll);
		MyDanMu(tv.getText().toString(),tv,imageView,ll);
	}

	
	
	/**
	 * 给生成的view设置属性位移动画
	 * @param title
	 * @param textview
	 * @param imageView
	 * @param relativeLayout
	 */
	@SuppressLint("NewApi")
	public void MyDanMu(String title,final TextView textview,final ImageView imageView,final LinearLayout relativeLayout){
		int width = DisplayUtils.getWindowWidth(BarrageActivity.this);
        int strwidth =DenUtil.dp2px(BarrageActivity.this, DenUtil.GetTextWidth(title, 40));
        x=(float)width+strwidth;
        ValueAnimator animator = ValueAnimator.ofFloat(0, -strwidth-width);  
        Log.e("TranslationX", ""+(-strwidth-width));
       
     animator.setTarget(textview); 

     animator.setDuration(8000).start();  
     animator.setInterpolator(new LinearInterpolator());
     animator.addListener(new AnimatorListener() {
		
		@Override
		public void onAnimationStart(Animator arg0) {
			
			
		}
		
		@Override
		public void onAnimationRepeat(Animator arg0) {
			
			
		}
		
		@Override
		public void onAnimationEnd(Animator arg0) {
		
			barrarylayout.removeView(relativeLayout);
		}
		
		@Override
		public void onAnimationCancel(Animator arg0) {
			// TODO Auto-generated method stub
			
		}
	});
     
     animator.addUpdateListener(new AnimatorUpdateListener()  
     {  
         @Override  
         public void onAnimationUpdate(ValueAnimator animation)  
         {  
        	 relativeLayout.setTranslationX((Float)animation.getAnimatedValue());

 			
 			
         }  
         
     });
     
}
	/**
	 * 启动线程
	 * @author Administrator
	 *
	 */
	 public class MyThread implements Runnable{      // thread  
	        @Override  
	        public void run(){  
	            while(true){  
	                try{  
	                	int N = dataBarrages.size();

	                	 for (int i = 0; i < N; i++) {
	                		 Message message = Message.obtain(); 
	     	                handler.obtainMessage(1, i, 0).sendToTarget();
	     	                SystemClock.sleep(1000);
	     	            }
	                }catch (Exception e) {  
	                }  
	            }  
	        }  
	    }  
	 
	/**
	 * handle 线程处理
	 */
	final Handler handler = new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what) {
				case 1 :
//
//					Barrage barrage=dataBarrages.get(3);
//					initAdd(barrage);
					
					if(dataBarrages!=null){
						int index = msg.arg1;
						 String content = dataBarrages.get(index).getContent();
						 myBarrage=new Barrage();
						 myBarrage=dataBarrages.get(index);
						 initAdd(myBarrage);
					}
						
					break;

				case 2 :
					
					break;
			}
		}
	};
	

    @Override  
    public void onDestroy()  
    {  
//    	asknetThread.stop();
//		danmuThread.stop();
    	handler.removeCallbacks(danmuThread);
    	
    	super.onDestroy();  
    }
	@Override
	public void onBackPressed() {
		
		finish();
		super.onBackPressed();
	}
	@Override
	public void onClick(View v) {
	switch (v.getId()) {
		case R.id.back_barrage_rl :
			finish();
			
			break;
		case R.id.userjoin_barrary_rl:
			Intent intent=new Intent(this,JoinUsersActivity.class);
			startActivity(intent);
			
			break;

		default :
			break;
	}
		
	} 
    


}
