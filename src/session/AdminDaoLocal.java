package session;

import entity.Admin;

public interface AdminDaoLocal extends GenericDaoLocal<Admin, Integer> {
	public Admin findUser(
			String username, String password);

}
