package session;

import java.util.List;

import entity.User;

public interface UserDaoLocal extends GenericDaoLocal<User, Integer> {
	public User findUser(String username, String password);
	public List<User> findByType(String type);
}
