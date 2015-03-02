package session;

import java.util.List;

import entity.Buyer;
import entity.Coupons;

public interface CouponsDaoLocal extends GenericDaoLocal<Coupons, Integer> {
	public List<Coupons> findCoupounsForBuyer(Buyer  buyerID);
	
}
