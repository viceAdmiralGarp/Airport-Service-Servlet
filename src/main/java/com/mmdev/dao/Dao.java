package com.mmdev.dao;

import java.util.List;

public interface Dao<E> {
	List<E> findAll();

	E findById(Long id);

	void create(E entity);

	void update(E entity);

	void remove(E entity);


}
