package com.sboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCheck {
    private boolean uidExists;
    private boolean nickExists;
    private boolean emailExists;


    public String getUidExists() {
        return getUidExists();
    }

    public String getEmailExists() {
        return getEmailExists();
    }

    public String getNickExists() {
        return getNickExists();
    }

}
