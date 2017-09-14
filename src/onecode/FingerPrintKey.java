/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onecode;
import java.io.FileInputStream;
import java.security.MessageDigest;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Bipin
 */
public class FingerPrintKey {
     public String getFpKey(String inputText) throws Exception 
    {
     MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    // calculating from the given file running its inside
    // while calculating the digest formula

    FileInputStream input = new FileInputStream(inputText);
    byte[] buffer = new byte[8192];
    int length;
    while( (length = input.read(buffer)) != -1 ) {
      messageDigest.update(buffer, 0, length);
    }
    byte[] raw = messageDigest.digest();

    //printout in 64 base
    BASE64Encoder encoder = new BASE64Encoder();
     String base64 = encoder.encode(raw);
    
    
    return base64;
    }
    
    
}
