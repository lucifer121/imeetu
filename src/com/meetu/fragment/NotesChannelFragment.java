package com.meetu.fragment;

import java.util.ArrayList;
import java.util.List;

import com.avos.avoscloud.LogUtil.log;
import com.meetu.R;
import com.meetu.activity.messages.InputDialog;
import com.meetu.activity.messages.InputDialog.SendMessageCallback;
import com.meetu.activity.messages.NotesActivity;
import com.meetu.activity.miliao.FaceGVAdapter;
import com.meetu.activity.miliao.FaceVPAdapter;
import com.meetu.activity.miliao.ChatGroupActivity.OnCorpusSelectedListener;

import com.meetu.entity.ChatEmoji;
import com.meetu.sqlite.EmojisDao;
import com.meetu.tools.DensityUtil;
import com.meetu.tools.DisplayUtils;
import com.meetu.tools.StringToDrawbleId;
import com.tencent.open.cgireport.reportItem;


import android.R.integer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

public class NotesChannelFragment extends Fragment implements OnClickListener,OnItemClickListener{
	private RelativeLayout topLayout,bottomLayout,allLayout;
	
	private  View view;
	private int windowWidth;
	//表情键盘相关
	private RelativeLayout sendLayout,editviewLayout;
	private LinearLayout sendLinearLayout;
	int mVisibleHeight;
	
	public static EditText mEditText;
	private RelativeLayout emojiLayout;
	private LinearLayout faceLayout;
	private ImageView emojiAll;
	private ScrollView mScrollView;
	private  boolean mIsKeyboardShow=false;//记录系统软键盘是否弹出
	private  boolean isShowEmoji=false;//记录表情键盘是否弹出
	private boolean isShowBottom=true;//记录公共底部是否显示    
	
	private boolean isWindow=true;//记录屏幕点击的时候的状态 true 表示会弹出键盘 false 落下键盘
	private int  windowFocusX,windowFocusY;
	private int noteHight;//纸条的绝对高度
	private int emojiHight;//表情键盘的高度
	private int ruanHight;//软键盘的高度
	
    //添加表情相关
	private EmojisDao emojisDao;
	private static List<ChatEmoji> chatEmojis; 
	private ImageView image_face;//表情图标
	// 7列3行
	private int columns=7;//表情的列数
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
	
