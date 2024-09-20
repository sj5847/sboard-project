package com.sboard.dto;

import com.sboard.entity.User;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "user")
public class UserDto {

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

    public User toEntity() {
        return User.builder()
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
}
