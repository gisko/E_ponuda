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
@Table(name = "payment")
@NamedQuery(name = "findPaymentsForBuyer", query = "SELECT p FROM Payment p WHERE p.buyer = :buyerID")
public class Payment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2372597259875557528L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "payment_id", unique = true, nullable = false)
	private Integer id;
	@Column(name = "price", unique = false, nullable = false)
	private double price;
	@Column(name = "paymentsMade", unique = false, nullable = false)
	private boolean paymentsMade;
	@Column(name = "canceled", unique = false, nullable = false)
	private boolean canceled;
	@ManyToOne
	@JoinColumn(nullable = false)
	public Buyer buyer;
	@ManyToOne
	@JoinColumn(nullable = false)
	public Offer offer;

	public Payment() {
		super();
	}
	
	

	public Payment( double price, boolean paymentsMade,
			boolean canceled, Buyer buyer, Offer offer) {
		super();
		this.price = price;
		this.paymentsMade = paymentsMade;
		this.canceled = canceled;
		this.buyer = buyer;
		this.offer = offer;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isPaymentsMade() {
		return paymentsMade;
	}

	public void setPaymentsMade(boolean paymentsMade) {
		this.paymentsMade = paymentsMade;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

}
