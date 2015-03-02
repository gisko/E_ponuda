package entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="offer")
@NamedQueries({
@NamedQuery(name = "findOffersPerCategory", query = "SELECT o FROM Offer o WHERE o.category = :catID AND o.active=true"),
@NamedQuery(name = "findActiveOffers", query = "SELECT o FROM Offer o WHERE o.active=true"),
@NamedQuery(name = "findActiveOffersForManager", query = "SELECT o FROM Offer o WHERE o.manager= :managerID AND o.active=true"),
@NamedQuery(name = "findOffersForManager", query = "SELECT o FROM Offer o WHERE o.manager= :managerID")
})
public class Offer implements Serializable
{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7708123953628246343L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "offer_id", unique = true, nullable = false)
	private int id;
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	@Column(name = "dateCreated", unique = false, nullable = false)
	private Date dateCreated;
	@Column(name = "expirationDate", unique = false, nullable = false)
	private Date expirationDate;
	@Column(name = "validFrom", unique = false, nullable = false)
	private Date validFrom;
	@Column(name = "validTo", unique = false, nullable = false)
	private Date validTo;
	@Column(name = "regularPrice", unique = false, nullable = false)
	private double regularPrice;
	@Column(name = "salePrice", unique = false, nullable = false)
	private double salePrice;
	@Column(name = "maxOffers", unique = false, nullable = false)
	private int maxOffers;
	@Column(name = "description", unique = false, nullable = false)
	private String description;
	@Column(name = "purchasedOffers", unique = false, nullable = false)
	private int purchasedOffers;
	@Column(name = "active", unique = false, nullable = false)
	private boolean active;
	@Column(name = "headImageID", unique = false, nullable = true)
	private String headImageID;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Seller manager;
	@OneToMany(mappedBy = "offer")
	private Set<Payment> payment = new HashSet<Payment>();
	@OneToMany(mappedBy = "offer", cascade = { ALL }, fetch = FetchType.LAZY)
	private Set<Coupons> coupons = new HashSet<Coupons>();
	@OneToMany(mappedBy = "offer", cascade = { ALL }, fetch = FetchType.LAZY) 
	private Set<Comment> comment = new HashSet<Comment>();
	@OneToMany(mappedBy = "offer", cascade = { ALL }, fetch = FetchType.LAZY) 
	private Set<Image> image = new HashSet<Image>();
	@ManyToOne
	@JoinColumn(nullable = false)
	private Category category;
	
	public Offer(){
		super();
	}
	

	public Offer(String name, Date dateCreated, Date expirationDate,
			Date validFrom, Date validTo, double regularPrice,
			double salePrice, int maxOffers, String description,
			int purchasedOffers, boolean active, Seller manager,
			Category category,String headImageID) {
		this.name = name;
		this.dateCreated = dateCreated;
		this.expirationDate = expirationDate;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.regularPrice = regularPrice;
		this.salePrice = salePrice;
		this.maxOffers = maxOffers;
		this.description = description;
		this.purchasedOffers = purchasedOffers;
		this.active = active;
		this.manager = manager;
		this.category = category;
		this.headImageID = headImageID;
	}



	
	public String getHeadImageID() {
		return headImageID;
	}


	public void setHeadImageID(String headImageID) {
		this.headImageID = headImageID;
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public double getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(double regularPrice) {
		this.regularPrice = regularPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public int getMaxOffers() {
		return maxOffers;
	}

	public void setMaxOffers(int maxOffers) {
		this.maxOffers = maxOffers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPurchasedOffers() {
		return purchasedOffers;
	}

	public void setPurchasedOffers(int purchasedOffers) {
		this.purchasedOffers = purchasedOffers;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Seller getManager() {
		return manager;
	}

	public void setManager(Seller manager) {
		this.manager = manager;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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


	public Set<Image> getImage() {
		return image;
	}


	public void setImage(Set<Image> image) {
		this.image = image;
	}
	
	public boolean getDelete(){
		return comment.isEmpty()&&coupons.isEmpty()&&payment.isEmpty();
	}
	
	
	


}

