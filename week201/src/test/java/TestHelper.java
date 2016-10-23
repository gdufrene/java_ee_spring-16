import javax.sql.DataSource;

public class TestHelper {
	
	public static DataSource getDataSource() {
		DataSource ds = null;
		/*
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:data.db");
		*/
		return ds;
	}
	
	public static UserDao createUserDao() {
		UserJDBCDao dao = new UserJDBCDao();
		dao.setDataSource( getDataSource() );
		return dao;
	};
	
}
