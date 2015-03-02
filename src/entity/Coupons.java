package entity;


import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="coupons")
@NamedQuery(name = "findCoupounsForBuyer", query = "SELECT c FROM Coupons c WHERE c.buyer = :buyerID")
public class Coupons implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3504571305416436862L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "coupons_id", unique = true, nullable = false)
	private int id;
	@Column(name = "couponIdentifies", unique = true, nullable = false)
	private String couponIdentifies;
	@Column(name = "used", unique = false, nullable = false)
	private boolean used;
	@Column(name = "validTo", unique = false, nullable = false)
	private Date validTo;
	@Column(name = "active", unique = false, nullable = false)
	private boolean active;
	@ManyToOne 
	@JoinColumn(nullable = false)
	public Offer offer;
	@ManyToOne 
	@JoinColumn(nullable = false)
	public Buyer buyer;

	public Coupons() {
		super();
	}
	
	public Coupons( String couponIdentifies, boolean used, Date validTo,
			boolean active, Offer offer, Buyer buyer) {
		super();
		
		this.couponIdentifies = couponIdentifies;
		this.used = used;
		this.validTo = validTo;
		this.active = active;
		this.offer = offer;
		this.buyer = buyer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCouponIdentifies() {
		return couponIdentifies;
	}

	public void setCouponIdentifies(String couponIdentifies) {
		this.couponIdentifies = couponIdentifies;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
