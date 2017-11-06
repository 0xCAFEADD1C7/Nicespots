package org.Dao;

import java.util.List;

import org.Entite.User;


public interface UserDao {

	public void addUser(User user);
	public User getUser(int id);
	public List<User> getAllUser();
	public User getUserByMail(String mail);
	
	
	public void updateUser(String  token,User user);
}
