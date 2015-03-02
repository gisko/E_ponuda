package entity;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAdmin", query = "SELECT u FROM User u WHERE u.username like :username AND u.password LIKE :password AND u.DTYPE='Admin'")
public class Admin extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3148238141421474458L;

	public Admin() {
		super();
	}

	public Admin(String firstName, String lastName, String username,
			String password) {
		super(firstName, lastName, username, password);
	}
	

}
