package com.xunjian.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementExecute {

	//判断页面元素是否存在
	public static boolean isElementPresent(WebDriver driver,By by){
			try{
		          driver.findElement(by);
		          return true;
		      }catch(Exception e){
		          return false;
		      }
	}
}
