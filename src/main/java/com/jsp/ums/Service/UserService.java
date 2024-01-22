package com.jsp.ums.Service;

import org.springframework.http.ResponseEntity;

import com.jsp.ums.Entity.User;
import com.jsp.ums.requestDto.UserRequest;
import com.jsp.ums.responseDto.UserResponse;
import com.jsp.ums.util.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest);

	ResponseEntity<ResponseStructure<UserResponse>> findUser(int userId);

	ResponseEntity<ResponseStructure<User>> deleteUser(int userId) throws Exception;

	ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId, User user);

	

}
