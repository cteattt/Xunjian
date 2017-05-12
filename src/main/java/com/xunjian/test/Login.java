package com.xunjian.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Login {
	
	//截图路径
	static ReadXML rdxml_config = new ReadXML("config.xml");
	private static String jiuzhoutong_vcode;
	private static String xinyonghui_vcode;

	//九州通登录
	public static void jiuzhoutongLogin(WebDriver driver,String username, String password)throws Exception
	{	
		//读取txt文件
		ReadFile rdtxt = new ReadFile();
		//验证码图片路径
		String vcodeImg_path = rdxml_config.getPhoto_path() + "九州通\\jiuzhoutong_vcodeImg.png";
		//tesseract执行命令
		String tesseract_cmd = "cmd.exe /C tesseract.exe " + vcodeImg_path + " " + rdxml_config.getPhoto_path() + "九州通\\jiuzhoutong_vcode";
		int count = 1;
			
		driver.findElement(By.linkText("请登录")).click();
		
		/*
		 * 1.增加判断，如果验证码输入错误，点击换一张，再重新截图并进行验证码的输入
		 * 2.如果输入次数超过5次，弹出错误提示框，手动输入
		 */
		for(int i = 0; i < 10; i++){		
			//登录页面填写用户信息
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(password);
			
			Thread.sleep(4000);
			
			//验证码元素
			WebElement vcodeImg = driver.findElement(By.id("vcodeImg"));
			
			//调用验证码截图
			Screenshot.locationScreenshot(driver, vcodeImg_path, vcodeImg);
			
			//tesseract分析截图的内容,use Tesseract to get strings
			Runtime rt = Runtime.getRuntime();
			rt.exec(tesseract_cmd);
		    
			Thread.sleep(3000);
			
			String vcode_path = rdxml_config.getPhoto_path() + "九州通\\jiuzhoutong_vcode.txt";
			
			//验证码与分析图片验证码相同
			jiuzhoutong_vcode = rdtxt.readTxt(vcode_path);
			
			System.out.println(jiuzhoutong_vcode);
			//验证码控件，并输入验证码
			
			driver.findElement(By.id("vcode")).clear();
			driver.findElement(By.id("vcode")).sendKeys(jiuzhoutong_vcode);
			
			Thread.sleep(2000);
			driver.findElement(By.id("subbtn")).click();
			
			//错误信息是否显示
			if(ElementExecute.isElementPresent(driver, By.id("errorMsg")) == true){
				//错误次数+1
				count++;
			}else{
				//不显示错误信息，跳出循环
				break;
			}
			
			//换一张验证码图片
			driver.findElement(By.id("vcodeA")).click();
			
			Thread.sleep(2000);
		 }
		
		//判断count是否为6，如果为6，则手动输入验证码
		if(count == 10){
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(password);
			
			//弹出弹框提示请输入验证码
			((JavascriptExecutor)driver).executeScript("alert('请输入验证码!')");
			
			Thread.sleep(10000);
			
			driver.findElement(By.id("subbtn")).click();
		}
	}
		
	
		//信用惠登录
	public static void xinyonghuiLogin(WebDriver driver,String loginName, String password) throws Exception
	{	
		int count = 1;
		//读取txt文件
		ReadFile rdtxt = new ReadFile();
		
		driver.switchTo().frame("mainwin");
		driver.findElement(By.linkText("前往个人>>")).click();
			
		Thread.sleep(3000);
		driver.findElement(By.id("usrLoginUrl")).click();

		Thread.sleep(3000);
		
		/*
		 *1.增加判断，如果验证码输入错误，点击换一张，再重新截图并进行验证码的输入
		 *2.如果输出次数超过5次，弹出错误提示框，手动输入 
		 */
		for(int i = 0; i < 10; i++){
			//个人用户登录
			driver.findElement(By.id("loginName")).clear();
			driver.findElement(By.id("loginName")).sendKeys(loginName);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(password);
			
			//验证码元素
			WebElement ChkCode = driver.findElement(By.id("checkCode"));
			
			//调用验证码截图
			Screenshot.xinyonghuicodeScreenShot(driver, "F:\\截图\\巡检\\信用惠\\xinyonghui_old_vcode.jpg", ChkCode);
			Thread.sleep(3000);
			
			//对验证码图片进行处理
			new PicutureHandle("F:\\截图\\巡检\\信用惠\\xinyonghui_old_vcode.jpg", "F:/截图/巡检/信用惠/xinyonghui_checkCode_new.jpg");
			Thread.sleep(3000);
			
			//tesseract分析截图的内容，use Tesseract to get strings
			Runtime rt = Runtime.getRuntime();
			rt.exec("cmd.exe /C tesseract.exe F:\\截图\\巡检\\信用惠\\xinyonghui_checkCode_new.jpg F:\\截图\\巡检\\信用惠\\xinyonghui_vcode");
			Thread.sleep(3000);
			
			xinyonghui_vcode = rdtxt.readTxt("F:\\截图\\巡检\\信用惠\\xinyonghui_vcode.txt");
			
			Thread.sleep(3000);
			driver.findElement(By.id("vCode")).clear();
			driver.findElement(By.id("vCode")).sendKeys(xinyonghui_vcode);
			
			Thread.sleep(3000);
			driver.findElement(By.id("lgnBtn")).click();
			Thread.sleep(2000);
			
			//错误提示是否显示
			if(ElementExecute.isElementPresent(driver, By.xpath("/html/body/form/div[4]/div/div/div/table/tbody/tr[2]/td/div/span")) == true){
				//错误次数+1
				count++;
			}
			else{
				//不显示错误信息，跳出循环
				break;
			}
			
			//先切换到企业登录再切换到个人登录
			driver.findElement(By.id("gotoCorpLgn")).click();
			Thread.sleep(4000);
			
			driver.findElement(By.id("gotpPersonLgn")).click();
		    Thread.sleep(2000);
			
		}
		
		//判断count是否为10，如果为10，则手动输入验证码
		if(count == 10){
			driver.findElement(By.id("loginName")).clear();
			driver.findElement(By.id("loginName")).sendKeys(loginName);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(password);
			
			//弹出弹框提示请输入验证码
			((JavascriptExecutor)driver).executeScript("alert('请输入验证码并点击确认按钮!')");
						
			Thread.sleep(10000);
		}
		
	}

}
