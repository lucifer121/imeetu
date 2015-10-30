package com.meetu.activity.messages;

import com.meetu.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class InputDialog extends Dialog {

	    private SendMessageCallback mCallback;
	    private TextView number;



	    /**
	     * 自定义接口，用于回调按钮点击事件到Activity
	     */
	    public interface SendMessageCallback {
	        public void textChange(String message);

	        public void sendMessage(String message);
	    }

	    public InputDialog(final Context context, final SendMessageCallback mCallback) {
	        super(context);                             
	        
	        
	        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
	        setContentView(R.layout.item_note_dialog);
	        this.mCallback = mCallback;
	        setCanceledOnTouchOutside(true);
	        number = (TextView) findViewById(R.id.input_notes_et);

	        Window window = getWindow();
	        WindowManager.LayoutParams params = window.getAttributes();
	        params.width = WindowManager.LayoutParams.MATCH_PARENT;
	        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
	        params.gravity = Gravity.BOTTOM;
	        window.setAttributes(params);
	        //SOFT_INPUT_ADJUST_PAN

	    }


	}

	


