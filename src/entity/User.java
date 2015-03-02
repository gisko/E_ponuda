package entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "User")
@NamedQueries({
@NamedQuery(name = "findUser", query = "SELECT u FROM User u WHERE u.username like :username AND u.password LIKE :password"),
@NamedQuery(name = "findByType", query = "SELECT u FROM User u WHERE u.DTYPE LIKE :DTYPE")
})
public abstract class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9211980915905603039L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer id;
	@Column(name = "firstName", unique = false, nullable = false)
	private String firstName;
	@Column(name = "lastName", unique = false, nullable = false)
	private String lastName;
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	@Column(name = "password", unique = false, nullable = false)
	private String password;
	@Column(name = "DTYPE", unique = false, nullable = false)
	private String DTYPE;
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDTYPE() {
		return DTYPE;
	}

	public void setDTYPE(String myTYPE) {
		DTYPE = myTYPE;
	}

	public User() {
		super();
	}
	
	public User(String firstName, String lastName, String username,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

}
