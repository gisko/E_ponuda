package session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import entity.User;



@Stateless
@Local(UserDaoLocal.class)
public class UserDaoBean extends GenericDaoBean<User, Integer> implements UserDaoLocal {

	@Override
	public User findUser(String username, String password) {
		Query q = em
				.createNamedQuery("findUser");
		q.setParameter("username", username);
		q.setParameter("password", password);
		User user = (User) q.getSingleResult();
		return user;
	}
	@Override
	public List<User> findByType(String type) {
		Query q = em
				.createNamedQuery("findByType");
		q.setParameter("DTYPE", type);
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) q.getResultList();
		return users;
	}
}
