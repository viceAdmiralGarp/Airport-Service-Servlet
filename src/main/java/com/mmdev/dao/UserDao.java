package com.mmdev.dao;

import com.mmdev.entity.Ticket;
import com.mmdev.entity.User;
import com.mmdev.exception.DaoException;
import com.mmdev.util.ConnectionManagerUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User>{

	private static final UserDao INSTANCE = new UserDao();

	private static final String SAVE_SQL = """
							INSERT INTO users (name, email,password,birthday,role,gender) 
							VALUES (?,?,?,?,?,?);
			""";

	private static final String FIND_ALL_SQL = """
   						SELECT id, name, email,password,birthday,role,gender FROM users;	
			""";

	private UserDao() {
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(FIND_ALL_SQL)) {
			var resultSet = prepareStatement.executeQuery();
			User user;
			while (resultSet.next()){
				user = buildUser(resultSet);
				users.add(user);
			}
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (User findAll)", e);
		}
		return users;
	}

	@Override
	public User findById(Long id) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement("")) {
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (User findById)", e);
		}
		return null;
	}

	@Override
	public void create(User entity) {
		try (var open = ConnectionManagerUtil.open();
		var prepareStatement = open.prepareStatement(SAVE_SQL)) {
			prepareStatement.setString(1,entity.getName());
			prepareStatement.setString(2,entity.getEmail());
			prepareStatement.setString(3,entity.getPassword());
			prepareStatement.setDate(4,entity.getBirthday());
			prepareStatement.setString(5,entity.getRole());
			prepareStatement.setString(6,entity.getGender());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (User create)", e);
		}
	}

	@Override
	public void update(User entity) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement("")) {
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (User update)", e);
		}
	}

	@Override
	public void remove(User entity) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement("")) {
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (User remove)", e);
		}
	}

	private User buildUser(ResultSet resultSet) throws SQLException {
		return User.builder()
				.id(resultSet.getLong("id"))
				.name(resultSet.getString("name"))
				.email(resultSet.getString("email"))
				.password(resultSet.getString("password"))
				.birthday(resultSet.getDate("birthday"))
				.role(resultSet.getString("role"))
				.gender(resultSet.getString("gender"))
				.build();
	}

	public static UserDao getInstance(){
		return INSTANCE;
	}

}
