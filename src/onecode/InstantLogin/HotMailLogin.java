/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onecode.InstantLogin;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
/**
 *
 * @author Bipin
 */
public class HotMailLogin implements Login{
    private WebElement user,pass,btn1,btn2;

    @Override
    public void login(String username, String password,int i) throws InterruptedException
    {
       System.setProperty("webdriver.gecko.driver","D:\\Project\\ONECode\\assets\\geckodriver-v0.17.0-win64");
       
      driver.manage().window().maximize();
      
      int c=i;
      
      if(c==0)
      {
          driver.manage().window().maximize();
          driver.get("http://www.hotmail.com");
          
          user=driver.findElement(By.id("i0116"));
          user.sendKeys(username);
        
          Thread.sleep(3000);
        
          btn1=driver.findElement(By.className("btn-primary"));
          btn1.click();
        
          Thread.sleep(3000);
        
          pass=driver.findElement(By.id("i0118"));
          pass.sendKeys(password);
        
          Thread.sleep(3000);
        
          btn2=driver.findElement(By.id("idSIButton9"));
          btn2.click();
         
      }
      else
      {
          try
          {
                  driver.manage().window().maximize();
                  String selectLinkOpeninNewTabs = Keys.chord(Keys.CONTROL,"t"); 
                  driver.findElement(By.tagName("body")).sendKeys(selectLinkOp‌​eninNewTabs); 
                  Thread.sleep(2000); 
                  
                  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles()); 
                  driver.switchTo().window(tabs.get(c)); 
                  driver.get("http://www.hotmail.com");
                  
                  user=driver.findElement(By.id("i0116"));
                  user.sendKeys(username);
        
                  Thread.sleep(3000);
        
                  btn1=driver.findElement(By.className("btn-primary"));
                  btn1.click();
        
                  Thread.sleep(3000);
        
                  pass=driver.findElement(By.id("i0118"));
                  pass.sendKeys(password);
        
                  Thread.sleep(3000);
        
                  btn2=driver.findElement(By.id("idSIButton9"));
                  btn2.click();
                  
          }
          catch(Exception ex)
          {
              
          }
      }
    }   
}
