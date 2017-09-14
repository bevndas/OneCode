/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onecode;
import onecode.AESEncryption;
import onecode.ConnectionDB;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;

/**
 *
 * @author Bipin
 */
public class AccountSave implements ActionListener {
     JLabel ftitle,url,pass1label,username,title;
    JTextField titletext,urltxt,passtxt,emailtxt,usertxt;
    JPasswordField pass1txt;
    JButton signup,cancel;
    JFrame f;
    JPanel p;
    
    PreparedStatement pst;
    
    String key=null;
    int uid=0;
    
    String encryptPass=null;
    
    public AccountSave(String key,int uid)
    {
        f=new JFrame("Sign Up");
        f.setSize(500,420);
        
        ImageIcon imgg=new ImageIcon("C:\\Users\\Bipin\\Downloads\\OneCode (1)\\OneCode\\Images\\onecode.jpg");
        f.setIconImage(imgg.getImage());
        
        p=new JPanel();
        
        ftitle=new JLabel("-- Add Account --");
        
        title=new JLabel("Title");
        url=new JLabel("URL");
        
        username=new JLabel("Email-Id");
        pass1label=new JLabel("Password");
        
         titletext=new JTextField();
          urltxt=new JTextField();
        usertxt=new JTextField();
                
        pass1txt=new JPasswordField();
      
        
        signup=new JButton("Sign Up");               
        cancel=new JButton("Cancel");               
               
        f.add(p);
        p.setBackground(new Color(109,110,115));
        
        p.setLayout(null);
        
        p.add(title);
        title.setBounds(120,50,300,40);
        title.setFont(new Font("Times New Roman",Font.BOLD,17));
        title.setForeground(Color.white);
        
         p.add(title);
        title.setBounds(70,100,150,25);
        title.setFont(new Font("Times New Roman",Font.BOLD,17));
        title.setForeground(Color.white);        
        p.add(titletext);
        titletext.setBounds(200,100,250,25);
        
        
        
        p.add(url);
        url.setBounds(70,135,150,25);  
        url.setFont(new Font("Times New Roman",Font.BOLD,17));
        url.setForeground(Color.white);
        p.add(urltxt);
        urltxt.setBounds(200,135,250,25);
        
        p.add(username);
        username.setBounds(70,170,150,25);  
        username.setFont(new Font("Times New Roman",Font.BOLD,17));
        username.setForeground(Color.white);
        p.add(usertxt);
        usertxt.setBounds(200,170,250,25);
     
           p.add(pass1label);
        pass1label.setBounds(70,205,150,25);  
        pass1label.setFont(new Font("Times New Roman",Font.BOLD,17));
        pass1label.setForeground(Color.white);
        p.add(pass1txt);
        pass1txt.setBounds(200,205,250,25);
     
        
        
        p.add(signup);
        signup.setBounds(70,240,200,25); 
        signup.addActionListener(this);
        
        p.add(cancel);
        cancel.setBounds(270,240,200,25);      
        
        this.key=key;
        this.uid=uid;
        
        f.setVisible(true);
        f.setLocation(450,110);
        f.setResizable(false);        
    }
    
   
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==signup)
        {
            String pass1=pass1txt.getText().toString();
            
            
            String email=usertxt.getText();
            
                try
                {
                    AESEncryption aes=new AESEncryption(key);
                    
                    encryptPass=aes.encryt(pass1);
                    
                    ConnectionDB con=new ConnectionDB();
                    System.out.println(""+titletext.getText());
                    
                    pst=con.con.prepareStatement("insert into "+titletext.getText()+" (Title,URL,Username,Password,UserId) values(?,?,?,?,?)");
                    pst.setString(1,titletext.getText());
                    pst.setString(2, urltxt.getText());
                    pst.setString(3,usertxt.getText());
                    pst.setString(4,encryptPass);
                    pst.setInt(5, uid); 
                  
                   
                   
                    
                    int i= pst.executeUpdate();
                    
                    if(i>0)
                    {
                        JOptionPane.showMessageDialog(null,"Record Added Successfully");
                        titletext.setText("");
                        urltxt.setText("");
                        usertxt.setText("");
                        pass1txt.setText("");
                                                                      
                    }                   
                    
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,ex);
                }
           
            
        }
    }
    
    
}
