package com.meetu.fragment;



import com.meetu.R;
import com.meetu.activity.ChooseSchoolActivity;
import com.meetu.activity.mine.ChangeBirthdayActivity;
import com.meetu.activity.mine.ChangeCityActivity;
import com.meetu.activity.mine.ChangeMajorActivity;
import com.meetu.activity.mine.ChangeNameActivity;
import com.meetu.activity.mine.ChangeSchoolActivity;
import com.meetu.activity.mine.ChangexingzuoActivity;

import android.R.raw;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MinePersonalInformation extends Fragment implements OnClickListener{
	private View view;
	private ImageView ivman_selector,ivwoman_selector;
	private TextView username,usersex,userbirthday,userConstellation;
	private TextView userschool,usergrade,usermajor,userhometown;
//	private EditText username;
	private LinearLayout nameLayout,sexlLayout,birthdayLayout,constellationLayout;
	private LinearLayout schoolLayout,gradeLayout,majorLayout,hometownLayout;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
//	@ViewInject(id=R.id.minesetting_birthday_tv,click="btnClick") TextView textView;
//	public void btnClick(View v){
//	       textView.setText("text set form button");
//	    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null){
			view=inflater.inflate(R.layout.fragment_mine_personal_information, null);
			initView();
		}
		ViewGroup parent=(ViewGroup)view.getParent();
		if(parent!=null){
			parent.removeView(view);
		}
		return view;
	}
	private void initView(){
		username=(TextView) view.findViewById(R.id.minesetting_username_tv);
		usersex=(TextView) view.findViewById(R.id.minesetting_sex_tv);
		userbirthday=(TextView) view.findViewById(R.id.minesetting_birthday_tv);
		userConstellation=(TextView) view.findViewById(R.id.minesetting_Constellation_tv);
		userschool=(TextView) view.findViewById(R.id.minesetting_school_tv);

		usermajor=(TextView) view.findViewById(R.id.minesetting_major_tv);
		userhometown=(TextView) view.findViewById(R.id.minesetting_hometown_tv);
		username.setOnClickListener(this);
		usersex.setOnClickListener(this);
		userbirthday.setOnClickListener(this);
		userConstellation.setOnClickListener(this);
		userschool.setOnClickListener(this);

		usermajor.setOnClickListener(this);
		userhometown.setOnClickListener(this);
		nameLayout=(LinearLayout) view.findViewById(R.id.minesetting_username_ll);
		birthdayLayout=(LinearLayout) view.findViewById(R.id.minesetting_birthday_ll);
		constellationLayout=(LinearLayout) view.findViewById(R.id.minesetting_Constellation_ll);
		schoolLayout=(LinearLayout) view.findViewById(R.id.minesetting_school_ll);

		sexlLayout=(LinearLayout) view.findViewById(R.id.minesetting_sex_ll);
		majorLayout=(LinearLayout) view.findViewById(R.id.minesetting_major_ll);
		hometownLayout=(LinearLayout) view.findViewById(R.id.minesetting_hometown_ll);
		nameLayout.setOnClickListener(this);
		birthdayLayout.setOnClickListener(this);
		constellationLayout.setOnClickListener(this);
		schoolLayout.setOnClickListener(this);
		sexlLayout.setOnClickListener(this);
		majorLayout.setOnClickListener(this);
		
		hometownLayout.setOnClickListener(this);
		
	}

	@Override
	public void setArguments(Bundle args) {
		// TODO Auto-generated method stub
		
		super.setArguments(args);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.minesetting_username_ll :
//				Toast.makeText(getActivity(), username.getText(), Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(getActivity(),ChangeNameActivity.class);
				intent.putExtra("name",username.getText().toString());
				startActivityForResult(intent,0);
				break;
			case R.id.minesetting_birthday_ll:
	//			Toast.makeText(getActivity(), userbirthday.getText(), Toast.LENGTH_SHORT).show();
//				super.getActivity().showDialog(1);
//				new DatePickerDialog(super.getActivity(), new OnDateSetListener() {
//
//					@Override
//					public void onDateSet(DatePicker arg0, int year, int month, int day) {
//						// TODO Auto-generated method stub
//						userbirthday.setText(year + "-" + (month + 1) + "-" + day);
//					}
//				}, 2015, 7, 13).show();
				Intent intent5=new Intent(getActivity(),ChangeBirthdayActivity.class);
				intent5.putExtra("birthday",userbirthday.getText().toString());
				startActivityForResult(intent5,5);
				break;
//		//性别的修改
//			case R.id.minesetting_sex_tv:
//				initPopupWindow();
//				popupWindow.showAsDropDown(v, 72, 400);	
//				break;
//			case R.id.iv_man_sexselector:
//				
//				//	ivman_selector.setImageResource(R.drawable.complete_gender_ms_720);
//					popupWindow.dismiss();
//					usersex.setText("男生");
//					break;
//			case R.id.iv_woman_sexselector:
//				//	ivwoman_selector.setImageResource(R.drawable.complete_gender_fs_720);
//					popupWindow.dismiss();
//					usersex.setText("女生");
//					break;
			case R.id.minesetting_school_ll:
				Intent intent1=new Intent(getActivity(),ChangeSchoolActivity.class);
				intent1.putExtra("school",userschool.getText());
				startActivityForResult(intent1, 1);	
				break;
		//星座的修改	
			case R.id.minesetting_Constellation_ll:
				Intent intent2=new Intent(getActivity(),ChangexingzuoActivity.class);
				intent2.putExtra("Constellation", userConstellation.getText());
				startActivityForResult(intent2, 2);
				break;
		//专业的修改
			case R.id.minesetting_major_ll:
				Intent intent3=new Intent(getActivity(),ChangeMajorActivity.class);
				intent3.putExtra("major", usermajor.getText());
				startActivityForResult(intent3, 3);
				break;
		//家乡的修改
			case R.id.minesetting_hometown_ll:
				Intent intent4=new Intent(getActivity(),ChangeCityActivity.class);
				intent4.putExtra("hometown", userhometown.getText());
				startActivityForResult(intent4,4);
				break;
			default :
				break;
		}
		
	}
	
	