	/**
	 * 发送小字条相关
	 */
	private List<String> notesMessageViewList;//存储每个小字条消息
	private TextView content;
	private ImageView photoHead;
	private  RelativeLayout contentLayout;
	private	int layoutW = 0,layoutH = 0;//小纸条的输入框的宽高
	private int viewX,viewY;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if(view==null){
			view=inflater.inflate(R.layout.fragment_notes_channel, null);
			//从本地数据库 获取表情数据
			emojisDao=new EmojisDao(getActivity());
			chatEmojis=emojisDao.getChatEmojisList();
			emojiHigh=DensityUtil.dip2px(getActivity(), 24);
			emojiWeight=DensityUtil.dip2px(getActivity(), 24);
			
			noteHight=DisplayUtils.getWindowHeight(getActivity())-DensityUtil.dip2px(getActivity(), 190);
			
			emojiHight=DensityUtil.dip2px(getActivity(), 275);
			ruanHight=DensityUtil.dip2px(getActivity(), 350);
			
			getHight();
			
			intitView();
			//加载表情
			InitViewPager();
			
		}
		ViewGroup parent=(ViewGroup) view.getParent();
		if(parent!=null){
			parent.removeView(view);
		}
		return view;
	}
	/**
	 * 获得弹出键盘的高度
	 */
	private void getHight() {
		// TODO Auto-generated method stub
		view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
		        @Override
		        public void onGlobalLayout() {
		            getKeyboardHeight();
		        }
				private void getKeyboardHeight() {
					// TODO Auto-generated method stub
					Rect r = new Rect();
				    view.getWindowVisibleDisplayFrame(r);

				    int visibleHeight = r.height();

				    if (mVisibleHeight == 0) {
				        mVisibleHeight = visibleHeight;
				        return;
				    }

				    if (mVisibleHeight == visibleHeight) {
				        return;
				    }
				    mVisibleHeight = visibleHeight;

				    // Magic is here
//				    if (/* compare the visiable height  to others */) {
//				        mIsKeyboardShow = true;
//				    } else {
//				        mIsKeyboardShow = false;
//				    }
				}
		    });
		
	}
	private void intitView() {
		// TODO Auto-generated method stub
		mEditText=(EditText) view.findViewById(R.id.input_notes_fragment_et);
		//输入框文字的变化监听
		mEditText.addTextChangedListener(mTextWatcher);
			
		//动态设置高度
		topLayout=(RelativeLayout) view.findViewById(R.id.top_fragment_notes_channel_rl);
		bottomLayout=(RelativeLayout) view.findViewById(R.id.bottom_fragment_notes_channel_rl);
		windowWidth=DisplayUtils.getWindowWidth(getActivity());
		
		

		int topH=(int) (windowWidth/1.2);
		int bottomH=(int) (windowWidth/2.23);		
		RelativeLayout.LayoutParams topparams=(LayoutParams) topLayout.getLayoutParams();	
		topparams.height=topH;
		topLayout.setLayoutParams(topparams);
		RelativeLayout.LayoutParams bottomparams=(LayoutParams) bottomLayout.getLayoutParams();	
		bottomparams.height=bottomH;
		bottomLayout.setLayoutParams(bottomparams);
		log.e("lucifer","topH=="+topH+" bottomH=="+bottomH);	
		
		mScrollView=(ScrollView) view.findViewById(R.id.scrollview_notes_channel);
		FrameLayout.LayoutParams scrollParams=(android.widget.FrameLayout.LayoutParams) mScrollView.getLayoutParams();
		scrollParams.height=topH+bottomH;
		mScrollView.setLayoutParams(scrollParams);
		
		allLayout=(RelativeLayout) view.findViewById(R.id.all_fragment_notes_channes_rl);
		allLayout.setOnClickListener(this);
		
		//获取点击屏幕的坐标
		allLayout.setOnTouchListener(new TouchListenerImp());
		sendLayout=(RelativeLayout) view.findViewById(R.id.bottom_emoji_send_rl);
		
		editviewLayout=(RelativeLayout) view.findViewById(R.id.bottom_emoji_send_rl);
		sendLinearLayout=(LinearLayout) view.findViewById(R.id.bottom_notes_send_ll);
		emojiLayout=(RelativeLayout) view.findViewById(R.id.emoji_notes_fragment_rl);
		emojiLayout.setOnClickListener(this);
		faceLayout=(LinearLayout) view.findViewById(R.id.chat_face_container);
		emojiAll=(ImageView) view.findViewById(R.id.emoji_notes_fragment_img);
		
		//表情相关
		//表情布局
		chat_face_container=(LinearLayout)view. findViewById(R.id.chat_face_container);
		mViewPager = (ViewPager) view.findViewById(R.id.face_viewpager);
		mViewPager.setOnPageChangeListener(new PageChange());
		//表情下小圆点
		mDotsLayout = (LinearLayout)view. findViewById(R.id.face_dots_container);
		
		
		
		
	}
	/**
	 * 获取当前 屏幕点击的位置
	 * @author Administrator
	 *
	 */
	private class TouchListenerImp implements OnTouchListener{

		public boolean onTouch(View v, MotionEvent event) {
	//	MainActivity.this.info.setText("x="+event.getX()+"y="+event.getY());
			log.e("lucifer", "x=="+event.getX() +" y=="+event.getY());
			windowFocusX=(int)event.getX();
			windowFocusY=(int)event.getY();
		return false;
		}
		}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.all_fragment_notes_channes_rl:
			//TODO
			log.e("lucifer","触发页面");
			
			getHight();
