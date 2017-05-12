package com.xunjian.test;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXML {

	String chrome_drvier_name; //谷歌驱动
	String chrome_driver_path; 
	String chrome_bin_name; //谷歌浏览器路径
	String chrome_bin_path;
	
	//图片路径
	String photo_path;
	
	//九州通用户名和密码及手机号码
	String jiuzhoutong_username;
	String jiuzhoutong_password;
	String jiuzhoutong_mobile;
	
	//信用惠
	String xinyonghui_loginName; //信用惠用户名
	String xinyonghui_password;//信用惠密码
	
	 //1、创建一个DocumentBuilderFactory的对象
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	
	//谷歌浏览器
	//set
	public void setChrome_drvier_name(String chrome_drvier_name) {
		this.chrome_drvier_name = chrome_drvier_name;
	}
	public void setChrome_driver_path(String chrome_driver_path) {
		this.chrome_driver_path = chrome_driver_path;
	}
	public void setChrome_bin_name(String chrome_bin_name) {
		this.chrome_bin_name = chrome_bin_name;
	}
	public void setChrome_bin_path(String chrome_bin_path) {
		this.chrome_bin_path = chrome_bin_path;
	}
	//get
	public String getChrome_drvier_name() {
		return chrome_drvier_name;
	}
	public String getChrome_driver_path() {
		return chrome_driver_path;
	}
	public String getChrome_bin_name() {
		return chrome_bin_name;
	}
	public String getChrome_bin_path() {
		return chrome_bin_path;
	}
	
	//截图
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	
	//九州通
	//set
	public void setJiuzhoutong_username(String jiuzhoutong_username) {
		this.jiuzhoutong_username = jiuzhoutong_username;
	}
	public void setJiuzhoutong_password(String jiuzhoutong_password) {
		this.jiuzhoutong_password = jiuzhoutong_password;
	}
	public void setJiuzhoutong_mobile(String jiuzhoutong_mobile) {
		this.jiuzhoutong_mobile = jiuzhoutong_mobile;
	}
	//get
	public String getJiuzhoutong_username() {
		return jiuzhoutong_username;
	}
	public String getJiuzhoutong_password() {
		return jiuzhoutong_password;
	}
	public String getJiuzhoutong_mobile() {
		return jiuzhoutong_mobile;
	}
	
	//信用惠
	public void setXinyonghui_loginName(String xinyonghui_loginName) {
		this.xinyonghui_loginName = xinyonghui_loginName;
	}
	public void setXinyonghui_password(String xinyonghui_password) {
		this.xinyonghui_password = xinyonghui_password;
	}
	public String getXinyonghui_loginName() {
		return xinyonghui_loginName;
	}
	public String getXinyonghui_password() {
		return xinyonghui_password;
	}
	
	public ReadXML(String fileName)
	{
		try {
            //创建DocumentBuilder对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            //3、通过DocumentBuilder对象的parser方法加载books.xml文件到当前项目下
            /*注意导入Document对象时，要导入org.w3c.dom.Document包下的*/
            Document document = db.parse(fileName);//传入文件名可以是相对路径也可以是绝对路径
            
            
            //谷歌驱动
            //获取所有driverinfo的子节点
            NodeList driverInfo = document.getElementsByTagName("driverinfo");
            
            //遍历每一个dirverInfo节点
            for(int i = 0 ; i < driverInfo.getLength(); i++){
            	
            	//通过 item(i)方法 获取一个driver节点，nodelist的索引值从0开始
            	Node driver = driverInfo.item(i);
            	
            	//解析driver节点的子节点
            	NodeList childNodes = driver.getChildNodes();
            	
            	for (int k = 0; k < childNodes.getLength(); k++) {
                    //区分出text类型的node以及element类型的node
                      if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE){
                    	  //对chromename进行赋值
                    	  if(childNodes.item(k).getNodeName().equals("drivername"))
                    	  {
                    		  //获取了element类型节点的节点名并对谷歌驱动名称进行赋值
                    		  chrome_drvier_name = childNodes.item(k).getFirstChild().getNodeValue();
                    	  }
                        
                    	  //对chromepath进行赋值
                    	  if(childNodes.item(k).getNodeName().equals("path"))
                    	  {
                    		  chrome_driver_path = childNodes.item(k).getFirstChild().getNodeValue();
                    	  }
                      	}
                 	}
             	}
            
            
            //谷歌浏览器
            NodeList binInfo = document.getElementsByTagName("bininfo");
            
            //遍历每一个bininfo节点的子节点
          //遍历每一个dirverInfo节点
            for(int i = 0 ; i < binInfo.getLength(); i++){
            	
            	//通过 item(i)方法 获取一个book节点，nodelist的索引值从0开始
            	Node bin = binInfo.item(i);
            	
            	//解析driver节点的子节点
            	NodeList childNodes = bin.getChildNodes();
            	
            	for (int k = 0; k < childNodes.getLength(); k++) {
                    //区分出text类型的node以及element类型的node
                      if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE){
                    	  //对binname进行赋值
                    	  if(childNodes.item(k).getNodeName().equals("binname"))
                    	  {
                    		  //获取了element类型节点的节点名并对谷歌驱动名称进行赋值
                    		  chrome_bin_name = childNodes.item(k).getFirstChild().getNodeValue();
                    	  }
                        
                    	  //对binpath进行赋值
                    	  if(childNodes.item(k).getNodeName().equals("path"))
                    	  {
                    		  chrome_bin_path = childNodes.item(k).getFirstChild().getNodeValue();
                    	  }
                      	}
                 	}
             	}
            
            
            //图片存储路径
            //获取所有图片路径的子节点
            NodeList photopathList = document.getElementsByTagName("photopath");
            
            //遍历每一个photopathList节点
            for(int i = 0 ; i < photopathList.getLength(); i++){
            	
            	//通过 item(i)方法 获取一个book节点，nodelist的索引值从0开始
            	Node photopathNode = photopathList.item(i);
            	
            	//解析driver节点的子节点
            	NodeList childNodes = photopathNode.getChildNodes();
            	
            	for (int k = 0; k < childNodes.getLength(); k++) {
                    //区分出text类型的node以及element类型的node
                      if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE){
                    	  //对photopath进行赋值
                    	  if(childNodes.item(k).getNodeName().equals("path"))
                    	  {
                    		  //获取了element类型节点的节点名并对谷歌驱动名称进行赋值
                    		  photo_path = childNodes.item(k).getFirstChild().getNodeValue();
                    	  }
                      	}
                 	}
             	}
            
            
            //九州通用户名、密码与手机号码的读取
            //获取所有jiuzhoutong的子节点
            NodeList jiuzhoutongList = document.getElementsByTagName("jiuzhoutong");
            
            //遍历每一个jiuzhoutongList节点
            for(int i = 0 ; i < jiuzhoutongList.getLength(); i++){
            	
            	//通过 item(i)方法 获取一个book节点，nodelist的索引值从0开始
            	Node jiuzhoutong = jiuzhoutongList.item(i);
            	
            	//解析driver节点的子节点
            	NodeList childNodes = jiuzhoutong.getChildNodes();
            	
            	for (int k = 0; k < childNodes.getLength(); k++) {
                    //区分出text类型的node以及element类型的node
                      if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE){
                    	  //对username进行赋值
                    	  if(childNodes.item(k).getNodeName().equals("username") )
                    	  {
                    		  //获取了element类型节点的节点名并对谷歌驱动名称进行赋值
                    		  jiuzhoutong_username = childNodes.item(k).getFirstChild().getNodeValue();
                    	  }
                        
                    	  //对password进行赋值
                    	  if(childNodes.item(k).getNodeName().equals("password") )
                    	  {
                    		  jiuzhoutong_password = childNodes.item(k).getFirstChild().getNodeValue();
                    	  }
                    	  
                    	  //对mobile进行赋值
                    	  if(childNodes.item(k).getNodeName().equals("mobile"))
                    	  {
                    		  jiuzhoutong_mobile = childNodes.item(k).getFirstChild().getNodeValue();
                    	  }
                      	}
                 }
             }
            
            //信用惠用户名、密码的读取
            //获取所有xinyonghui的子节点
            NodeList xinyonghuiList = document.getElementsByTagName("xinyonghui");
            
            //遍历每一个xinyonghuiList节点
            for(int i = 0; i < xinyonghuiList.getLength(); i++){
            	
            	//通过item(i)方法获取一个xinyonghui节点，nodelist的索引值从0开始
            	Node xinyonghui = xinyonghuiList.item(i);
            	
            	//解析xinyonghui节点的子节点
            	NodeList childNodes = xinyonghui.getChildNodes();
            	
            	for(int k = 0; k < childNodes.getLength(); k++){
            		//区分出text类型的node以及element类型的node
            		if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE){
            			//对xinyonghui_loginname进行赋值
            			if(childNodes.item(k).getNodeName().equals("loginName")){
            				//获取element类型节点的节点名并对xinyonghui_loginname进行赋值
            				xinyonghui_loginName = childNodes.item(k).getFirstChild().getNodeValue();
            			}
            			
            			//对xinyonghui_passwrod进行赋值
            			if(childNodes.item(k).getNodeName().equals("password")){
            				//获取element类型节点的节点名并对xinyonghui_password进行赋值
            				xinyonghui_password = childNodes.item(k).getFirstChild().getNodeValue();
            			}
            		}
            	}
            }
     
		}catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
