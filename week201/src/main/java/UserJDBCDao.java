import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserJDBCDao implements UserDao {
	
	Connection conn;
	
	public void setConnection(Connection connection) {
		this.conn = connection;
	}

	public User find(String email) {
		User user = null;
		
		// TODO : complete query
		String query = "SELECT * FROM ...";
		
		Connection conn = null;
		try {
			ResultSet rs = conn.createStatement().executeQuery( query );
			
			// TODO : use resultSet, map to User;
			user = new User();
			
			rs.close();
		} catch ( SQLException e ) {
			throw new Error("Unable to find User " + email, e);
		}
		
		return user;
	}

	public void delete(String email) {
		// TODO : complete query
		String query = "DELETE FROM ...";
		
		try {
			conn.createStatement().executeUpdate(query);
		} catch(SQLException e) {
			throw new Error("Unable to delete User " + email, e);
		}
	}

	public void create(User user) {
		// TODO : complete query
		String query = "INSERT INTO ...";
		
		try {
			conn.createStatement().executeUpdate(query);
		} catch(SQLException e) {
			throw new Error("Unable to insert User " + user, e);
		}
	}

	public void update(User user) {
		// TODO : complete query
		String query = "UPDATE ... SET ...";
		
		try {
			conn.createStatement().executeUpdate(query);
		} catch(SQLException e) {
			throw new Error("Unable to insert User " + user, e);
		}
	}


	public boolean checkPassword(String email, String password) {
		// TODO : complete query
		String query = "SELECT ... WHERE ...";
		
		try {
			ResultSet rs = conn.createStatement().executeQuery(query);
			boolean exists = rs.next();
			rs.close();
			return exists;
		} catch(SQLException e) {
			throw new Error("Unable to identified User " + email, e);
		}
	}
}
