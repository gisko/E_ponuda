package session;

import java.util.List;

import entity.Category;
import entity.Offer;
import entity.Seller;

public interface OfferDaoLocal extends GenericDaoLocal<Offer, Integer> {
	
	public List<Offer> findOffersPerCategory(Category  catID);
	public List<Offer> findActiveOffers();
	public List<Offer> findActiveOffersForManager(Seller sellerID);
	public List<Offer> findOffersForManager(Seller sellerID);
}
