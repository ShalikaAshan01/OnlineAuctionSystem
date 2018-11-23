/**
 * 
 */
package service;

import java.util.logging.Logger;
import model.PersonModel;

/**
 * @author Shalika Ashan
 *
 */
public interface PersonService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(PersonService.class.getName());
	/**
	 * search tuple using email and password
	 * @param username
	 * @param password
	 * @return
	 */
	public PersonModel login(String username,String password) ;
	
	/**
	 * insert 
	 * @param per
	 * @return
	 */
	public Integer SignUP(PersonModel per);
}