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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
@NamedQueries({
@NamedQuery(name = "findCommentsForOffer", query = "SELECT c FROM Comment c WHERE c.offer = :offerID  ORDER BY c.id DESC")
})
public class Comment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8296981940027596707L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "comment_id", unique = true, nullable = false)
	private int id;
	@Column(name = "message", unique = false, nullable = false)
	private String message;
	@ManyToOne
	@JoinColumn(nullable = false)
	public Offer offer;
	@OneToOne(optional = false)
	public Buyer buyer;

	public Comment() {
		super();
	}

	public Comment(String message, Offer offer, Buyer buyer) {
		super();
		this.message = message;
		this.offer = offer;
		this.buyer = buyer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

}
