package electricity.billing.system;

import java.awt.Image;

import javax.swing.*;

//@SuppressWarnings("serial") 
public class Splash extends JFrame implements Runnable {
	Thread t;

	Splash() {
		getContentPane();
		setLayout(null);
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/icon/elect.jpg"));
		Image i2 = i1.getImage().getScaledInstance(730, 650, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(50, 50, 780, 690);
		add(image);
		setVisible(true);
		int x = 1;
		for (int i = 2; i < 600; i += 4) {
			setSize(i + x, i);
			setLocation(700 - ((i + x) / 2), 400 - (i / 2));
//		setSize(730,550);
//		setLocation(400,150);
		}
		t = new Thread(this);
		t.start();
		setVisible(true);
	}

	public void run() {
		try {
			Thread.sleep(6000);
			setVisible(false);
//			 login frame
			new Login_page();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new Splash();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
