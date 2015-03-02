package entity;


import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 562119373377827570L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "category_id", unique = true, nullable = false)
	private int id;
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	@Column(name = "description", unique = false, nullable = false)
	private String description;
	@OneToMany(cascade = { ALL }, fetch = FetchType.LAZY, mappedBy = "category")
	protected Set<Offer> offer = new HashSet<Offer>();

	public Category() {
		super();
		offer = new HashSet<Offer>();
	}

	public Category(String name, String description) {
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
