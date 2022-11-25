package com.sixman.roomus.member.command.domain.model.vo;

public enum Role {
    ADMIN("ADMIN"),
    SELLER("SELLER"),
    USER("USER");
    private String value;

    Role(String value){
        this.value = value;
    }

    public String getKey(){
        return name();
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
