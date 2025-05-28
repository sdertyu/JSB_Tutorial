package com.pngo.crudProj.services;

import com.pngo.crudProj.dto.request.UserCreate;
import com.pngo.crudProj.dto.request.UserUpdate;
import com.pngo.crudProj.dto.response.UserResponse;
import com.pngo.crudProj.entities.User;
import com.pngo.crudProj.exception.AppException;
import com.pngo.crudProj.exception.ErrorCode;
import com.pngo.crudProj.mapper.UserMapper;
import com.pngo.crudProj.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreate request) {
//        User user = new User();

        if (userRepository.existsByUsername(request.getUsername())) {
//            throw new RuntimeException(ErrorCode.UNCATEGORIED_EXCEPTION.getMessage());
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

//        UserCreate userCreate = UserCreate.builder()
//                .username(request.getUsername())
//                .password(request.getPassword())
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .birthDate(request.getBirthDate())
//                .build();

//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setBirthDate(request.getBirthDate());

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUserById(String id) {
        UserResponse userResponse = userMapper.toUserResponse(userRepository.findById(id).orElseThrow(()
                -> new RuntimeException("User not found with id: " + id)));
        return userResponse;
//        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//        return userRepository.findById(id).orElse(null);
    }

    public UserResponse updateUser(String id, UserUpdate request) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIED_EXCEPTION));
        userMapper.updateUserFromDto(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIED_EXCEPTION));
        userRepository.deleteById(id);
    }
}
