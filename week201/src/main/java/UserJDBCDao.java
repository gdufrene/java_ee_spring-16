import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class UserJDBCDao implements UserDao {
	
	DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public User find(String email) {
		User user = null;
		
		// TODO : complete query
		String query = "SELECT * FROM ...";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ResultSet rs = conn.createStatement().executeQuery( query );
			
			// TODO : use resultSet, map to User;
			user = new User();
			
			rs.close();
		} catch ( SQLException e ) {
			throw new Error("Unable to find User " + email, e);
		} finally {
			try {
				if ( conn != null ) conn.close();
			} catch (SQLException e) { }
		}
		
		return user;
	}

	public void delete(String email) {
		// TODO : complete query
		String query = "DELETE FROM ...";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.createStatement().executeUpdate(query);
		} catch(SQLException e) {
			throw new Error("Unable to delete User " + email, e);
		} finally {
			try {
				if ( conn != null ) conn.close();
			} catch (SQLException e) { }
		}
	}

	public void create(User user) {
		// TODO : complete query
		String query = "INSERT INTO ...";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.createStatement().executeUpdate(query);
		} catch(SQLException e) {
			throw new Error("Unable to insert User " + user, e);
		} finally {
			try {
				if ( conn != null ) conn.close();
			} catch (SQLException e) { }
		}
	}

	public void update(User user) {
		// TODO : complete query
		String query = "UPDATE ... SET ...";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.createStatement().executeUpdate(query);
		} catch(SQLException e) {
			throw new Error("Unable to insert User " + user, e);
		} finally {
			try {
				if ( conn != null ) conn.close();
			} catch (SQLException e) { }
		}
	}


	public boolean checkPassword(String email, String password) {
		// TODO : complete query
		String query = "SELECT ... WHERE ...";
		
		try {
			Connection conn = dataSource.getConnection();
			ResultSet rs = conn.createStatement().executeQuery(query);
			boolean exists = rs.next();
			conn.close();
			return exists;
		} catch(SQLException e) {
			throw new Error("Unable to identified User " + email, e);
		}
	}
}
