package com.sboard.service;

import com.sboard.dto.UserDto;
import com.sboard.entity.User;
import com.sboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User insertUser(UserDto userDto) {
        User user = userDto.toEntity();
        return userRepository.save(user);
    }

    public UserDto selectUser(String uid) {

        return userRepository.findById(uid)
                .map(User::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public List<UserDto> selectUsers() {
              List<User> users =  userRepository.findAll();

              List<UserDto> userDtos = users
                      .stream()
                      .map(e->e.toDto())
                      .collect(Collectors.toUnmodifiableList());
        return userDtos;
//        return users.stream()
//                .map(User::toDto)
//                .collect(Collectors.toUnmodifiableList());
    }

    public void updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getUid()).orElseThrow(()->
                new RuntimeException("User not found"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setHp(userDto.getHp());
        user.setNick(userDto.getName());

        userRepository.save(user);
    }

    public void deleteUser(String uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(()->new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    public boolean checkEmail(String email) {

        return userRepository.findByEmail(email).isPresent();
    }

    public boolean checkUid(String uid) {

        return userRepository.findByUid(uid).isPresent();
    }

    public boolean checkNick(String nick) {
        return userRepository.findByNick(nick).isPresent();
    }
}
