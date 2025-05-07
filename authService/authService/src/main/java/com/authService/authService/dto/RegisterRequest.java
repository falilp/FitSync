package com.authService.authService.dto;

import java.util.Date;

//#region imports
import com.authService.authService.models.Gender;
import com.authService.authService.models.Role;
//#endregion

public class RegisterRequest{
    private String username;
    private String name;
    private String surName;
    private String email;
    private Date birthDate;
    private Role rol;
    private Gender gender;
    private int height;
    private int weight;
    private String password;

    public RegisterRequest(){}

    public RegisterRequest(String username, String pass, String name, String surName, String email, 
                            Date birthDate, Role rol, Gender gender, int height, int weight){
        this.username = username; this.name = name; 
        this.surName = surName; this.email = email; 
        this.birthDate = birthDate; this.rol = rol;
        this.gender = gender; this.height = height;
        this.weight = weight; this.password = pass;
    }

    public String getUsername(){ return username; }
    public String getName(){ return name; }
    public String getSurName(){ return surName; }
    public String getEmail(){ return email; }
    public Date getBirthDate(){ return birthDate;}
    public Role getRol(){ return rol; }
    public Gender getGender(){ return gender; }
    public int getHeight(){ return height; }
    public int getWeight(){ return weight; }
    public String getPassword(){ return password; }

    public void setUsername(String username){ this.username = username; }
    public void setName(String name){ this.name = name; }
    public void setSurName(String surName){ this.surName = surName; }
    public void setEmail(String email){ this.email = email; }
    public void setBirthDate(Date birthDate){ this.birthDate = birthDate;}
    public void setRol(Role rol){ this.rol = rol; }
    public void setGender(Gender gender){ this.gender = gender; }
    public void setHeight(int height){ this.height = height; }
    public void setWeight(int weight){ this.weight = weight; }
    public void setPassword(String pass){ this.password = pass; }  
}