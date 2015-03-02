package session;

import entity.Buyer;

public interface BuyerDaoLocal extends GenericDaoLocal<Buyer, Integer> {

	public Buyer findUser(
			String username, String password);
}
