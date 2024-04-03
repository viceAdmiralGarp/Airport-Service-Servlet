package com.mmdev.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<E> {
	List<E> findAll();

	E findById(Long id);

	E create(E entity);

	void update(E entity);

	void remove(E entity);


}
