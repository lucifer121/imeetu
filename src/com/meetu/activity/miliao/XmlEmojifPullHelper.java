package com.meetu.activity.miliao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.avos.avoscloud.LogUtil.log;
import com.meetu.entity.ChatEmoji;

public class XmlEmojifPullHelper implements EmojiParser{
	/**
	 * 读取xml 存储数据 返回list
	 */

	@Override
	public List<ChatEmoji> parse(InputStream is) throws Exception {
		// TODO Auto-generated method stub
		int i=0;
		List<ChatEmoji> chatEmojis=null; 
		ChatEmoji emoji=null;
		 XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例  
	        parser.setInput(is, "UTF-8");               //设置输入流 并指明编码方式  
	  
	        int eventType = parser.getEventType();  
	        chatEmojis = new ArrayList<ChatEmoji>(); 
	        
	        try {
				while (eventType != XmlPullParser.END_DOCUMENT) {  
				    switch (eventType) {  
				    case XmlPullParser.START_DOCUMENT:  
				    	 
				        break; 
				    case XmlPullParser.START_TAG: 
				    	log.e(parser.getName());
				        if (parser.getName().equals("emoji")) {  
				        	emoji = new ChatEmoji();  
				        }
				    else if (parser.getName().equals("key")) {  
				            eventType = parser.next(); 
				            emoji.setCharacter(parser.getText());
				            
				            log.e("lucifer","key="+ parser.getText());

				        } else if (parser.getName().equals("value")) {  
				            eventType = parser.next();  

				            emoji.setFaceName(""+parser.getText());
				            
				            log.e("lucifer","key="+ parser.getText());
				        }  
				        break;  
				    case XmlPullParser.END_TAG:  
				        if (parser.getName().equals("emoji")) {  
				        	chatEmojis.add(emoji);	
				        	log.e("emoji===","emoji==="+emoji.getCharacter());
				        	emoji = null; 
				        	log.e("i"+i++);
				        }  
				        break;  
				    }  
				    //TODO 这行报异常 错误
				    eventType = parser.next();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}  
	        log.e("lucifer========","chatEmojis="+chatEmojis.size());
	        return chatEmojis;  
	}
	
	

}
