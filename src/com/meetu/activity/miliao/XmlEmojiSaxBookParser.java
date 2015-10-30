package com.meetu.activity.miliao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.meetu.entity.ChatEmoji;

public class XmlEmojiSaxBookParser implements EmojiParser{

	@Override
	public List<ChatEmoji> parse(InputStream is) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();  //取得SAXParserFactory实例  
        SAXParser parser = factory.newSAXParser();                  //从factory获取SAXParser实例  
        MyHandler handler = new MyHandler();                        //实例化自定义Handler  
        parser.parse(is, handler);                                  //根据自定义Handler规则解析输入流  
        return handler.getEmojis();  
	}
	
	//需要重写DefaultHandler的方法  
    private class MyHandler extends DefaultHandler {  
  
        private List<ChatEmoji> emojis;  
        private ChatEmoji emoji;  
        private StringBuilder builder;  
          
        //返回解析后得到的Book对象集合  
        public List<ChatEmoji> getEmojis() {  
            return emojis;  
        }  
          
        @Override  
        public void startDocument() throws SAXException {  
            super.startDocument();  
            emojis = new ArrayList<ChatEmoji>();  
            builder = new StringBuilder();  
        }  
  
        @Override  
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
            super.startElement(uri, localName, qName, attributes);  
            if (localName.equals("book")) {  
                emoji = new ChatEmoji();  
            }  
            builder.setLength(0);   //将字符长度设置为0 以便重新开始读取元素内的字符节点  
        }  
          
        @Override  
        public void characters(char[] ch, int start, int length) throws SAXException {  
            super.characters(ch, start, length);  
            builder.append(ch, start, length);  //将读取的字符数组追加到builder中  
        }  
          
        @Override  
        public void endElement(String uri, String localName, String qName) throws SAXException {  
            super.endElement(uri, localName, qName);  
//            if (localName.equals("id")) {  
//                emoji.setId(Integer.parseInt(builder.toString()));  
 //           } else
            	if (localName.equals("key")) {  
            	emoji.setCharacter(builder.toString());  
            } else if (localName.equals("string")) {  
            	emoji.setFaceName(builder.toString());  
            } else if (localName.equals("emoji")) {  
                emojis.add(emoji);  
            }  
        }  
    }  

}
