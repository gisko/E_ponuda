package session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Buyer;
@Stateless
@Local(BuyerDaoLocal.class)
public class BuyerDaoBean extends GenericDaoBean<Buyer, Integer> implements
		BuyerDaoLocal {

	@Override
	public Buyer findUser(String username, String password) {
		Query q = em
				.createNamedQuery("findUser");
		q.setParameter("username", username);
		q.setParameter("password", password);
		Buyer buyer = (Buyer) q.getSingleResult();
		return buyer;
	}

	
}
