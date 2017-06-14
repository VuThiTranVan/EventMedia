package com.framgia.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * 
 * @version 22/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
public abstract class AbstractDAO<PK extends Serializable, T> extends HibernateDaoSupport {

	private Class<T> persistentClass;

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public AbstractDAO(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public void initDao() {

	}

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
		        .getActualTypeArguments()[1];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T getFindById(PK key) {
		return (T) getSession().get(getPersistentClass(), key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(getPersistentClass());
	}

	protected Criteria createEntityCriteriawithAlias(String alias) {
		return getSession().createCriteria(getPersistentClass(), alias);
	}

}
