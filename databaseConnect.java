package datebase;

import java.sql.*;

/**
 * 
 * @author Administrator
 * DatabaseConnect: stat
 * 					init, getStat
 */

public class databaseConnect {

	private static Statement stat;
	private static PreparedStatement pstat;
	private static Connection con;
	
	public static void init() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://127.0.0.1:3306/Mysql?useUnicode=true&characterEncoding=UTF8";
		String user = "root";
		String password = "5201314";
		
		con = DriverManager.getConnection(url, user, password);
		con.createStatement().executeUpdate("use qiangkesystem");
		stat = con.createStatement();
	}
	
	public static Statement getStat() throws ClassNotFoundException, SQLException {
		
		if(stat == null) {
			init();
			stat = con.createStatement();
		}
			
		return stat;
		
	}
	
	public static PreparedStatement getStat(String sql) throws ClassNotFoundException, SQLException {
		
		if(con == null) {
			init();
		}
		pstat = con.prepareStatement(sql);
		return pstat;
	}
}
