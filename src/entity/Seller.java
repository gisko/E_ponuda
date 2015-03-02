package entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({
@NamedQuery(name = "findSeller", query = "SELECT u FROM User u WHERE u.username like :username AND u.password LIKE :password AND u.DTYPE='Seller'"),

})
public class Seller extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8514230535872458826L;
	@Column(name = "phoneNumber", unique = false, nullable = true)
	private String phoneNumber;
	@Column(name = "email", unique = false, nullable = true)
	private String email;
	@OneToMany(mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<Branch> branch = new HashSet<Branch>();
	@OneToMany(mappedBy = "manager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<Offer> offer = new HashSet<Offer>();

	public Seller() {
		super();
	}

	public Seller(String firstName, String lastName, String username, String password, String phoneNumber, String email) {
		super(firstName, lastName, username, password);
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Branch> getBranch() {
		return branch;
	}

	public void setBranch(Set<Branch> branch) {
		this.branch = branch;
	}

	public Set<Offer> getOffer() {
		return offer;
	}

	public void setOffer(Set<Offer> offer) {
		this.offer = offer;
	}
	
	public boolean getDelete(){
		return offer.isEmpty();
	}

}
