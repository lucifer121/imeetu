package com.meetu.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;

import com.avos.avoscloud.LogUtil.log;
import com.meetu.activity.miliao.EmojiParser;
import com.meetu.activity.miliao.XmlEmojifPullHelper;
import com.meetu.entity.ChatEmoji;
import com.meetu.tools.DensityUtil;

/**
 * 表情相关 封装
 * 
 * @author Administrator
 *
 */
public class EmojisRelevantUtils {
	//解析xml相关
		private static EmojiParser parser;  
	    private static List<ChatEmoji> chatEmojis; 
	    private static List<String> staticFacesList; 
	

	
	/**
	 * 得到一个SpanableString对象，通过传入的字符串,并进行正则判断
	 * 
	 * @param context
	 * @param str
	 * @return
	 */
	public static  SpannableString getExpressionString(Context context, String str,List<ChatEmoji> chatEmojis) {
		
		
		SpannableString spannableString = new SpannableString(str);
		// 正则表达式比配字符串里是否含有表情，如： 我好[开心]啊
		String zhengze = "\\[[^\\]]+\\]";
		// 通过传入的正则表达式来生成一个pattern
		Pattern sinaPatten = Pattern.compile(zhengze, Pattern.CASE_INSENSITIVE);
		try {
			dealExpression(context, spannableString, sinaPatten, 0,chatEmojis);
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
			SpannableString spannableString, Pattern patten, int start, List<ChatEmoji> chatEmojis)
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
				bitmap = Bitmap.createScaledBitmap(bitmap, DensityUtil.dip2px(context, 50),DensityUtil.dip2px(context, 50), true);
				// 通过图片资源id来得到bitmap，用一个ImageSpan来包装
				ImageSpan imageSpan = new ImageSpan(bitmap);
				// 计算该图片名字的长度，也就是要替换的字符串的长度
				int end = matcher.start() + key.length();
				// 将该图片替换字符串中规定的位置中
				spannableString.setSpan(imageSpan, matcher.start(), end,
						Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
				if (end < spannableString.length()) {
					// 如果整个字符串还未验证完，则继续。。
					dealExpression(context, spannableString, patten, end,chatEmojis);
				}
				break;
			}
		}
	}
	

}
