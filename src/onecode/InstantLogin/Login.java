/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onecode.InstantLogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 *
 * @author Bipin
 */
public interface Login {
      public static WebDriver driver = new FirefoxDriver();
    
    public void login(String username,String password,int i) throws InterruptedException; 
    
}
