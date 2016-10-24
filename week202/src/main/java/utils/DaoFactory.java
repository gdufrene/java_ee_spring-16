package utils;

import java.sql.SQLException;

import org.sqlite.SQLiteDataSource;

import user.dao.UserDao;
import user.dao.UserJDBCDao;

public class DaoFactory {
	
	private static UserDao userDao; 

	public static UserDao createUserDao() throws SQLException {
		if ( userDao != null ) return userDao;
		
		UserJDBCDao dao = new UserJDBCDao();
		SQLiteDataSource dataSource = new SQLiteDataSource();
		dataSource.setUrl("jdbc:sqlite:data.db");
		dao.setConnection(dataSource.getConnection());
		return dao;
	}
}
