package entity;


import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="image")
@NamedQuery(name = "findImageForOffer", query = "SELECT i FROM Image i WHERE i.offer = :offID")
public class Image implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = -1999120711374872015L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "image_id", unique = true, nullable = false)
	private int id;
	@Column(name = "name", unique = false, nullable = false)
	private String location;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Offer offer;
	
	public Image(){
		super();
	}
	

	public Image(String location, Offer offer) {
		this.location = location;
		this.offer = offer;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	
}
