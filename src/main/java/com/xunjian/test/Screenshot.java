package com.xunjian.test;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Screenshot {

	//全屏截图
	public static void fullScreenshot(WebDriver driver, String filepath)
	{
		try {
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	//屏幕定点截图-九州通
	public static void locationScreenshot(WebDriver driver, String filepath, WebElement element) throws InterruptedException
	{
		try {
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			Point p = element.getLocation();
			Rectangle rect = new Rectangle(53, 20);
			BufferedImage img = ImageIO.read(srcFile);
			
			//九州通截图 
			BufferedImage dest = img.getSubimage(p.getX()+2, p.getY()+1, rect.width, rect.height);
			ImageIO.write(dest, "png", srcFile);
			Thread.sleep(1000);
			FileUtils.copyFile(srcFile, new File(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//信用惠验证码截图
	public static void xinyonghuicodeScreenShot(WebDriver driver, String filepath, WebElement element) throws InterruptedException
	{
		try{
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			Point p = element.getLocation();
			BufferedImage img = ImageIO.read(srcFile);
			
			//信用惠截图
			BufferedImage dest = img.getSubimage(p.getX(), p.getY(), 80, 32);
			ImageIO.write(dest, "jpg", srcFile);
			Thread.sleep(1000);
			FileUtils.copyFile(srcFile, new File(filepath));
		} catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//融资登录页面截图
	public static void efinanceloanScreenShot(WebDriver driver, String filepath, WebElement element) throws InterruptedException{
		try{
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			Point p = element.getLocation();
			BufferedImage img = ImageIO.read(srcFile);
			
			//融资登录页面截图
			BufferedImage dest = img.getSubimage(p.getX()+5, p.getY()+3, 72, 28);
			ImageIO.write(dest, "jpg", srcFile);
			Thread.sleep(1000);
			FileUtils.copyFile(srcFile, new File(filepath));
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
