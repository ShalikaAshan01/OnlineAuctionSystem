/**
 * 
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Shalika Ashan
 *
 */
public class DBConnectionUtil {

	private final static String hostName = "localhost";
	private final static String dbName = "onlineauctionsystem";
	private final static String userName = "root";
	private final static String password = "";
	
	private static Connection connection;

	// This works according to singleton pattern
	private DBConnectionUtil() {
	}

	/**
	 * Create Database connection for the given URL, Username and Password
	 * 
	 * @return Connection this returns SQL connection for MySql Database
	 * 
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name
	 * @throws SQLException
	 *             - An exception that provides information on a database access
	 *             error or other errors
	 */
	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		
		if(connection == null || connection.isClosed()) {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
			connection = DriverManager.getConnection(connectionURL, userName,
		             password);
		}
	    return connection;
	}
}