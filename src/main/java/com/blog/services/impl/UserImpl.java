package com.blog.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourcesNotFoundException;
import com.blog.payloads.UserDTO;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;

@Service
public class UserImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper mapper;
	@Override
	public UserDTO createUser(UserDTO user) {
		User dtoToUser = this.dtoToUser(user);
		userRepo.save(dtoToUser);
		return this.userToUserDTO(dtoToUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userID) {

		User user  = this.userRepo.findById(userID).orElseThrow(() -> new ResourcesNotFoundException("user", "id", userID));
		
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setAbout(userDTO.getAbout());
		User updatedUser = userRepo.save(user);
		
		return this.userToUserDTO(updatedUser);
	}

	@Override
	public UserDTO getUserByID(Integer userID) {
		User user = this.userRepo.findById(userID).orElseThrow(()->new ResourcesNotFoundException("user", "id", userID));
		return this.userToUserDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		
		
		
		List<UserDTO> allUsers = users.stream().map(user -> this.userToUserDTO(user)).collect(Collectors.toList());
		
		/*
		for (User user : users) {
			UserDTO dto = new UserDTO();
			dto.setAbout(user.getAbout());
			dto.setEmail(user.getEmail());
			dto.setName(user.getName());
			dto.setPassword(user.getPassword());
			allUsers.add(dto);
		}
		*/
		
		return allUsers;
	}

	@Override
	public void deleteUser(Integer userID) {
		User user = userRepo.findById(userID).orElseThrow(()->new ResourcesNotFoundException("user", "id", userID));
		this.userRepo.delete(user);
	}

	public User dtoToUser(UserDTO dto) {
		User user = this.mapper.map(dto, User.class);
	/*	user.setId(dto.getId());
		user.setAbout(dto.getAbout());
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
	*/
		return user;
	}

	public UserDTO userToUserDTO(User user) {
		UserDTO dto = mapper.map(user, UserDTO.class);
/*
		dto.setAbout(user.getAbout());
		dto.setEmail(user.getEmail());
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setPassword(user.getPassword());
*/
		return dto;
	}

}
