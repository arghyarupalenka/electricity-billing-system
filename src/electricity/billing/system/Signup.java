package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class Signup extends JFrame implements ActionListener {
	JButton create,back;
	Choice accountType;
	JTextField meter,username,name,password;
	Signup(){
 		
		setBounds(450,150,700,400);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel panel=new JPanel();
		panel.setBounds(30, 30, 650, 300);
		panel.setBorder(new TitledBorder(new LineBorder(Color.ORANGE),"Create-Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(173,215,230)));
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		panel.setForeground(new Color(34,139,34));
		add(panel);
		
		JLabel heading=new JLabel("Create account");
		heading.setBounds(100, 50, 140, 20);
		heading.setForeground(Color.GRAY);
		heading.setFont(new Font("Tahoma",Font.BOLD,14));
		panel.add(heading);
		
		accountType=new Choice();
		accountType.add("Admin");
		accountType.add("Customer");
		accountType.setBounds(260,50,150,20);
		panel.add(accountType);
		
		JLabel lblmeter=new JLabel("Meter Number");
		lblmeter.setBounds(100, 90, 140, 22);
		lblmeter.setForeground(Color.GRAY);
		lblmeter.setFont(new Font("Tahoma",Font.BOLD,14));
		lblmeter.setVisible(false);
		panel.add(lblmeter);
		
		meter=new JTextField();
		meter.setBounds(260, 90, 150, 22);
		meter.setVisible(false);
		panel.add(meter);
		
		JLabel lblusername=new JLabel("User name");
		lblusername.setBounds(100, 130, 140, 22);
		lblusername.setForeground(Color.GRAY);
		lblusername.setFont(new Font("Tahoma",Font.BOLD,14));
		panel.add(lblusername);
		
		username=new JTextField();
		username.setBounds(260, 130, 150, 22);
		panel.add(username);
		
		JLabel lblname=new JLabel("Name");
		lblname.setBounds(100, 170, 140, 22);
		lblname.setForeground(Color.GRAY);
		lblname.setFont(new Font("Tahoma",Font.BOLD,14));
		panel.add(lblname);
		
		name=new JTextField();
		name.setBounds(260, 170, 150, 22);
		panel.add(name);
		
        meter.addFocusListener(new FocusListener() {
			
			@Override
			public void focusGained(FocusEvent fe) {
				
			}
			@Override
			public void focusLost(FocusEvent fe) {
				try {
					Conn c=new Conn();
					ResultSet rs=c.s.executeQuery("select * from login where meter_no='"+meter.getText()+"'");
					while(rs.next()) {
						name.setText(rs.getString("name"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		JLabel lblPassword=new JLabel("Password");
		lblPassword.setBounds(100, 210, 140, 22);
		lblPassword.setForeground(Color.GRAY);
		lblPassword.setFont(new Font("Tahoma",Font.BOLD,14));
		panel.add(lblPassword);
		
		password=new JTextField();
		password.setBounds(260, 210, 150, 22); 
		panel.add(password);
		
		accountType.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent ae) {
					String user=accountType.getSelectedItem();
					if(user.equals("Customer")) {
						lblmeter.setVisible(true);
						meter.setVisible(true);
						name.setEditable(false);
					}else {
						lblmeter.setVisible(false);
						meter.setVisible(false);
						name.setEditable(true);
					}
			}
			
		});
		
		create=new JButton("Create");
		create.setBounds(140, 260, 120, 22);
		create.addActionListener(this);
		panel.add(create);
		
		back=new JButton("Back");
		back.setBounds(300, 260, 120, 22);
		back.addActionListener(this);
		panel.add(back);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("image/icon/signupImage.png"));
		Image i2=i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel image=new JLabel(i3);
		image.setBounds(415, 30, 250, 250);
		panel.add(image);
		
		setVisible(true);
		
		
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==create) {
			String atype=accountType.getSelectedItem();
			String susername=username.getText();
			String sname=name.getText();
			String spassword=password.getText();
			String smeter=meter.getText();
			
			try {
				Conn c=new Conn();
				String query=null;
				if(atype.equals("Admin")) {
				  query="insert into login values('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+atype+"')";
				}else {
					query="update login set username='"+susername+"',password='"+spassword+"',user='"+atype+"' where meter_no='"+smeter+"'";
				}
				  c.s.executeUpdate(query);
				JOptionPane.showMessageDialog(null,"Account created succesfully");
				
				setVisible(false);
				new Login_page();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			}else if(ae.getSource()==back) {
			setVisible(false);
			new Login_page();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Signup();
	}

}
