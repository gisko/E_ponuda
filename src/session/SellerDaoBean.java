package session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Seller;
@Stateless
@Local(SellerDaoLocal.class)
public class SellerDaoBean extends GenericDaoBean<Seller, Integer> implements
		SellerDaoLocal {

	@Override
	public Seller findUser(String username, String password) {
		Query q = em
				.createNamedQuery("findSeller");
		q.setParameter("username", username);
		q.setParameter("password", password);
		Seller seller = (Seller) q.getSingleResult();
		return seller;
	}
	/*
	public void remove(Seller v) {
		v = em.merge(v);
		v.getBranch().remove(v);
		em.remove(v);
	}
	*/
}
