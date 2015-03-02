package session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Branch;
import entity.Seller;
@Stateless
@Local(BranchDaoLocal.class)
public class BranchDaoBean extends GenericDaoBean<Branch, Integer> implements
		BranchDaoLocal {

	@Override
	public Branch findUnique(String name, String address) {
		Query q = em
				.createNamedQuery("findUnique");
		q.setParameter("name", name);
		q.setParameter("address", address);
		Branch branch = (Branch) q.getSingleResult();
		return branch;
	}

	@Override
	public List<Branch> findBranchForSeller(Seller sellerID) {
		// TODO Auto-generated method stub
		Query q = em
				.createNamedQuery("findBranchForSeller");
		q.setParameter("sellerID", sellerID);
		@SuppressWarnings("unchecked")
		List<Branch> branches = (List<Branch>) q.getResultList();
		return branches;
	}

	


}
