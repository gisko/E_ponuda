package session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Category;
import entity.Offer;
import entity.Seller;
@Stateless
@Local(OfferDaoLocal.class)
public class OfferDaoBean extends GenericDaoBean<Offer, Integer> implements
		OfferDaoLocal {

	@Override
	public List<Offer> findOffersPerCategory(Category catID) {
		Query q = em.createNamedQuery("findOffersPerCategory");
		q.setParameter("catID", (catID));
		@SuppressWarnings("unchecked")
		List<Offer> offers = (List<Offer>) q.getResultList();
		return offers;
	}

	@Override
	public List<Offer> findActiveOffers() {
		Query q = em.createNamedQuery("findActiveOffers");
		@SuppressWarnings("unchecked")
		List<Offer> offers = (List<Offer>) q.getResultList();
		return offers;
	}
	@Override
	public List<Offer> findActiveOffersForManager(Seller managerID) {
		Query q = em.createNamedQuery("findActiveOffersForManager");
		q.setParameter("managerID", (managerID));
		@SuppressWarnings("unchecked")
		List<Offer> offers = (List<Offer>) q.getResultList();
		return offers;
	}
	@Override
	public List<Offer> findOffersForManager(Seller managerID) {
		Query q = em.createNamedQuery("findOffersForManager");
		q.setParameter("managerID", (managerID));
		@SuppressWarnings("unchecked")
		List<Offer> offers = (List<Offer>) q.getResultList();
		return offers;
	}
	

	

}
