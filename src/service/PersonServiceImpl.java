/**
 * 
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.AdministratorAccount;
import model.PersonModel;
import util.DBConnectionUtil;

/**
 * @author Shalika Ashan
 *
 */
public class PersonServiceImpl implements PersonService {
	
	private static Connection conn;
	private static PreparedStatement preparedstatement; 
	private String query;
	/**initialize the logger**/
	public static final Logger log = Logger.getLogger(PersonService.class.getName());
	
	public PersonServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public PersonModel login(String username,String password) {

		try {
			conn = DBConnectionUtil.getDBConnection();
			query = "SELECT * FROM customer WHERE email=? AND password=?";
			PreparedStatement preparedstatement = conn.prepareStatement(query);
			preparedstatement.setString(1, username);
			preparedstatement.setString(2, password);
			ResultSet result = preparedstatement.executeQuery();
			
			if(result.next()) {
				PersonModel person = new PersonModel();
				person.setCid(result.getInt(1));
				person.setFname(result.getString(2));
				person.setLname(result.getString(3));
				person.setEmail(result.getString(4));
				person.setAddrl1(result.getString(5));
				person.setAddrl2(result.getString(6));
				person.setAddrl3(result.getString(7));
				person.setPhone(result.getString(8));
				person.setDob(result.getString(9));
				person.setGender(result.getString(10));
				person.setPassword(result.getString(11));
				
				return person;
			}
			
		}catch (SQLException| ClassNotFoundException| NullPointerException e) {
			//handle exception
			System.out.println(e.getMessage());
			System.out.println("Somthing was going wrong");
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and
		 * database connection at the end
		 */
		finally {
			try {
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e){
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		return null;
	}
	@Override
	public Integer SignUP(PersonModel per) {
		int result = 0;
		try {
			conn = DBConnectionUtil.getDBConnection();
			String query = "INSERT INTO customer(cfname, clname, email, addrL1, addrL2, addrL3, phone, DoB, gender, password)"
							+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, per.getFname());
			preparedStatement.setString(2, per.getLname());
			preparedStatement.setString(3, per.getEmail());
			preparedStatement.setString(4, per.getAddrl1());
			preparedStatement.setString(5, per.getAddrl2());
			preparedStatement.setString(6, per.getAddrl3());
			preparedStatement.setString(7, per.getPhone());
			preparedStatement.setString(8, per.getDob());
			preparedStatement.setString(9, per.getGender());
			preparedStatement.setString(10, per.getPassword());
			result = preparedStatement.executeUpdate();
		
		}catch (SQLException | ClassNotFoundException e) {
			System.out.println("Somthing going to wrong");
			log.log(Level.SEVERE, e.getMessage());
		}
		/**
		 * close the prepared statement and
		 * database connection at the end
		 */
		
		finally {
			try {
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e){
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return result;
	}
	public static AdministratorAccount findUser(Connection conn, String userName, String password) throws SQLException {
		
		String sql = "SELECT t.aId, t.USER_NAME, t.PASSWORD FROM administrator_account t WHERE t.USER_NAME = ? AND t.PASSWORD = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();
		
		if(rs.next()) {
			int aId = rs.getInt("aId");
			AdministratorAccount user = new AdministratorAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setaId(aId);
			return user;
		}
		return null;
	}

}
