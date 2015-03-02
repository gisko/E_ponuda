package session;

import entity.Seller;

public interface SellerDaoLocal extends GenericDaoLocal<Seller, Integer> {
	
	public Seller findUser(
			String username, String password);
}
