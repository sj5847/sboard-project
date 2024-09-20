package com.sboard.repository;

import com.sboard.dto.UserDto;
import com.sboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<Object> findByEmail(String email);

    Optional<Object> findByUid(String uid);

    Optional<Object> findByNick(String nick);


}
