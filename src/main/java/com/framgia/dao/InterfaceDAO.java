package com.framgia.dao;

import java.io.Serializable;

public interface InterfaceDAO<PK extends Serializable, T> extends java.io.Serializable {
	public void delete(T entity);

	public void saveOrUpdate(T entity);
}
