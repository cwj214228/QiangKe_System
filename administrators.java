package datebase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


@SuppressWarnings("serial")
public class administrators implements Serializable{
	
	private String name;
	private String password;
	private String course;
	
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public administrators(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	@Override
	public String toString() {
		return "administrators [name=" + name + ", password=" + password + "]";
	}
	
	/**
	 * 管理员登陆
	 * @param name
	 * @param password
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static administrators login(String name,String password) throws SQLException, ClassNotFoundException{
		
		administrators admin=null;
		String sql = "select * from administrators where name='"+name+"' and password='"+password+"'";
		ResultSet rs = databaseConnect.getStat().executeQuery(sql);
		if(rs.next()) {
			admin = new administrators(rs.getString(1), rs.getString(2));
		}
		return admin;
	}
	
	/**
	 * 抢课成功，向数据库写入数据
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void succeed(String id,String name,String password,String course) throws ClassNotFoundException, SQLException{
		String sql = "insert into succeed (id,name,password,course) values('"+id+"','"+name+"','"+password+"','"+course+"')";
		databaseConnect.getStat().executeUpdate(sql);
		System.out.println("数据插入成功");
	}
}
