package com.sixman.roomus.member.Dto;

import com.sixman.roomus.member.command.domain.model.Role;


public class LoginRequestDTO {

    String userName;
    int userNo;
    Role userRole;
    public LoginRequestDTO() {
        super();
    }


    public LoginRequestDTO(String userName, int userNo, Role userRole) {
        this.userName = userName;
        this.userNo = userNo;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "LoginRequestDTO{" +
                "userName='" + userName + '\'' +
                ", userNo=" + userNo +
                ", userRole=" + userRole +
                '}';
    }
}
