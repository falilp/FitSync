package com.authService.authService.dto;

public class ResgisterResponse{
    private String msg;

    public ResgisterResponse(){}

    public ResgisterResponse(String msg){ this.msg = msg; }

    public String getMsg(){ return msg; }

    public void setMsg(String msg){ this.msg = msg; }
}