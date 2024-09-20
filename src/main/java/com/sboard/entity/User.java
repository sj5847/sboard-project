package com.sboard.entity;

import com.sboard.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    private String uid;
    private String pass;
    private String name;
    private String nick;
    private String email;
    private String hp;
    private String role;
    private String zip;
    private String addr1;
    private String addr2;
    private String regip;

    @CreationTimestamp
    private String regDate;
    private String leaveDate;

    public UserDto toDto() {
        return UserDto.builder()
                .uid(uid)
                .pass(pass)
                .name(name)
                .nick(nick)
                .email(email)
                .hp(hp)
                .role(role)
                .zip(zip)
                .addr1(addr1)
                .addr2(addr2)
                .regip(regip)
                .regDate(regDate)
                .leaveDate(leaveDate)
                .build();
    }


    public void setName(String name) {
    }

    public void setEmail(String email) {
    }

    public void setHp(String hp) {
    }

    public void setNick(String name) {

    }
}
