import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class TestHelper {
	
	private static Connection connection;
	
	public static UserDao createUserDao() throws SQLException {
		UserJDBCDao dao = new UserJDBCDao();
		dao.setConnection( getConnection() );
		return dao;
	}

	public static Connection getConnection() throws SQLException {
		if ( connection != null ) return connection;
		DataSource ds = null;
		/*
		SQLiteDataSource sqLiteSource = new SQLiteDataSource();
		sqLiteSource.setUrl("jdbc:sqlite:data.db");
		sqLiteSource.setLockingMode("NORMAL");
		*/
		
		return ( connection = ds.getConnection() );
	};
	
}
