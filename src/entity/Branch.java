package entity;


import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="branch")
@NamedQueries({
@NamedQuery(name = "findUnique", query = "SELECT b FROM Branch b WHERE b.name like :name AND b.address LIKE :address"),
@NamedQuery(name = "findBranchForSeller", query = "SELECT b FROM Branch b WHERE b.manager= :sellerID"),
})
public class Branch implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1982335417389582530L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "branch_id", unique = true, nullable = false)
	private int id;
	@Column(name = "name", unique = false, nullable = false)
	private String name;
	@Column(name = "phoneNumber", unique = false, nullable = false)
	private String phoneNumber;
	@Column(name = "address", unique = false, nullable = false)
	private String address;
	@ManyToOne
	@JoinColumn(nullable = false)
	public Seller manager;

	public Branch() {
		super();
	}
	

	public Branch(String name, String phoneNumber, String address,
			Seller manager) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.manager = manager;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Seller getManager() {
		return manager;
	}

	public void setManager(Seller manager) {
		this.manager = manager;
	}

}
