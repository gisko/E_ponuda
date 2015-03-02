package session;

import java.util.List;

import entity.Branch;
import entity.Seller;

public interface BranchDaoLocal extends GenericDaoLocal<Branch, Integer> {
	public Branch findUnique(String name, String address);
	public List<Branch> findBranchForSeller(Seller  sellerID);
	
	
}
