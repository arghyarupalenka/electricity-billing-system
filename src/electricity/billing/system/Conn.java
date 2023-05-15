package electricity.billing.system;

import java.sql.*;

public class Conn {
		
	Connection c;
	
	Statement s;
	
		Conn() throws ClassNotFoundException, SQLException{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try {
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs","root","aaru332");
			s=c.createStatement();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
