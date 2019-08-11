package banco;

import java.sql.*;

class ConnFactory {
	
	public Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "root", "888555333");
			
			return con;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void runSQL() {
		
	}
}