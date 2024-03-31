package com.mmdev.dao;

import com.mmdev.entity.Gender;
import com.mmdev.entity.Role;
import com.mmdev.entity.Ticket;
import com.mmdev.entity.User;
import com.mmdev.exception.DaoException;
import com.mmdev.util.ConnectionManagerUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {

	private static final UserDao INSTANCE = new UserDao();

	private static final String SAVE_SQL = """
						INSERT INTO users (name, email,password,birthday,role,gender) 
						VALUES (?,?,?,?,?,?);
			""";

	private static final String FIND_ALL_SQL = """
									SELECT id, name, email,password,birthday,role,gender FROM users;	
			""";

	private static final String FIND_BY_ID_SQL = """
									SELECT id, name, email,password,birthday,role,gender FROM users WHERE id = ?;
			""";

	private static final String UPDATE_SQL = """
									UPDATE users SET 
									name = ?, 
									email = ?,
									password = ?,
									birthday = ?,
									role  = ?,
									gender  = ?
									WHERE id = ?		
			""";

	private static final String DELETE_SQL = """
						DELETE FROM users
						WHERE id = ?
						AND name = ?
									AND email = ?
									AND password = ?
									AND birthday = ?
									AND role  = ?
									AND gender  = ?	
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
			while (resultSet.next()) {
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
			 var prepareStatement = open.prepareStatement(FIND_BY_ID_SQL)) {
			prepareStatement.setLong(1, id);
			var resultSet = prepareStatement.executeQuery();
			User user = null;
			while (resultSet.next()) {
				user = buildUser(resultSet);
			}
			return user;
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (User findById)", e);
		}
	}

	@Override
	public void create(User entity) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(SAVE_SQL)) {
			prepareStatement.setString(1, entity.getName());
			prepareStatement.setString(2, entity.getEmail());
			prepareStatement.setString(3, entity.getPassword());
			prepareStatement.setDate(4, entity.getBirthday());
			prepareStatement.setObject(5, entity.getRole(), Types.OTHER);
			prepareStatement.setObject(6, entity.getGender(), Types.OTHER);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (User create)", e);
		}
	}

	@Override
	public void update(User entity) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(UPDATE_SQL)) {
			prepareStatement.setString(1, entity.getName());
			prepareStatement.setString(2, entity.getEmail());
			prepareStatement.setString(3, entity.getPassword());
			prepareStatement.setDate(4, entity.getBirthday());
			prepareStatement.setObject(5, entity.getRole());
			prepareStatement.setObject(6, entity.getGender());
			prepareStatement.setLong(7, entity.getId());
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error while executing SQL query (User update)", e);
		}
	}

	@Override
	public void remove(User entity) {
		try (var open = ConnectionManagerUtil.open();
			 var prepareStatement = open.prepareStatement(DELETE_SQL)) {
			prepareStatement.setLong(1, entity.getId());
			prepareStatement.setString(2, entity.getName());
			prepareStatement.setString(3, entity.getEmail());
			prepareStatement.setString(4, entity.getPassword());
			prepareStatement.setDate(5, entity.getBirthday());
			prepareStatement.setObject(6, entity.getRole());
			prepareStatement.setObject(7, entity.getGender());
			prepareStatement.executeUpdate();
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
				.role((Role) resultSet.getObject("role"))
				.gender((Gender) resultSet.getObject("gender"))
				.build();
	}

	public static UserDao getInstance() {
		return INSTANCE;
	}

}
