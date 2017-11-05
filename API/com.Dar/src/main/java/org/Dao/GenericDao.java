package org.Dao;

import java.util.List;

import org.hibernate.query.Query;

public interface GenericDao<T> {
	public T getOne(Query<T> query);
	public List<T> getAll(Query<T> query);
	public void add(T obj);
	//TODO add getById
}
