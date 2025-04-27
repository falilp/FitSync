package com.authService.authService.dto;

public class RegisterRequest{
    private String username;
    private String password;

    public RegisterRequest(){}

    public RegisterRequest(String username, String pass){
        this.username = username;
        this.password = pass;
    }

    public String getUsername(){ return username; }
    public String getPassword(){ return password; }

    public void setUsername(String username){ this.username = username; }
    public void setPassword(String pass){ this.password = pass; }  
}