/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onecode.InstantLogin;

/**
 *
 * @author Bipin
 */
public class LoginFactory {
      public Login getLogin(String socialMedia)
    {
        if(socialMedia.equalsIgnoreCase("facebook"))
        {
            return new FBLogin();
        }
        else if(socialMedia.equalsIgnoreCase("hotmail"))
        {
            return new HotMailLogin();
        }
        else
        {
            return null;
        }
    }
    
}
