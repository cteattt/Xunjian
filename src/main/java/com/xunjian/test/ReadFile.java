package com.xunjian.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//读取文件
public class ReadFile {
	
	private String jiuzhoutong_vcode; //九州通验证码
	ReadXML rdxml_config = new ReadXML("config.xml");
	
	public void setJiuzhoutong_vcode(String jiuzhoutong_vcode) {
		this.jiuzhoutong_vcode = jiuzhoutong_vcode;
	}
	public String getJiuzhoutong_vcode() {
		return jiuzhoutong_vcode;
	}
	
	public String readTxt(String filePath) throws Exception{
		String lineTxt = null;
		String encoding = "GBK";
		File file = new File(filePath);
		if(file.isFile() && file.exists()){ //判断文件是否存在
			InputStreamReader read = new InputStreamReader(
					new FileInputStream(file), encoding); //考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			while((lineTxt = bufferedReader.readLine()) != null){
				return lineTxt;
			}
			read.close();
		}else{
				System.out.println("找不到指定的文件!");
		}
		return lineTxt;
	}
}