//			showDialog();
//			LinearLayout.LayoutParams params= (android.widget.LinearLayout.LayoutParams) sendLayout.getLayoutParams();
//			
//			params.bottomMargin=mVisibleHeight;
//			
//			sendLayout.setLayoutParams(params);
			
//			sendLinearLayout.setVisibility(View.VISIBLE);
			
//			Toast.makeText(getActivity(), ""+mVisibleHeight, Toast.LENGTH_SHORT).show();
//			SendMessageCallback mCallback = null;
//			new InputDialog(getActivity(), mCallback).show();
			
			//第一次点击屏幕
			if(isWindow==true){
				sendLinearLayout.setVisibility(View.VISIBLE);
				if(isShowEmoji==true){
					NotesActivity.visible();
					emojiAll.setBackgroundResource(R.drawable.emotionsemojihl);
					faceLayout.setVisibility(View.GONE);
					isShowEmoji=false;
					
				}
				mEditText.requestFocus();
				InputMethodManager inputManager = (InputMethodManager)mEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);    
				inputManager.showSoftInput(mEditText, -20);
				isWindow=false;
				mIsKeyboardShow=true;
				//allLayout.setY(-100);
				
				if((noteHight-windowFocusY)+DensityUtil.dip2px(getActivity(), 77)>=ruanHight){
					
				}else{
					float moveHight=(noteHight-windowFocusY)+DensityUtil.dip2px(getActivity(), 77)-ruanHight;
					log.e("zcq", "moveHight=="+moveHight);
//					allLayout.setY(moveHight);
					mScrollView.setY(moveHight);
				}
				
				
				NotesActivity.dismiss();
//				notesMessageViewList.add(getsendMessageLayout());
				
				allLayout.addView(getsendMessageLayout());
				
			}else{
				sendLinearLayout.setVisibility(View.GONE);
				faceLayout.setVisibility(View.GONE);
				isShowEmoji=false;
				InputMethodManager inputManager = (InputMethodManager)mEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE); 
				inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0); 
				isWindow=true;
				mIsKeyboardShow=false;
				
				allLayout.setY(0);
				mScrollView.setY(0);
				NotesActivity.visible();
//				allLayout.removeViewAt(1);
//				dismissAll();
				removeView(100);
				
			}
				
			
			break;
			
		case R.id.emoji_notes_fragment_rl:
			
			//biaoqing
			if(isShowEmoji==false){
				NotesActivity.dismiss();
				faceLayout.setVisibility(View.VISIBLE);
				emojiAll.setBackgroundResource(R.drawable.massage_letters_show_reply_img_keybroad);
				isShowEmoji=true;
				//落下软键盘
				InputMethodManager inputManager = (InputMethodManager)mEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE); 
				inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0); 
				mIsKeyboardShow=false;
				
				if((noteHight-windowFocusY)+DensityUtil.dip2px(getActivity(), 77)<emojiHight){
					float moveHight=(noteHight-windowFocusY)+DensityUtil.dip2px(getActivity(), 77)-emojiHight;
					log.e("zcq", "moveHightemoji=="+moveHight);
//					allLayout.setY(moveHight);
					mScrollView.setY(moveHight);
				}
				
//				allLayout.addView(getsendMessageLayout());
				
