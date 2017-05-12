package com.xunjian.test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

//去除噪点
public class PicutureHandle {
	
	//默认构造方法
	public PicutureHandle(){
		
	}
	
	public PicutureHandle(String filename, String save_filename) throws IOException{
		//读取文件夹里的图片
		BufferedImage img = ImageIO.read(new File(filename));
		//获取图片的高度
		int width = img.getWidth();
		int height = img.getHeight();
		
		//循环执行除去干扰像素
		for(int i = 1; i < width; i++){
			Color colorFirst = new Color(img.getRGB(i, 1));
			int numFirstGet = colorFirst.getRed() + colorFirst.getGreen() + colorFirst.getBlue();
			for(int x = 0; x < width; x++){
				for(int y = 0; y < height; y++){
					Color color = new Color(img.getRGB(x, y));
//					System.out.println("red:" + color.getRed() + " | " + "green:" + color.getGreen()
//					+ " | " + "blue:" + color.getBlue());
					int num = color.getRed() + color.getGreen() + color.getBlue();
					if(num >= numFirstGet){
						img.setRGB(x, y, Color.WHITE.getRGB());
					}
				}
			}
		}
		
		//图片背景变黑色
		for(int i = 1; i < width; i++){
			Color color1 = new Color(img.getRGB(i, 1));
			int num1 = color1.getRed() + color1.getGreen() + color1.getBlue();
			for (int x = 0; x < width; x++){
				for(int y = 0; y < height; y++){
					Color color = new Color(img.getRGB(x, y));
//					System.out.println("red:" + color.getRed() + " | " + "green:" + color.getGreen()
//					+ " | " + "blue:" + color.getBlue());
					int num = color.getRed() + color.getGreen() + color.getBlue();
					if(num == num1){
						img.setRGB(x, y, Color.BLACK.getRGB());
					}else{
						img.setRGB(x, y, Color.WHITE.getRGB());
					}
				}
			}
		}
		
		//保存图片
		File save_file = new File(save_filename);
		if(!save_file.exists()){
			File dir = save_file.getParentFile();
			if(!dir.exists()){
				dir.mkdirs();
			}
			try{
				save_file.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		ImageIO.write(img, "jpg", save_file);
	}
}
