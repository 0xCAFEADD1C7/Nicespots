package org.Dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.query.Query;

public interface GenericDao<T> {
	public T getById(Serializable id);
//	public T getOne(Query<T> query);
	
	public List<T> getAll();
//	public List<T> getAll(Query<T> query);
	
	public void add(T obj);
	public void delete(Serializable id);
}