//				allLayout.addView(getsendMessageLayout());
					

			}else{
				//第二次点击表情
				
				NotesActivity.visible();
				emojiAll.setBackgroundResource(R.drawable.emotionsemojihl);
				faceLayout.setVisibility(View.GONE);
				isShowEmoji=false;
				sendLinearLayout.setVisibility(View.GONE);
				allLayout.setY(0);
				mScrollView.setY(0);
				
//				allLayout.removeViewAt(100);
				
			}
			
				
			break;
		default:
			break;
		}
		
	}
	public static void dismissAll(){
//		sendLinearLayout.setVisibility(View.GONE);
//		faceLayout.setVisibility(View.GONE);
//		isShowEmoji=false;
//		InputMethodManager inputManager = (InputMethodManager)mEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE); 
//		inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0); 
//		isWindow=true;
//		mIsKeyboardShow=false;
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
		log.e("zcq", "count=="+count);
		return count % (columns * rows - 1) == 0 ? count / (columns * rows - 1)
				: count / (columns * rows - 1) + 1;
	}
	private ImageView dotsItem(int position) {
		//TODO
		LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.dot_image, null);
		ImageView iv = (ImageView) layout.findViewById(R.id.face_dot);
		iv.setId(position);
		return iv;
	}
	/*
	 * 初始表情 *
	 */
	private void InitViewPager() {
		log.e("zcq", "getPagerCount()=="+getPagerCount());
		// 获取页数 TODO
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
		//TODO
		LayoutInflater inflater = (LayoutInflater)getActivity(). getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		delEmoji.setId(StringToDrawbleId.getDrawableId(getActivity(), "massage_chat_btn_delete"));
		subList.add(delEmoji);
		/**
		 * 将添加过删除表情的表情列表存储到新的list里
		 */
		for(ChatEmoji emoji:subList){
			subAllList.add(emoji);
		}
		
		FaceGVAdapter mGvAdapter = new FaceGVAdapter(subList, getActivity());
		gridview.setAdapter(mGvAdapter);
		gridview.setNumColumns(columns);
		// 单击表情执行的操作
		gridview.setOnItemClickListener(this);

		
		return gridview;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
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
					addFace(getActivity(), emoji.getId(), emoji.getCharacter());
			mEditText.append(spannableString);
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
	 * 获取添加的视图 输入框
	 */
	private View  getsendMessageLayout(){
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		
		LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.item_note_channels_messages, null);
		contentLayout=(RelativeLayout) view.findViewById(R.id.input_note_channels_rl);
		content=(TextView) view.findViewById(R.id.content_note_channels_tv);
		
		content.setText(mEditText.getText().toString());
		photoHead=(ImageView) view.findViewById(R.id.photoHead_notes_channel_fragment_img);
		
		viewX=windowFocusX-(DensityUtil.dip2px(getActivity(), 34)+1);
		viewY=windowFocusY-(DensityUtil.dip2px(getActivity(), 54));
		//在屏幕的最左边
		if(windowFocusX<((DensityUtil.dip2px(getActivity(), 34)+1))){
			//x=((DensityUtil.dip2px(getActivity(), 34)+1));
			viewX=windowFocusX;
		}
		//在你屏幕的最右边
		if((windowWidth-windowFocusX)<((DensityUtil.dip2px(getActivity(), 34)+1))){
			viewX=windowFocusX-((DensityUtil.dip2px(getActivity(), 69)));
		}
		//在屏幕的最上边
		if(windowFocusY<(DensityUtil.dip2px(getActivity(), 54))){
			//y=(DensityUtil.dip2px(getActivity(), 54));
			viewY=windowFocusY;
		}
		
		log.e("lucifer"+"x=="+viewX+"y=="+viewY);
		
		view.setX(viewX);
		view.setY(viewY);
		
		view.setId(100);
		
//		view.setTag(notesMessageViewList.size());
		view.setLayoutParams(params);
		return view;
	}
	
	/**
	 * 当输入框变化的时候的处理
	 */
	TextWatcher mTextWatcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			
			String contentString=mEditText.getText().toString();
			log.e("lucifer", "contentString"+contentString+" arg0=="+arg0);
				
			
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
			
		}
		
		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			//Toast.makeText(getActivity(), ""+mEditText.getText(), Toast.LENGTH_SHORT).show();
			

