package com.jsp.ums.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ums.Entity.User;
import com.jsp.ums.Service.UserService;
import com.jsp.ums.requestDto.UserRequest;
import com.jsp.ums.responseDto.UserResponse;
import com.jsp.ums.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody @Valid UserRequest userRequest) {
		return userService.saveUser(userRequest);
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@PathVariable @Valid int userId,@RequestBody User user) {
		return userService.updateUser(userId,user);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUser(@PathVariable @Valid int userId) {
		return userService.findUser(userId);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@PathVariable @Valid int userId) throws Exception {
		return userService.deleteUser(userId);
	}
	
	
	
}
