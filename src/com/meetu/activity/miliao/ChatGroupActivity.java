package com.meetu.activity.miliao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;

import com.avos.avoscloud.LogUtil.log;
import com.baidu.location.h.m;



import com.meetu.R;
import com.meetu.R.layout;
import com.meetu.adapter.ChatmsgsListViewAdapter;
import com.meetu.entity.ChatEmoji;
import com.meetu.entity.Chatmsgs;
import com.meetu.fragment.ChatFragment;
import com.meetu.fragment.HomePagefragment;
import com.meetu.myapplication.MyApplication;
import com.meetu.sqlite.ChatmsgsDao;
import com.meetu.tools.BitmapCut;

import com.meetu.tools.DensityUtil;
import com.meetu.tools.DisplayUtils;
import com.meetu.tools.StringToDrawbleId;

import com.meetu.tools.UriToimagePath;
import com.meetu.tools.UrlLocationToBitmap;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChatGroupActivity extends Activity implements OnClickListener,OnItemClickListener {
	private Context context;
	private RelativeLayout backLayout;
	private ImageView  face;
	private LinearLayout faceLayout;
	private RelativeLayout listviewLayout;
	private Boolean faceBoolean=false;//表情键盘的显示状态
	private EditText mEditText;
	private RelativeLayout userlayout;
	
	//解析xml相关
	private EmojiParser parser;  
    private static List<ChatEmoji> chatEmojis; 
    private List<String> staticFacesList;
    
    //添加表情相关
    
	private ImageView image_face;//表情图标
	// 7列3行
	private int columns;//表情的列数
	private int rows =3;//表情的行数
	private int chatEmojisNumber;//表情的总数
	private List<View> views = new ArrayList<View>();
	private LinearLayout chat_face_container;//表情布局
	private ViewPager mViewPager;
	private LinearLayout mDotsLayout;
	private int current = 0;//表情第几页
	private List<ChatEmoji> subList;
	private List<ChatEmoji> subAllList=new ArrayList<ChatEmoji>();
	private FaceGVAdapter faceGVAdapter;
	private String emojiKey;
	
	/** 表情页的监听事件 */
	private OnCorpusSelectedListener mListener;
	/**
	 * 输入框内显示表情的宽高
	 */
	private static int emojiHigh;
	private static int emojiWeight;
	
	//聊天列表 相关
	
	private List<Chatmsgs> chatmsgsList=new ArrayList<Chatmsgs>();
	private List<Chatmsgs> chatmsgsCacheList=new ArrayList<Chatmsgs>();
	
	private Chatmsgs chatmsgs;
	private ListView mChatmsgsListView;
	
	private ChatmsgsListViewAdapter mChatmsgsAdapter;
	
	private RelativeLayout sendlLayout;
	private ImageView send;
	private ChatmsgsDao chatmsgsDao=new ChatmsgsDao(this);
	private ImageView photo,camera;//点击发图片
	private SimpleDateFormat sd;
	/**
	 * 聊天列表内显示表情的宽高
	 */
	private static int emojiChatHight;
	private static int emojiChatWeight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去除title
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏
		super.getWindow();
		setContentView(R.layout.fragment_chat);
		columns=DisplayUtils.getWindowWidth(this)/DensityUtil.dip2px(this, 45);

		/**
		 * 删除所有本地聊天数据
		 */
//		chatmsgsDao.deleteAll();
		
		loadEmoji();
		
		loadData();
		
		initView();
		/**
		 * 
		 */
		
		InitViewPager();

		
	}
	private void loadData() {
		// TODO Auto-generated method stub
		chatmsgsList=new ArrayList<Chatmsgs>();
		
		Chatmsgs item=new Chatmsgs();
		item.setContent("今天是个好日子");
		item.setSendTimeStamp("2015-10-09");
		item.setChatMsgType(0);
		item.setConversationId("1");
		chatmsgsList.add(item);
		
		Chatmsgs item1=new Chatmsgs();
		item1.setContent("明天是个好日子");
		item1.setChatMsgType(0);
		item1.setSendTimeStamp("2015-10-10");	
		item.setConversationId("1");
		chatmsgsList.add(item1);
		
//		handler.sendEmptyMessage(1);
		
		
//		for(Chatmsgs chatmsgs:chatmsgsList){
//			
//			
//			chatmsgsDao.insert(chatmsgs);
//			
//		}
		//根据对话id取出相应的缓存消息
		chatmsgsCacheList=chatmsgsDao.getChatmsgsList("1");
		
		
	//	handler.sendEmptyMessage(1);
		
		
	}
	private void initView() {
		userlayout=(RelativeLayout) super.findViewById(R.id.userList_miliao_chat_rl);
		userlayout.setOnClickListener(this);
		emojiHigh=DensityUtil.dip2px(this, 24);
		emojiWeight=DensityUtil.dip2px(this, 24);
		emojiChatHight=DensityUtil.dip2px(this, 40);
		emojiChatWeight=DensityUtil.dip2px(this, 40);
		sd=new SimpleDateFormat("MM-dd HH:mm");
		backLayout = (RelativeLayout) super
				.findViewById(R.id.back_miliao_chat_rl);
		backLayout.setOnClickListener(this);
		face=(ImageView) super.findViewById(R.id.chat_face_container_img);
		face.setOnClickListener(this);
		faceLayout=(LinearLayout) super.findViewById(R.id.chat_face_container);
		listviewLayout=(RelativeLayout) super.findViewById(R.id.listView_chatFragment_rl);
		listviewLayout.setOnClickListener(this);
		mEditText=(EditText) super.findViewById(R.id.input_chat_fragment_et);

		mEditText.setOnClickListener(this);
		
		//表情相关
		//表情布局
				chat_face_container=(LinearLayout) findViewById(R.id.chat_face_container);
				mViewPager = (ViewPager) findViewById(R.id.face_viewpager);
				mViewPager.setOnPageChangeListener(new PageChange());
				//表情下小圆点
				mDotsLayout = (LinearLayout) findViewById(R.id.face_dots_container);
				
		//聊天列表 相关
		mChatmsgsListView=(ListView) super.findViewById(R.id.listView_chatFragment);
		
//		mChatmsgsAdapter=new ChatmsgsListViewAdapter(this, chatmsgsList);
		
		mChatmsgsAdapter=new ChatmsgsListViewAdapter(this, chatmsgsCacheList);
		
		mChatmsgsListView.setAdapter(mChatmsgsAdapter);
		mChatmsgsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				if(faceBoolean==true){
					faceLayout.setVisibility(View.GONE);
					faceBoolean=false;
				}
				/**
				 * 隐藏默认输入软键盘
				 */			
				((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ChatGroupActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  
				
				Chatmsgs item=chatmsgsCacheList.get(position);
				if(item.getChatMsgType()==0||item.getChatMsgType()==1){
//					Toast.makeText(context, ""+item.getContent(), Toast.LENGTH_SHORT).show();
					log.e("lucifer", ""+item.getContent()+" id=="+item.getMessageCacheId()+",fangxiang=="+item.getChatMsgDirection());
				}
				
				
			}
		});
		
		mChatmsgsListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Chatmsgs item=chatmsgsCacheList.get(position);
				showDialog(item);
				log.e("lucifer"+"长按"+item.getContent()+" id=="+item.getMessageCacheId());
				return false;
			}
			
		});
		
		mChatmsgsListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
		mChatmsgsListView.setStackFromBottom(true);
		
		sendlLayout=(RelativeLayout) super.findViewById(R.id.send_chat_fragment_rl);
		sendlLayout.setOnClickListener(this);
		send=(ImageView) super.findViewById(R.id.send_chat_fragment_img);
		
		photo=(ImageView) super.findViewById(R.id.chat_photo_container_img);
		photo.setOnClickListener(this);
		camera=(ImageView) super.findViewById(R.id.chat_camera_container_img);
		camera.setOnClickListener(this);
	}
	
	private void showDialog(final Chatmsgs item) {
		final  AlertDialog portraidlg=new AlertDialog.Builder(this).create();
		portraidlg.show();
		Window win=portraidlg.getWindow();
		win.setContentView(R.layout.item_chatmessage_selector);
		TextView copy=(TextView) win.findViewById(R.id.copy_item_chatmessage_tv);
		copy.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.e("复制");
				portraidlg.dismiss();
				if(item.getChatMsgType()==0||item.getChatMsgType()==1){
					
					CopyContent(item.getContent());
				}		
			}			
		});
		
		TextView delete=(TextView) win.findViewById(R.id.delete_item_chatmessage_tv);
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				log.e("删除");
				portraidlg.dismiss();
				deleteChatMessageCache(""+item.getMessageCacheId());				
			}
		
		});
		//点击item的上下区域 dralog消失
		View topView=win.findViewById(R.id.top_item_chatmessage_dialog_view);
		topView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				portraidlg.dismiss();
				
			}
		});
		View bottomView=win.findViewById(R.id.bottom_item_chatmessage_dialog_view);
		bottomView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				portraidlg.dismiss();   				
			}
		});

		
	}
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	private void CopyContent(String content) {
		// TODO Auto-generated method stub
	//	Copy.copy(content,this);
		ClipboardManager clip = (ClipboardManager)getSystemService(this.CLIPBOARD_SERVICE);
		
		clip.setText(content); // 复制
	}
	 
	
	/**
	 * 删除本地缓存中本条信息
	 * @param messageCacheId 消息缓存的id	 
	 */
	private void deleteChatMessageCache(String messageCacheId) {
		// TODO Auto-generated method stub
		chatmsgsDao.delete(messageCacheId);
		handler.sendEmptyMessage(2);	
	}

	/**
	 * 获取表情a 
	 */
	private void loadEmoji() {
		 
		try {
			 InputStream is = getAssets().open("expressionImage_custom.xml");
			  parser = new XmlEmojifPullHelper();  
//			 parser=new XmlEmojiSaxBookParser();
				chatEmojis = parser.parse(is);
				
				chatEmojisNumber=chatEmojis.size();
				 staticFacesList = new ArrayList<String>();

				 for (ChatEmoji emoji : chatEmojis) {


		              //根据String类型id获取对应资源id
		              int resID = this.getResources().getIdentifier(emoji.getFaceName(),
								"drawable", this.getPackageName());
		              log.e("lucifer222222","name"+emoji.getFaceName()+" resID=="+resID);		              
		              emoji.setId(resID);
		              staticFacesList.add(""+resID);     
		          }  
//				 ChatEmoji chatEmoji=new ChatEmoji();
//				 for(int i=0;i<chatEmojis.size();i++){
//					 int resID = this.getResources().getIdentifier(chatEmojis.get(i).getFaceName(),
//								"drawable", this.getPackageName());
//					 chatEmoji.setId(resID);
//					 
//				 }
		} catch (IOException e1) {
			log.e("2",e1);
			e1.printStackTrace();
		} 
		 catch (Exception e) {
			
			 log.e("3", e);
			e.printStackTrace();
		}  
         
		
	}

	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.back_miliao_chat_rl:
			finish();

			break;
		case R.id.chat_face_container_img:
			if(faceBoolean==false){
				/**
				 * 隐藏默认输入软键盘
				 */			
				((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ChatGroupActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  
				
				faceLayout.setVisibility(View.VISIBLE);
				faceBoolean=true;
				
				
			}else{
				faceLayout.setVisibility(View.GONE);
				faceBoolean=false;
			}
						
			break;
			
		case R.id.listView_chatFragment_rl:
			faceLayout.setVisibility(View.GONE);
			faceBoolean=false;
				break;
		case R.id.input_chat_fragment_et:
			if(faceBoolean=true){
				faceLayout.setVisibility(View.GONE);
				faceBoolean=false;
			}
			break;
			/**
			 * 点击发送按钮
			 */
		case R.id.send_chat_fragment_rl:
			//发送消息
			 sendChatmessage();
			break;
		//图片消息
		case R.id.chat_photo_container_img:
			sendChatPhotoMessage();
			break;
		case R.id.chat_camera_container_img:
			sendChatCameraMessage();
			break;
		case R.id.userList_miliao_chat_rl:
			Intent intent=new Intent(this,MiLiaoInfoActivity.class);
			startActivity(intent);
		
		default:
			break;
		}
	}
	private void sendChatCameraMessage() {
		// TODO Auto-generated method stub
		Intent intent2=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
				"/chat_picture.png")));
		
		startActivityForResult(intent2, 22);
		
	}
	private void sendChatPhotoMessage() {
		// TODO Auto-generated method stub
		Intent intent1=new Intent(Intent.ACTION_PICK,null);
		intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
		startActivityForResult(intent1, 11);
		
	}
	private Bitmap headerPortait;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case 11:
			if(resultCode==this.RESULT_OK){

				Uri uri = data.getData(); 
				log.e("lucifer", uri.toString()+" ,filepath=="+UriToimagePath.getImageAbsolutePath(this, uri));
				String fileName=UriToimagePath.getImageAbsolutePath(this, uri);
//				headerPortait=UrlLocationToBitmap.convertToBitmap(url.toString(), 160, 160);
//				headerPortait=UriToBitmap.getBitmapFromUri(this, uri);
				sendChatPhoto(fileName);
//				send.setImageBitmap(headerPortait);
				
				
			}
			break ;
		case 22:
			if(resultCode==this.RESULT_OK){
				File temp=new File(Environment.getExternalStorageDirectory()
						+ "/chat_picture.png");
				
				String fileName=temp.toString();
				log.e("lucifer1",fileName.toString());
//				Bitmap bm=UriToBitmap.getBitmapFromUri(mContext, uri);
			//	holder.photo.setImageBitmap(bm);
				
				BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
				Bitmap bmp = BitmapFactory.decodeFile(fileName, options);
				
				saveHeadImg(bmp);
				
//				Uri uri=Uri.fromFile(temp);
//				log.e("lucifer", uri.toString()+" ,filepath=="+UriToimagePath.getImageAbsolutePath(this, uri)+",temp=="+temp);
//				String fileName=UriToimagePath.getImageAbsolutePath(this, uri);
				
			
		}
			
			break;


		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	/**
	 * 把要上传的图片存到本地sd卡上
	 * @param photo
	 */
	public void saveHeadImg(Bitmap photo){
		String uuid = UUID.randomUUID().toString();	 
	    String  picName = "/"+uuid + ".jpg";
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(new File(Environment.getExternalStorageDirectory()+picName));
			photo.compress(CompressFormat.PNG, 100, fos);	
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}finally{
			
				try {
					if(fos!=null)fos.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}
		
		
		File temp=new File(Environment.getExternalStorageDirectory()
				+ picName);
		log.e("lucifer xin", "temp=="+temp.toString());
		sendChatPhoto(temp.toString());
	}
	
	private void sendChatPhoto(String uir) {
		// TODO Auto-generated method stub
		Chatmsgs mchatmsgs=new Chatmsgs();
	//	mchatmsgs.setImgMsgImageUrl(imgMsgImageUrl);
		mchatmsgs.setImgMsgImageUrl(uir.toString());
		mchatmsgs.setConversationId("1");
		mchatmsgs.setChatMsgType(2);
	//	mchatmsgs.setSendTimeStamp(sd.format(new Date()));
//		long time=(new Date()).getTime();
//		mchatmsgs.setSendTimeStamp(Long.toString(time));
		isShowTime(mchatmsgs);
		log.e("lucifer time", ""+mchatmsgs.getIsShowTime());
		chatmsgsDao.insert(mchatmsgs);
		
		//TODO		测试类型 加了左边布局的数据	
		Chatmsgs item=new Chatmsgs();
		item.setImgMsgImageUrl(uir.toString());
		item.setConversationId("1");
		item.setChatMsgType(3);
	//	item.setSendTimeStamp(sd.format(new Date()));
//		long time1=(new Date()).getTime();
//		mchatmsgs.setSendTimeStamp(Long.toString(time1));
		isShowTime(item);
		chatmsgsDao.insert(item);
		
		handler.sendEmptyMessage(1);
		
		
	}
	/**
	 * 发送普通文本消息 然后通知线程更新UI
	 *
	 */
	private void sendChatmessage() {
		// TODO 测试类型 加了左边布局的数据
		Chatmsgs mchatmsgs=new Chatmsgs();
		if(mEditText.getText().length()!=0){
			String mcontentString=mEditText.getText().toString();
			mchatmsgs.setContent(mcontentString);
			mchatmsgs.setConversationId("1");
			mchatmsgs.setChatMsgType(0);
			mchatmsgs.setChatMsgDirection(0);
//			mchatmsgs.setSendTimeStamp(sd.format(new Date()));
//			long time=(new Date()).getTime();
//			mchatmsgs.setSendTimeStamp(Long.toString(time));
////			chatmsgsList.add(mchatmsgs);
//			if(chatmsgsCacheList.size()==0){
//				//显示时间
//				mchatmsgs.setIsShowTime(0);
//			}else{
//				String timeLaString=chatmsgsCacheList.get(chatmsgsCacheList.size()-1).getSendTimeStamp();
//				Long timelastLong=Long.parseLong(timeLaString);
//				if(time-timelastLong>=60000){
//					mchatmsgs.setIsShowTime(0);
//				}else{
//					mchatmsgs.setIsShowTime(1);
//				}
//			};
			
			isShowTime(mchatmsgs);
			log.e("lucifer time", ""+mchatmsgs.getIsShowTime());
			
			chatmsgsDao.insert(mchatmsgs);
//TODO		测试类型 加了左边布局的数据	
			Chatmsgs item=new Chatmsgs();
			item.setContent(mcontentString);
			item.setConversationId("1");
			item.setChatMsgType(1);
			item.setChatMsgDirection(1);
//			item.setSendTimeStamp(sd.format(new Date()));
			
//			long time2=(new Date()).getTime();
//			item.setSendTimeStamp(Long.toString(time2));
			isShowTime(item);
			log.e("lucifer time", ""+item.getIsShowTime());
			chatmsgsDao.insert(item);
			mEditText.setText("");
			handler.sendEmptyMessage(1);

		}else{
			Toast.makeText(this, "发送内容不能为空", Toast.LENGTH_SHORT).show();
		}
	}
	/**
	 * 简单封装 添加时间戳 和是否显示时间
	 * @param chatmsgs
	 */
	private void isShowTime(Chatmsgs chatmsgs) {
		chatmsgsCacheList.clear();
		chatmsgsCacheList.addAll(chatmsgsDao.getChatmsgsList("1"));
		long time=(new Date()).getTime();
		chatmsgs.setSendTimeStamp(Long.toString(time));
//		chatmsgsList.add(mchatmsgs);
		if(chatmsgsCacheList.size()==0){
			//显示时间
			chatmsgs.setIsShowTime(0);
		}else{
			String timeLaString=chatmsgsCacheList.get(chatmsgsCacheList.size()-1).getSendTimeStamp();
			log.e("lucifer","size=="+chatmsgsCacheList.size()+"   timeLaString=="+timeLaString);
			Long timelastLong=Long.parseLong(timeLaString);
			if(time-timelastLong>=60000){
				chatmsgs.setIsShowTime(0);
			}else{
				chatmsgs.setIsShowTime(1);
			}
		};
		
	}
	/*
	 * 初始表情 *
	 */
	private void InitViewPager() {
		// 获取页数
		for (int i = 0; i < getPagerCount(); i++) {
			views.add(viewPagerItem(i));
			LayoutParams params = new LayoutParams(16, 16);
			mDotsLayout.addView(dotsItem(i), params);
		}
		FaceVPAdapter mVpAdapter = new FaceVPAdapter(views);
		mViewPager.setAdapter(mVpAdapter);
		mDotsLayout.getChildAt(0).setSelected(true);
	}
	
	private View viewPagerItem(int position) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.face_gridview, null);//表情布局
		GridView gridview = (GridView) layout.findViewById(R.id.chart_face_gv);
		/**
		 * 注：因为每一页末尾都有一个删除图标，所以每一页的实际表情columns *　rows　－　1; 空出最后一个位置给删除图标
		 * */
		subList = new ArrayList<ChatEmoji>();
		//TODO
		subList.addAll(chatEmojis
				.subList(position * (columns * rows - 1),
						(columns * rows - 1) * (position + 1) >chatEmojis.size()? chatEmojis.size() : (columns* rows - 1)
								* (position + 1)));
		
		/**
		 * 末尾添加删除图标 添加了一个实体
		 * */
