package com.meetu.activity.miliao;

import java.io.InputStream;
import java.util.List;

import com.meetu.entity.ChatEmoji;

public interface  EmojiParser {
	/** 
     * 解析输入流 得到emoji对象集合 
     * @param is 
     * @return 
     * @throws Exception 
     */  
    public List<ChatEmoji> parse(InputStream is) throws Exception;
    

}
