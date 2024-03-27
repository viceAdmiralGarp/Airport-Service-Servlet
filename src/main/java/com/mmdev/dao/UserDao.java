package com.mmdev.dao;

import com.mmdev.entity.User;

import java.util.List;

public class UserDao implements Dao<User>{

	private static final String SAVE_SQL = """
							INSERT INTO users (name, birthday, image, email, password, role, gender) 
							VALUES (?,?,?,?,?,?,?);
			""";
	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public User findById(Long id) {
		return null;
	}

	@Override
	public void create(User entity) {


	}

	@Override
	public void update(User entity) {

	}

	@Override
	public void remove(User entity) {

	}
}
