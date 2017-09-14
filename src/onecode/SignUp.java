/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onecode;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author Bipin
 */
public class SignUp implements ActionListener{
     JLabel title,pass1label,fname,lname,email,fingerPrint;
    JTextField fnametxt,lnametxt,emailtxt;
    JPasswordField pass1txt,fingerPrinttxt;
    JButton signup;
    JFrame f;
    JPanel p;
    
    String msg;
    
    Connection con;
    PreparedStatement pst;
    
    public SignUp()
    {
        f=new JFrame("Sign Up");
        f.setSize(500,450);
        
        ImageIcon imgg=new ImageIcon("C:\\Users\\Bipin\\Downloads\\OneCode (1)\\OneCode\\Images\\onecode.jpg");
        f.setIconImage(imgg.getImage());
        
        p=new JPanel();
        
        title=new JLabel("-- Sign Up --");
      
        pass1label=new JLabel("Master Key");
        fname=new JLabel("First Name");
        lname=new JLabel("Last Name");
        email=new JLabel("Email-id");
        fingerPrint=new JLabel("FingerPrint");
                
       
        fnametxt=new JTextField();
        lnametxt=new JTextField();
        emailtxt=new JTextField();
        
        pass1txt=new JPasswordField();
        fingerPrinttxt=new JPasswordField();
        
        signup=new JButton("Sign Up");               
               
        f.add(p);
        p.setBackground(new Color(109,110,115));
        
        p.setLayout(null);
        
        p.add(title);
        title.setBounds(190,50,200,40);
        title.setFont(new Font("Times New Roman",Font.BOLD,17));
        title.setForeground(Color.white);
        
      
        p.add(fname);
        fname.setBounds(70,135,150,25); 
          fname.setFont(new Font("Times New Roman",Font.BOLD,17));
       fname.setForeground(Color.white);
        p.add(fnametxt);
        fnametxt.setBounds(200,135,250,25);
        
       
        p.add(lname);
        lname.setBounds(70,170,150,25); 
          lname.setFont(new Font("Times New Roman",Font.BOLD,17));
       lname.setForeground(Color.white);
        p.add(lnametxt);
        lnametxt.setBounds(200,170,250,25);
        
        p.add(email);
        email.setBounds(70,205,150,25);        
        email.setFont(new Font("Times New Roman",Font.BOLD,17));
       email.setForeground(Color.white);
        p.add(emailtxt);
        emailtxt.setBounds(200,205,250,25);
        
        p.add(fingerPrint);
        fingerPrint.setBounds(70,240,150,25);        
        fingerPrint.setFont(new Font("Times New Roman",Font.BOLD,17));
        fingerPrint.setForeground(Color.white);
        p.add(fingerPrinttxt);
        fingerPrinttxt.setBounds(200,240,250,25);
        
        p.add(pass1label);
        pass1label.setBounds(70,275,150,25);        
        pass1label.setFont(new Font("Times New Roman",Font.BOLD,17));
        pass1label.setForeground(Color.white);
        p.add(pass1txt);
        pass1txt.setBounds(200,275,250,25);
        
        
        
        
        p.add(signup);
        signup.setBounds(200,310,250,25);  
        signup.addActionListener(this);       
        
        
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setSize(500,500);
                
    }
    
    
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==signup)
        {
           
            FingerPrintKey fp=new FingerPrintKey();
          
            AESEncryption aes;
             String key=null;
             String encryptKeym=null;
          
                try
                {
                     key=fp.getFpKey(fingerPrinttxt.getText());// fp encrypted key
                    aes=new AESEncryption(key);
                    encryptKeym=aes.encryt(pass1label.getText()); // masterkey encrypted
                }
                catch(Exception ex){}
          
        
            try
            {
                ConnectionDB con=new ConnectionDB();
    
                pst=con.con.prepareStatement("insert into one_code_user (Fname,Lname,Email,FingerPrint,MasterKey) values(?,?,?,?,?)");
               
                pst.setString(1, fnametxt.getText());
                pst.setString(2,lnametxt.getText());
                pst.setString(3, emailtxt.getText());
                pst.setString(4,key);
                pst.setString(5, encryptKeym);
                    
                int i= pst.executeUpdate();
                    
                if(i>0)
                {
                    JOptionPane.showMessageDialog(null,"User Added Successfully");
                   fingerPrinttxt.setText("");
                    pass1txt.setText("");
                    fnametxt.setText("");
                    lnametxt.setText("");
                    emailtxt.setText("");                        
                }                   
   
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,ex);
            }

        }
    }

    
}
