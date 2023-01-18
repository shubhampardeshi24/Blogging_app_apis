package com.blog.services;

import java.util.List;

import com.blog.payloads.UserDTO;


public interface UserService {

	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user, Integer userID);
	UserDTO getUserByID(Integer userID);
	List<UserDTO> getAllUsers();
	void deleteUser(Integer userID);
	
	
}
