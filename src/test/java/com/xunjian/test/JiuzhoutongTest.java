package com.xunjian.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class JiuzhoutongTest {
	
	private WebDriver driver;
	private String username;
	private String password;
	private String mobile;
	private String baseurl;
	private String photo_path;
	private String chrome_drvier_name;
	private String chrome_driver_path;
	private String chrome_bin_name;
	private String chrome_bin_path;
	
	//获取配置文件地址
	ReadXML rdxml_config = new ReadXML("config.xml");
	ReadXML rdxml_parameter = new ReadXML("parameter.xml");
  
	@BeforeClass
	public void setUp() throws Exception
	{
		chrome_drvier_name = rdxml_config.getChrome_drvier_name(); //谷歌驱动
		chrome_driver_path = rdxml_config.getChrome_driver_path();
		chrome_bin_name = rdxml_config.getChrome_bin_name(); //谷歌浏览器路径
		chrome_bin_path = rdxml_config.getChrome_bin_path();
		
		baseurl = "http://www.54315.com/";
		
		//用户登录信息
		username = rdxml_parameter.getJiuzhoutong_username();
		password = rdxml_parameter.getJiuzhoutong_password();
		
		//用户注册用的手机号
		mobile = rdxml_parameter.getJiuzhoutong_mobile();
		
		//截图保存的位置
		photo_path = rdxml_config.getPhoto_path();
		
		System.out.println("---------------------九州通开始-------------------------");
	}
  
	//用户登录测试用例
	@Test
	public void testLogin() throws Exception
	{
		System.out.println("---------------------登录---------------------");
		
		//谷歌浏览器
		System.setProperty(chrome_drvier_name, chrome_driver_path);
		System.setProperty(chrome_bin_name, chrome_bin_path);  			
		driver = new ChromeDriver();
		
		//将浏览器页面最大化
		driver.manage().window().maximize();
		
		driver.get(baseurl);
		
		Login.jiuzhoutongLogin(driver, username, password);
		Thread.sleep(3000);
		
		//断言通过用户名查看是否成功
		assertEquals(driver.findElement(By.cssSelector("span.pr20")).getText(), "您好，" + username);
		
		//对登录之后的页面进行截图
		Screenshot.fullScreenshot(driver, photo_path+"九州通\\用户登录.jpg");
		
		Thread.sleep(1000);
		
		//移动页面最底部
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		Thread.sleep(3000);
		
		//对滚动之后的页面进行截图
		Screenshot.fullScreenshot(driver, photo_path+"九州通\\滚动到底部的页面.jpg");
		
		driver.close();
	}
	
	//页面跳转
	//挂牌销售
	@Test
	public void testGuapaixiaoshouSkip() throws Exception
	{
		System.out.println("---------------------挂牌销售跳转---------------------");
		
		//谷歌浏览器
		System.setProperty(chrome_drvier_name, chrome_driver_path);
		System.setProperty(chrome_bin_name, chrome_bin_path);				
		driver = new ChromeDriver();
				
		driver.get(baseurl);
		
		//将浏览器页面最大化
//	    driver.manage().window().maximize();
		
		//获得当前窗口句柄
		String current_window = driver.getWindowHandle();
		
		//打开挂牌销售页面
		//鼠标悬停
		Actions action = new Actions(driver);
		action.clickAndHold(driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/ul/li[2]/span/i[1]"))).perform();
		driver.findElement(By.linkText("挂牌销售")).click();
		
		
		//获得所有窗口句柄
		Set<String> handles = driver.getWindowHandles();
		
		//判断是否为挂牌销售窗口，并关闭挂牌销售界面
		for(String handle : handles)
		{
			if(handle.equals(current_window) == false)
			{
				//切换到挂牌销售界面
				driver.switchTo().window(handle);
				Thread.sleep(2000);
				System.out.println("Now 挂牌销售 window!");
				
				//进行截图
				Screenshot.fullScreenshot(driver, photo_path+"九州通\\挂牌销售.jpg");
				
				Thread.sleep(1000);
			}
			
			driver.close();
		}
	}
	
	//药材采购
	@Test
	public void testYaocaicaigouSkip() throws Exception
	{
		System.out.println("---------------------药材采购跳转---------------------");
		
		//谷歌浏览器
		System.setProperty(chrome_drvier_name, chrome_driver_path);
		System.setProperty(chrome_bin_name, chrome_bin_path);  
		driver = new ChromeDriver();
		
		driver.get(baseurl);
		
		//将浏览器页面最大化
//	    driver.manage().window().maximize();
		
		//获得当前窗口句柄
		String current_window = driver.getWindowHandle();
		
		//打开药材采购页面
		//鼠标悬停
		Actions action = new Actions(driver);
		action.clickAndHold(driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/ul/li[2]/span/i[1]"))).perform();
		driver.findElement(By.linkText("药材采购")).click();
		
		
		//获得所有窗口句柄
		Set<String> handles = driver.getWindowHandles();
		
		//判断是否为药材采购窗口，并关闭药材采购界面
		for(String handle : handles)
		{
			if(handle.equals(current_window) == false)
			{
				//切换到药材采购界面
				driver.switchTo().window(handle);
				Thread.sleep(2000);
				System.out.println("Now 药材采购 window!");
				
				//进行截图
				Screenshot.fullScreenshot(driver, photo_path+"九州通\\药材采购.jpg");
				
				Thread.sleep(1000);
			}
			
			driver.close();
		}
	}
	
	//药材金融
	@Test
	public void testYaocaijinrongSkip() throws Exception
	{
		System.out.println("---------------------药材金融跳转---------------------");
		
//		//谷歌浏览器
		System.setProperty(chrome_drvier_name, chrome_driver_path);
		System.setProperty(chrome_bin_name, chrome_bin_path); 
		driver = new ChromeDriver();
		
		driver.get(baseurl);
		
		//将浏览器页面最大化
//	    driver.manage().window().maximize();
		
		//获得当前窗口句柄
		String current_window = driver.getWindowHandle();
		
		//打开药材金融页面
		//鼠标悬停
		Actions action = new Actions(driver);
		action.clickAndHold(driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/ul/li[2]/span/i[1]"))).perform();
		driver.findElement(By.linkText("药材金融")).click();
		
		
		//获得所有窗口句柄
		Set<String> handles = driver.getWindowHandles();
		
		//判断是否为药材金融窗口，并关闭挂牌销售界面
		for(String handle : handles)
		{
			if(handle.equals(current_window) == false)
			{
				//切换到药材金融界面
				driver.switchTo().window(handle);
				Thread.sleep(2000);
				System.out.println("Now 药材金融 window!");
				
				//进行截图
				Screenshot.fullScreenshot(driver, photo_path+"九州通\\药材金融.jpg");
				
				Thread.sleep(1000);
			}
			driver.close();
		}
	}
	
	//仓储服务
	@Test
	public void testCangchufuwuSkip() throws Exception
	{
		System.out.println("---------------------仓储服务跳转---------------------");
		
		//谷歌浏览器
		System.setProperty(chrome_drvier_name, chrome_driver_path);
		System.setProperty(chrome_bin_name, chrome_bin_path);
		driver = new ChromeDriver();
		
		driver.get(baseurl);
		
		//将浏览器页面最大化
//	    driver.manage().window().maximize();
		
		//获得当前窗口句柄
		String current_window = driver.getWindowHandle();
		
		//打开仓储服务页面
		//鼠标悬停
		Actions action = new Actions(driver);
		action.clickAndHold(driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/ul/li[2]/span/i[1]"))).perform();
		driver.findElement(By.linkText("仓储服务")).click();
		
		
		//获得所有窗口句柄
		Set<String> handles = driver.getWindowHandles();
		
		//判断是否为仓储服务窗口，并关闭仓储服务界面
		for(String handle : handles)
		{
			if(handle.equals(current_window) == false)
			{
				//切换到仓储服务界面
				driver.switchTo().window(handle);
				Thread.sleep(2000);
				System.out.println("Now 仓储服务 window!");
				
				//进行截图
				Screenshot.fullScreenshot(driver, photo_path+"九州通\\仓储服务.jpg");
				
				Thread.sleep(1000);
			}
			driver.close();
		}
	}
	
	//质量保证体系
	@Test
	public void testZhiliangtixiSkip() throws Exception
	{
		System.out.println("---------------------质量保证体系跳转---------------------");
		
		//谷歌浏览器
		System.setProperty(chrome_drvier_name, chrome_driver_path);
		System.setProperty(chrome_bin_name, chrome_bin_path);
		driver = new ChromeDriver();
		
		driver.get(baseurl);
		
		//将浏览器页面最大化
//	    driver.manage().window().maximize();
		
	    driver.findElement(By.linkText("质量保障体系")).click();
	    
	    //进行截图
		Screenshot.fullScreenshot(driver, photo_path+"九州通\\质量保障体系.jpg");
		
		Point p_fugai = driver.findElement(By.xpath("/html/body/div[3]/h2[1]")).getLocation();
		//跳转到相应的坐标并截图
		((JavascriptExecutor)driver).executeScript("scrollTo(" + p_fugai.getX() + "," + p_fugai.getY() + ")");
		Screenshot.fullScreenshot(driver, photo_path+"九州通\\覆盖全国的检测网络.jpg");
		
		Point p_zhuanye = driver.findElement(By.xpath("/html/body/div[3]/h2[2]")).getLocation();
		//跳转到相应的坐标并截图
		((JavascriptExecutor)driver).executeScript("scrollTo(" + p_zhuanye.getX() + "," + p_zhuanye.getY() + ")");
		Screenshot.fullScreenshot(driver, photo_path+"九州通\\专业的质检团队与设备.jpg");
		
		driver.close();
	}
	
	@AfterClass
	public void tearDown() throws Exception 
	{
		driver.quit();
	}
}
