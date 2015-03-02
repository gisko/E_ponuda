package session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Buyer;
import entity.Coupons;
@Stateless
@Local(CouponsDaoLocal.class)
public class CouponsDaoBean extends GenericDaoBean<Coupons, Integer> implements
		CouponsDaoLocal {
	@Override
	public List<Coupons> findCoupounsForBuyer(Buyer buyerID) {
		Query q = em.createNamedQuery("findCoupounsForBuyer");
		q.setParameter("buyerID", (buyerID));
		@SuppressWarnings("unchecked")
		List<Coupons> coupons = (List<Coupons>) q.getResultList();
		return coupons;
	}
}
