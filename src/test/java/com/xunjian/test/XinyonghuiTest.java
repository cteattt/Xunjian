package com.xunjian.test;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import static org.testng.Assert.*;

public class XinyonghuiTest {
	private WebDriver driver;
	private String baseurl;
	
	//读取config文件的内容
	ReadXML rdxml_config = new ReadXML("config.xml");
	
	//读取参数的内容
	ReadXML rdxml_parameter = new ReadXML("parameter.xml");
	
	//谷歌驱动配置
	private String chrome_driver_path;
	private String chrome_bin_path;
	
	//用户名和密码
	private String loginName;
	private String password;
	
	//截图路径
	private String photo_path;
	
	@BeforeClass
	public void setUp() throws Exception
	{	
		baseurl = "https://e.cmbchina.com/CmbBank_EHome/UI/Home.aspx";
		
		chrome_driver_path = rdxml_config.getChrome_driver_path();
		chrome_bin_path = rdxml_config.getChrome_bin_path();
		
		//用户登录信息
		loginName = rdxml_parameter.getXinyonghui_loginName();
		password = rdxml_parameter.getXinyonghui_password();
		
		photo_path = rdxml_config.getPhoto_path();
		
		System.out.println("---------------------------------信用惠开始--------------------------");
	}
  
	//用户登录
	@Test
	public void testLogin() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", chrome_driver_path);
		System.setProperty("webdriver.chrome.bin", chrome_bin_path);
		driver = new ChromeDriver();
		System.out.println("---------------------用户登录---------------------");

		//将浏览器页面最大化
		driver.manage().window().maximize();
		
		driver.get(baseurl);
		
		Thread.sleep(5000);
				
		Login.xinyonghuiLogin(driver, loginName, password);
		
		//验证用户是否登录成功
		assertEquals(driver.findElement(By.id("CommonHeader_usrName_Top")).getText(), loginName);
		
		Thread.sleep(5000);
		
		//用户登录成功截图
		Screenshot.fullScreenshot(driver, photo_path+"信用惠\\用户登录.jpg");
		
		Thread.sleep(1000);
		
		//滚动到屏幕底部，然后进行截图
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
	    //对滚动之后的页面进行截图
		Screenshot.fullScreenshot(driver, photo_path+"信用惠\\登录后滚动到页面底部的截图.jpg");
		
		Thread.sleep(2000);
		
		driver.close();
	}
	
	@Test //积分兑换
	public void testJifenskip() throws Exception{
		System.setProperty("webdriver.chrome.driver", chrome_driver_path);
		System.setProperty("webdriver.chrome.bin", chrome_bin_path);
		driver = new ChromeDriver();
		System.out.println("---------------------积分兑换页面跳转---------------------");
		driver.get(baseurl);
		Thread.sleep(5000);
		
		driver.switchTo().frame("mainwin");
		driver.findElement(By.linkText("前往个人>>")).click();
		
		Thread.sleep(2000);
		
		driver.switchTo().frame(driver.findElement(By.id("mainwin")));
		//积分页面兑换页面跳转
		driver.findElement(By.xpath("/html/body/div[3]/div/p/a")).click();
		Thread.sleep(4000);
		
		//跳转页面截图
		Screenshot.fullScreenshot(driver, photo_path + "信用惠\\积分兑换截图1.jpg");
		
		Thread.sleep(2000);
		
		driver.close();
		
	}

	@AfterClass
	public void tearDown() throws Exception 
	{
		driver.quit();
	}
}
