package com.kosta.october.dao;

import com.kosta.october.domain.User;

public interface UserDao {
	
	int deleteUser(String id);
	
	User selectUser(String id);

	int insertUser(User user);
	
	int updateUser(User user);
	
	void deleteAll() throws Exception;
}
