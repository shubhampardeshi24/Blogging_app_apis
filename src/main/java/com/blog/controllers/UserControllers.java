package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDTO;
import com.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apis/users")
public class UserControllers {
	
	@Autowired
	private UserService userService;
	
	//POST - Create User
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user) {
		UserDTO createUser = userService.createUser(user);
		return new ResponseEntity<UserDTO>(createUser, HttpStatus.CREATED);
	}
	
	//PUT - Update user
	@PutMapping("/{userID}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO dto, @PathVariable Integer userID) {
		
		UserDTO updateUser = userService.updateUser(dto, userID);
		return ResponseEntity.ok(updateUser);
	}
	
	//DELETE - delete-user
	@DeleteMapping("/{userID}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userID) {
		userService.deleteUser(userID);
		return new ResponseEntity<ApiResponse>((new ApiResponse("User deleted successfully", true)),HttpStatus.OK);
	}
	//GET - user-get
	
	@GetMapping("/{userID}")	
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer userID)
	{
		UserDTO user = userService.getUserByID(userID);
		return ResponseEntity.ok(user);
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUser() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
		
}
