package entity;


import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Buyer extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1341701297529884130L;
	@Column(name = "phoneNumber", unique = false, nullable = true)
	private String phoneNumber;
	@Column(name = "address", unique = false, nullable = true)
	private String address;
	@Column(name = "email", unique = false, nullable = true)
	private String email;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY,mappedBy = "buyer")
	public Set<Payment> payment = new HashSet<Payment>();
	@OneToMany(cascade = { ALL }, fetch = LAZY,mappedBy = "buyer")
	public Set<Coupons> coupons = new HashSet<Coupons>();
	@OneToMany(cascade = CascadeType.ALL,fetch =FetchType.LAZY,mappedBy = "buyer")
	public Set<Comment> comment = new HashSet<Comment>();
	
	public Buyer() {
		super();
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber1(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress1(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail1(String email) {
		this.email = email;
	}



	public Set<Payment> getPayment() {
		return payment;
	}


	public void setPayment(Set<Payment> payment) {
		this.payment = payment;
	}


	public Set<Coupons> getCoupons() {
		return coupons;
	}


	public void setCoupons(Set<Coupons> coupons) {
		this.coupons = coupons;
	}


	public Set<Comment> getComment() {
		return comment;
	}


	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Buyer(String firstName, String lastName, String username,
			String password,String phoneNumber, String address, String email){
		super(firstName, lastName, username, password);
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
	}




	

	
}
