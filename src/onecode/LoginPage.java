/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onecode;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author Bipin
 */
public class LoginPage implements ActionListener{
    String fname=null;
    String lname=null;
    int uid=0;
    
    JFrame f;
    JTextArea intro;
    JLabel userlabel,passlabel,panel2,signuplabel,image;
    JTextField usertext;
    JPasswordField passtext;
    JButton btn;
    JPanel p1,p2;
  
    PreparedStatement pst;
    ResultSet rs;
        
    public LoginPage() 
    {
        f=new JFrame("One Code");
        f.setSize(1020,440);
        
        ImageIcon imgg=new ImageIcon("D:\\Project\\ONECode\\assets\\image\\onecode.jpg");
        f.setIconImage(imgg.getImage());
    
        intro=new JTextArea();        
        
        userlabel=new JLabel("Email");
        passlabel=new JLabel("Master Key");
        panel2=new JLabel("--Login--");
        signuplabel=new JLabel("Don't have an account?? Sign Up");
        image=new JLabel();
        
        usertext=new JTextField();
        passtext=new JPasswordField();
        
        btn=new JButton("Login");        
                
        p1=new JPanel();
        p2=new JPanel();        
      
        ImageIcon img = new ImageIcon(new ImageIcon("D:\\Project\\ONECode\\assets\\image\\onecode.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        
        f.setLayout(null);
        
        //Panel 1
        f.add(p1);     
        p1.setBounds(5, 5, 500, 400);
        p1.setBackground(new Color(109,110,115));
        
        p1.setLayout(null);
        
        p1.add(image);
        image.setBounds(150,100,200,200);
        image.setIcon(img);
        
        
        //End of Panel 1
        
        
        //Panel For Login Form
        f.add(p2); 
        p2.setBounds(510, 5, 500, 400);
        p2.setBackground(new Color(109,110,115));
       
        p2.setLayout(null);
        
        p2.add(panel2);
        panel2.setBounds(230,100,100,40);
        panel2.setFont(new Font("Sans Serif", Font.BOLD, 20));
        panel2.setForeground(Color.white);
        
        
        p2.add(userlabel);
        userlabel.setBounds(90,150,100,25); 
        userlabel.setFont(new Font("Times New Roman",Font.BOLD,17));
        userlabel.setForeground(Color.white);
        p2.add(usertext);
        usertext.setBounds(190,150,200,25);
        
        p2.add(passlabel);
        
       passlabel.setBounds(90,185,100,25);        
       passlabel.setFont(new Font("Times New Roman",Font.BOLD,17));
       passlabel.setForeground(Color.white);
       p2.add(passtext);
        passtext.setBounds(190,185,200,25);
        
        p2.add(btn);
        btn.setBounds(190,230,200,50);
        btn.addActionListener(this);
        
        p2.add(signuplabel);
        signuplabel.setBounds(255,295,200,25);
        signuplabel.setFont(new Font("Times New Roman", Font.PLAIN,15));
        signuplabel.setForeground(Color.white);
        signuplabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signuplabel.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) 
             {
                    new SignUp();
             }
        });
               
                
        f.setVisible(true);
    
        f.setResizable(false);  
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    f.setLocationRelativeTo(null);
    }
    
    public static void main(String[] args)
    {        
        new LoginPage();  
        
    }       

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==btn)
        {
           String email=usertext.getText();
            String pass=passtext.getText().toString();
            FingerPrintKey fp=new FingerPrintKey();
            
         
            AESEncryption aes;
             String encryptKey=null;
             String fpkey=null;
                try
                {
                    fpkey=fp.getFpKey(pass);
                    
                
                ConnectionDB con= new ConnectionDB();
                
                pst=con.con.prepareStatement("select * from one_code_user where Email=? AND FingerPrint=?");
                
                pst.setString(1, email);
                pst.setString(2, fpkey);
                
                                
                rs=pst.executeQuery();
                
                
                if(rs.next())
                {
                    fname=rs.getString("fname");
                    lname=rs.getString("lname");                       
                    uid=rs.getInt("Id");      
                    Container.MFp=fpkey;
                    Container.MKey=rs.getString("MasterKey");
                    
                    f.dispose();
                    
                    new MainPage(fname,lname,fpkey,uid);                  
                }
                else
                {
                    JOptionPane.showMessageDialog(null," Username or Password not correct");
                    
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    
}
