package com.mmdev.dao;

import java.util.List;

public interface Dao <E>{
	List<E> findAll();
}
