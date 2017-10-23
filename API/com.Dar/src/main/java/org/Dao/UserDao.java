package org.Dao;

import java.util.List;

import org.Entite.User;


public interface UserDao {

	public void addUser(User user);
	public User getUser(int id);
	public List<User> getAllUser();
}