//	private PopupWindow popupWindow;
//	private void initPopupWindow() {
//		if (popupWindow == null) {
//			View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_sex_selector,
//					null);		
//			popupWindow = new PopupWindow(view,
//					ViewGroup.LayoutParams.MATCH_PARENT,
//					ViewGroup.LayoutParams.MATCH_PARENT);
//			// 设置外观
//			popupWindow.setFocusable(true);
//			popupWindow.setOutsideTouchable(true);
//			ColorDrawable colorDrawable = new ColorDrawable();
//			popupWindow.setBackgroundDrawable(colorDrawable);
//		//	tvTitle=(TextView)view.findViewById(R.id.tvcolectList);
//			ivman_selector=(ImageView)view.findViewById(R.id.iv_man_sexselector);
//			ivwoman_selector=(ImageView)view.findViewById(R.id.iv_woman_sexselector);
//			ivman_selector.setOnClickListener(this);
//			ivwoman_selector.setOnClickListener(this);
//		}
//		
//	}
	
	@Override
	public void onActivityResult(int requestCode,int resultCode,Intent data){
	  
	  switch(resultCode){
	  case 0:
	   /*取得来自SecondActivity页面的数据，并显示到画面*/	   
	   /*获取Bundle中的数据，注意类型和key*/
	         String name = data.getExtras().getString("name");
	              
	        // Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
	         username.setText(name);
	         break;
	  case 2:
		  String xingzuo=data.getExtras().getString("xingzuo");
	//	  Toast.makeText(getActivity(), xingzuo, Toast.LENGTH_SHORT).show();
		  userConstellation.setText(xingzuo);
		  break;
	  case 3:
		  String major=data.getExtras().getString("major");
		  usermajor.setText(major);
		  break;
	  case 4:
		  String hometown=data.getExtras().getString("city");
		  userhometown.setText(hometown);
		  break;
	  case 5:
		  String birthday=data.getExtras().getString("birthday");
		  userbirthday.setText(birthday);
		  break;
	  }
	 }


}
