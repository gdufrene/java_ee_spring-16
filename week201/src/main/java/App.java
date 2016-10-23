
import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws SQLException {
		System.out.println("___ App ___");
		
		/*
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:data.db");
		*/
		
		UserJDBCDao dao = new UserJDBCDao();
		// dao.setConnection(ds.getConnection());
			
		User user = new User();
		// TODO : map user properties ...
		
		dao.create(user);
		
	}
}
