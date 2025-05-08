package com.authService.authService.dto;

public class ResgisterResponse{
    private String msg;
    private String token;

    public ResgisterResponse(){}

    public ResgisterResponse(String msg, String token){ 
        this.msg = msg; 
        this.token = token;
    }

    public String getMsg(){ return msg; }
    public String getToken(){ return token; }

    public void setMsg(String msg){ this.msg = msg; }
    public void setToken(String token){ this.token = token; }
}