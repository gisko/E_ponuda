package session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.Admin;
@Stateless
@Local(AdminDaoLocal.class)
public class AdminDaoBean extends GenericDaoBean<Admin, Integer> implements
		AdminDaoLocal {
	
	public Admin findUser(
			String username, String password){
		Query q = em
				.createNamedQuery("findAdmin");
		q.setParameter("username", username);
		q.setParameter("password", password);
		Admin result = (Admin) q.getSingleResult();
		return result;
		
	}

	@Override
	public Class<Admin> getEntityType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> findBy(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin persist(Admin entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin merge(Admin entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Admin entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