//		subList.add("massage_chat_btn_delete.png");
		
		ChatEmoji delEmoji=new ChatEmoji();
		delEmoji.setCharacter("[删除]");
		delEmoji.setFaceName("massage_chat_btn_delete");
		delEmoji.setId(StringToDrawbleId.getDrawableId(this, "massage_chat_btn_delete"));
		subList.add(delEmoji);
		/**
		 * 将添加过删除表情的表情列表存储到新的list里
		 */
		for(ChatEmoji emoji:subList){
			subAllList.add(emoji);
		}
		
		FaceGVAdapter mGvAdapter = new FaceGVAdapter(subList, this);
		gridview.setAdapter(mGvAdapter);
		gridview.setNumColumns(columns);
		// 单击表情执行的操作
		gridview.setOnItemClickListener(this);

		
		return gridview;
	}
	
	/**
	 * 表情页改变时，dots效果也要跟着改变
	 * */
	class PageChange implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
//			log.e("lucifer", "arg0="+arg0+" ,arg1="+arg1+",arg2="+arg2);
		}
		@Override
		public void onPageSelected(int arg0) {
			current = arg0;
			log.e("lucifer", "position=="+arg0);
			for (int i = 0; i < mDotsLayout.getChildCount(); i++) {
				mDotsLayout.getChildAt(i).setSelected(false);
			}
			mDotsLayout.getChildAt(arg0).setSelected(true);
		}

	}
	
	/**
	 * 根据表情数量以及GridView设置的行数和列数计算Pager数量
	 * @return
	 */
	private int getPagerCount() {
		//TODO
		int count = chatEmojis.size();
		
		return count % (columns * rows - 1) == 0 ? count / (columns * rows - 1)
				: count / (columns * rows - 1) + 1;
	}
	private ImageView dotsItem(int position) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.dot_image, null);
		ImageView iv = (ImageView) layout.findViewById(R.id.face_dot);
		iv.setId(position);
		return iv;
	}
	/**
	 * 点击表情
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		//拿到第几页的第几个表情
		ChatEmoji emoji = subAllList.get(position+(current*columns*rows));
		log.e("lucifer", "current=="+current);
//		Toast.makeText(this, ""+emoji.getCharacter(), Toast.LENGTH_SHORT).show();
		log.e("lucifer", "position="+position+"  id="+id);
		emojiKey=emoji.getCharacter();
		if(emoji.getCharacter().equals("[删除]")){
			EditViewDelect();
		}else{
			EditViewInsert(emoji);			
		}
		
		
	}
	/**
	 * 向输入框插入表情
	 */
	private void EditViewInsert(ChatEmoji emoji) {
//		// TODO Auto-generated method stub
//		mEditText.setText(mEditText.getText()+emojiKey);
//		
//		//光标设置到文本末尾
//		CharSequence text = mEditText.getText();
//		//Debug.asserts(text instanceof Spannable);
//		if (text instanceof Spannable) {
//		    Spannable spanText = (Spannable)text;
//		    Selection.setSelection(spanText, text.length());
//		 }
		if (!TextUtils.isEmpty(emoji.getCharacter())) {
			if (mListener != null)
				mListener.onCorpusSelected(emoji);
			SpannableString spannableString = 
					addFace(this, emoji.getId(), emoji.getCharacter());
			mEditText.append(spannableString);
		}
		
	}
	/**
	 * 删除输入框的内容，如果是表情，则删除整个表情字符串
	 */
	private void EditViewDelect() {
		// TODO Auto-generated method stub
		
		int selection = mEditText.getSelectionStart();
		String text = mEditText.getText().toString();
		if (selection > 0) {
			String text2 = text.substring(selection - 1);
			if ("]".equals(text2)) {
				int start = text.lastIndexOf("[");
				int end = selection;
				mEditText.getText().delete(start, end);
				return;
			}
			mEditText.getText().delete(selection - 1, selection);
		}
		
	}
	
	/**
	 * 使用handle 更新ui
	 */
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case 1:
				//刷新数据时 要先清空数据 再添加。不然 不刷新  亲测。。。
				chatmsgsCacheList.clear();

				chatmsgsCacheList.addAll(chatmsgsDao.getChatmsgsList("1"));	
				
				System.err.println(chatmsgsCacheList.get(chatmsgsCacheList.size()-1).getContent()+"  id=="+chatmsgsCacheList.get(chatmsgsCacheList.size()-1).getMessageCacheId());	
				
				mChatmsgsAdapter.notifyDataSetChanged();
				
					
				//ListView数据更新后，自动滚动到底部
				mChatmsgsListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
				
				refreshComplete();
				break;
			case 2:
				//刷新数据时 要先清空数据 再添加。不然 不刷新  亲测。。。
				chatmsgsCacheList.clear();

				chatmsgsCacheList.addAll(chatmsgsDao.getChatmsgsList("1"));	
				
				System.err.println(chatmsgsCacheList.get(chatmsgsCacheList.size()-1).getContent()+"  id=="+chatmsgsCacheList.get(chatmsgsCacheList.size()-1).getMessageCacheId());	
				
				mChatmsgsAdapter.notifyDataSetChanged();
				
				refreshComplete();
				
				break;
			}
		}
	
	};
	
	private void refreshComplete(){
		mChatmsgsListView.postDelayed(new Runnable() {
	
	            @Override
	            public void run() {
	            	//使用第三方的时候用
//	            	mChatmsgsListView.refreshComplete();
	            	
	            }
	    }, 500);
	}
	
	/**
	 * 得到一个SpanableString对象，通过传入的字符串,并进行正则判断
	 * 
	 * @param context
	 * @param str
	 * @return
	 */
	public static  SpannableString getExpressionString(Context context, String str) {
		SpannableString spannableString = new SpannableString(str);
		// 正则表达式比配字符串里是否含有表情，如： 我好[开心]啊
		String zhengze = "\\[[^\\]]+\\]";
		// 通过传入的正则表达式来生成一个pattern
		Pattern sinaPatten = Pattern.compile(zhengze, Pattern.CASE_INSENSITIVE);
		try {
			dealExpression(context, spannableString, sinaPatten, 0);
		} catch (Exception e) {
			Log.e("dealExpression", e.getMessage());
		}
		return spannableString;
	}
	/**
	 * 对spanableString进行正则判断，如果符合要求，则以表情图片代替
	 * 
	 * @param context
	 * @param spannableString
	 * @param patten
	 * @param start
	 * @throws Exception
	 */
	private static void dealExpression(Context context,
			SpannableString spannableString, Pattern patten, int start)
			throws Exception {
		Matcher matcher = patten.matcher(spannableString);
		while (matcher.find()) {
			String key = matcher.group();
			// 返回第一个字符的索引的文本匹配整个正则表达式,ture 则继续递归
			if (matcher.start() < start) { 
				continue;
			}
			log.e("lucifer",""+key);
			//TODO 测试一个表情
			String value = null;
			for(ChatEmoji chatEmoji:chatEmojis){
				if(chatEmoji.getCharacter().equals(key)){
					value=chatEmoji.getFaceName();
					break;
				}
				
			}
			log.e("lucifer111",value);
//			String value="expression_1";
			if (TextUtils.isEmpty(value)) { 
				continue;
			}
			int resId = context.getResources().getIdentifier(value, "drawable",
					context.getPackageName());
			// 通过上面匹配得到的字符串来生成图片资源id
			// Field field=R.drawable.class.getDeclaredField(value);
			// int resId=Integer.parseInt(field.get(null).toString());
			if (resId != 0) {
				Bitmap bitmap = BitmapFactory.decodeResource(
						context.getResources(), resId);
				bitmap = Bitmap.createScaledBitmap(bitmap, emojiChatHight, emojiChatWeight, true);
				// 通过图片资源id来得到bitmap，用一个ImageSpan来包装
				ImageSpan imageSpan = new ImageSpan(bitmap);
				// 计算该图片名字的长度，也就是要替换的字符串的长度
				int end = matcher.start() + key.length();
				// 将该图片替换字符串中规定的位置中
				spannableString.setSpan(imageSpan, matcher.start(), end,
						Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
				if (end < spannableString.length()) {
					// 如果整个字符串还未验证完，则继续。。
					dealExpression(context, spannableString, patten, end);
				}
				break;
			}
		}
	}
	/**
	 * 添加表情
	 * 
	 * @param context
	 * @param imgId
	 * @param spannableString
	 * @return
	 */
	public SpannableString addFace(Context context, int imgId,
			String spannableString) {
		if (TextUtils.isEmpty(spannableString)) {
			return null;
		}
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				imgId);
		bitmap = Bitmap.createScaledBitmap(bitmap, emojiHigh, emojiWeight, true);
		ImageSpan imageSpan = new ImageSpan(context, bitmap);
		SpannableString spannable = new SpannableString(spannableString);
		spannable.setSpan(imageSpan, 0, spannableString.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannable;
	}
	

	/**
	 * 表情的点击监听
	 * @author Administrator
	 *
	 */
	public interface OnCorpusSelectedListener {

		void onCorpusSelected(ChatEmoji emoji);

		void onCorpusDeleted();
	}

}