/*			
				//一个字体的高度和宽度
				
				int textHight=DensityUtil.dip2px(getActivity(), 15);
				int textWidth=DensityUtil.dip2px(getActivity(), 13);
				
				
				if(arg0.length()<=8){
					layoutW=arg0.length()*textWidth+DensityUtil.dip2px(getActivity(), 20);
					layoutH=textHight+DensityUtil.dip2px(getActivity(), 24);
				}else{
					layoutW=8*textWidth+DensityUtil.dip2px(getActivity(), 20);
					if(arg0.length()%8==0){
						layoutH=textHight*(arg0.length()/8)+DensityUtil.dip2px(getActivity(), 24);
						}else{
							layoutH=textHight*((arg0.length()/8)+1)+DensityUtil.dip2px(getActivity(), 24);
						}
					
				}
				
				RelativeLayout.LayoutParams params=(LayoutParams) contentLayout.getLayoutParams();
				if(layoutW<=DensityUtil.dip2px(getActivity(), 69)){
					layoutW=DensityUtil.dip2px(getActivity(), 69);
				}
				params.width=layoutW;
				params.height=layoutH;
				contentLayout.setLayoutParams(params);*/

			
			
			if(sendLayout.getVisibility()==View.VISIBLE){
				
				content.setText(""+arg0);
				
			//	getTextWH(""+arg0);
			}
			
			log.e("lucifer","content.getWidth()==="+content.getWidth()+"content.gethight()==="+content.getHeight());
			
			//在你屏幕的最右边
			if((windowWidth-windowFocusX)<((DensityUtil.dip2px(getActivity(), 34)+1))){
				//随着字体 textview 中的 字体宽度 让view内容不要出右屏幕
				if((content.getWidth()+DensityUtil.dip2px(getActivity(), 20))>=DensityUtil.dip2px(getActivity(), 69)){
					viewX=windowWidth-(content.getWidth()+DensityUtil.dip2px(getActivity(), 30));
				}
			}
			//随着字体 textview 中的 字体宽度 让view内容不要出下屏幕
			log.e("lucifer", "字条距离下表的距离"+(DensityUtil.dip2px(getActivity(), 54)+noteHight-windowFocusY)+"文本view的高度"+(content.getHeight()+DensityUtil.dip2px(getActivity(), 44+33)));
			log.e("lucifer", "content.getHeight()==="+content.getHeight());
			if((content.getHeight()+DensityUtil.dip2px(getActivity(), 44+33))>=(DensityUtil.dip2px(getActivity(), 54)+noteHight-windowFocusY)){
				viewY=DensityUtil.dip2px(getActivity(), 54)-content.getHeight()+DensityUtil.dip2px(getActivity(), 44);
				
			}
			
			
			
			View myView=view.findViewById(100);
			myView.setX(viewX);
			myView.setY(viewY);
			log.e("lucifer", "xxx==="+view.getX()+"yyy==="+view.getY());
		}
	};
//	
//	private void getTextWH( String mString){
//		Paint pFont = new Paint();
//		Rect rect = new Rect();
//		pFont.getTextBounds(mString, 0, 1, rect);
//		Log.e("lucifer", "height:"+rect.height()+"width:"+rect.width());
//	}
		
	/**
	 * 删除添加的view
	 * id   是view 的id
	 */
	private void removeView( int id){
		View hiddenView = allLayout.findViewById(100) ;  //在hidden_view.xml中hidden_layout是root layout
		if ( null != hiddenView ) {

			ViewGroup parent=(ViewGroup) hiddenView.getParent();
			parent.removeView(hiddenView);
		}
	}
	
	/**
	 * fragment 中 隐藏 输入框布局
	 */
	public void isShowEditLayout(){
		
		sendLinearLayout.setVisibility(View.GONE);
		
		removeView(100);
		mScrollView.setY(0);
		allLayout.setY(0);
		 mIsKeyboardShow=false;//记录系统软键盘是否弹出
		isShowEmoji=false;//记录表情键盘是否弹出
		isShowBottom=true;//记录公共底部是否显示    
		
		 isWindow=true;//记录屏幕点击的时候的状态 true 表示会弹出键盘 false 落下键盘
	}
	
	
	
	
	

}
