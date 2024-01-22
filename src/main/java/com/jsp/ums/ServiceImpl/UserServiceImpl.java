package com.jsp.ums.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.ums.Entity.User;
import com.jsp.ums.Exception.UserNotFoundByIdException;
import com.jsp.ums.Repository.UserRepository;
import com.jsp.ums.Service.UserService;
import com.jsp.ums.requestDto.UserRequest;
import com.jsp.ums.responseDto.UserResponse;
import com.jsp.ums.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResponseStructure<UserResponse> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest request) {
		User user = userRepository.save(mapToUser(request));
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("User saved Successfully");
		responseStructure.setData(mapToUserReponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);
	}


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUser(int userId) {
		return userRepository.findById(userId)
				.map(user->{
					ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>();
					responseStructure.setStatus(HttpStatus.FOUND.value());
					responseStructure.setMessage("user fatch susscessfully");
					responseStructure.setData(mapToUserReponse(user));
					return new ResponseEntity<>(responseStructure,HttpStatus.FOUND);
				})
				.orElseThrow(()-> new UserNotFoundByIdException("user not found by id"));
	}


	@Override
	public ResponseEntity<ResponseStructure<User>> deleteUser(int userId) throws Exception {

		Optional<User> optional = userRepository.findById(userId);
		if(optional.isPresent()) {
			User user = optional.get();
			userRepository.delete(user);
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted Successfully...");
			responseStructure.setData(user);
			return new  ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		}
		else {
			throw new UserNotFoundByIdException("User Not Found");
		}
	}



	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId, User user) {

		User user2 = userRepository.findById(userId)
				.map(u ->{
					user.setUserId(userId);
					return userRepository.save(user);
				})
				.orElseThrow(()-> new RuntimeException());
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Updated Successfully");
		responseStructure.setData(mapToUserReponse(user2));

		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);
	}

	private User mapToUser(UserRequest request) {
		User user = new User();
		user.setUserName(request.getUserName());
		user.setUserEmail(request.getUserEmail());
		user.setUserPassword(request.getUserPassword());
		return user;

	}

	private UserResponse mapToUserReponse(User user) {
		UserResponse response2 = new UserResponse();
		response2.setUserId(user.getUserId());
		response2.setUserEmail(user.getUserEmail());
		response2.setUserName(user.getUserName());
		return response2;
	}

}