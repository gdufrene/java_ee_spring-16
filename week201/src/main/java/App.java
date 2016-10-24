
import java.sql.Connection;
import java.sql.SQLException;

import org.sqlite.SQLiteDataSource;

public class App {

	public static void main(String[] args) throws SQLException {
		System.out.println("___ App ___");
		
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:data.db");
		
		Connection conn = ds.getConnection();
		
		// TODO : select all users.
		// ...
		
		conn.close();
	}
}
