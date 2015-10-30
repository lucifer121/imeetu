package com.meetu.fragment;

import com.meetu.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChatFragment extends Fragment implements OnClickListener{
	private RelativeLayout backLayout;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.fragment_chat, container, false);
	    backLayout=(RelativeLayout) view.findViewById(R.id.back_miliao_chat_rl);
	    return view;
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
		case R.id.back_miliao_chat_rl:
//			Toast.makeText(getActivity(), "123", Toast.LENGTH_SHORT).show();
			getActivity().finish();
			break;

		default:
			break;
		}
	}
	

}
