package session;

import java.util.List;

import entity.Buyer;
import entity.Coupons;
import entity.Payment;

public interface PaymentDaoLocal extends GenericDaoLocal<Payment, Integer> {
	public List<Payment> findPaymentsForBuyer(Buyer  buyerID);
	public void sendEmail(Coupons coupon);
}
