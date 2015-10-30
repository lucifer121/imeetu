package com.meetu.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

public class SavePictureInLocal {
	//保存拍照图片至SD卡

	//参数为调用系统相机后OK的返回Intent
	 public void saveImage(Intent data) {
	  
	  Bundle bundle = data.getExtras();
	        Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
	        FileOutputStream b = null;
	        File file = new File("/sdcard/myImage/");
	        if(!file.exists()){
	         file.mkdirs();// 创建文件夹
	        }
	        //生成随机数，命名图片
	        String uuid = UUID.randomUUID().toString();	 
	        String  picName = uuid + ".jpg";
	        String fileName = "/sdcard/fastQ/myImage/"+picName;
	                
	        try {
	            b = new FileOutputStream(fileName);
	            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                b.flush();
	                b.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	       
	  
	 } 

}
