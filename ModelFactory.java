package datebase;

import java.sql.SQLException;

public class ModelFactory {
	
	/**
	 * ����Ա��½
	 * @param name
	 * @param password
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static administrators login(String name,String password) throws ClassNotFoundException, SQLException{
		administrators admin = null;
		admin=administrators.login(name, password);
		return admin;
	}
	
	/**
	 * ���γɹ��������ݿ�д������
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void succeed(administrators admin,String name,String password,String course) throws ClassNotFoundException, SQLException{
		admin.succeed(admin.getName(), name, password, course);
	}
}
