package org.Dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T> {
	public T getById(Serializable id);
	
	public List<T> getAll();
	
	public void add(T obj);
	public void delete(Serializable id);
	public void update(T o);
	
	public T getOneBy(String field, Serializable value);
	public List<T> getAllBy(String field, Serializable value);
}
