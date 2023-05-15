package electricity.billing.system;

import java.awt.*; 
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

//import java.sql.*;

@SuppressWarnings("serial")
public class Login_page extends JFrame implements ActionListener{
	JButton Login,Cancel,Signup;
	JTextField username,password;
	Choice loginin;
		Login_page(){
			super("Login page");
			getContentPane().setBackground(Color.WHITE);
			setLayout(null);
			
			JLabel lblusername=new JLabel("Username");
			lblusername.setBounds(300, 20, 100, 20);
			add(lblusername);
			
			username=new JTextField();
			username.setBounds(400, 20, 150, 25);
			add(username);
			
			JLabel lblpassword=new JLabel("Password");
			lblpassword.setBounds(300, 60, 100, 20);
			add(lblpassword);
			
			password=new JTextField();
			password.setBounds(400, 60, 150, 25);
			add(password);
			
			JLabel lbllogininas=new JLabel("Login in as");
			lbllogininas.setBounds(300, 100, 100, 20);
			add(lbllogininas);
			
			loginin=new Choice();
			loginin.add("Admin");
			loginin.add("Customer");
			loginin.setBounds(400, 100, 150, 25);
			add(loginin);
			
			Login=new JButton("Login");
			Login.setBounds(330, 160,100, 20);
			Login.addActionListener(this);
			add(Login);
			
			
			Cancel=new JButton("Cancel");
			Cancel.setBounds(450, 160,100, 20);
			Cancel.addActionListener(this);
			add(Cancel);
		
			Signup=new JButton("Sign up");
			Signup.setBounds(380, 200,100, 20);
			Signup.addActionListener(this);
			add(Signup);
			
			ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("image/icon/second.jpg"));
			Image i2=i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
			ImageIcon i3=new ImageIcon(i2);
			JLabel image=new JLabel(i3);
			image.setBounds(0, 0, 250, 250);
			add(image);
			
			setSize(640,300);
			setLocation(400,200);
			setVisible(true);
			
		}
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource()==Login) {
				String susername=username.getText();
				String spassword=password.getText();
				@SuppressWarnings("unused")
				String user=loginin.getSelectedItem();
				
				
				try {
					Conn c=new Conn();
					
					String query= "Select * from login where username='"+susername+"'and password='"+spassword+"'and user='"+user+"'";
					
					ResultSet rs=c.s.executeQuery(query);
					String pass="";
					String uname="";
					 while(rs.next())
					 {
						 uname=rs.getString("username");
						 pass=rs.getString("password");
						 if(susername.equals(uname) && spassword.equals(pass)) {
								String meter=rs.getString("meter_no");
								setVisible(false);
								new Project(user,meter);
							}else {
								JOptionPane.showMessageDialog(null, "Invalid login");
								username.setText("");
								password.setText("");
							}
					 }
					 
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}else if(ae.getSource().equals(Cancel)) {
				setVisible(false);
			}else if(ae.getSource().equals(Signup)) {
				setVisible(false);
				
				new Signup();
			}
		}
	public static void main(String[] args) {
		// 
		 new Login_page();	
	}

}
