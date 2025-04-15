package com.authService.authService.models;

//#region imports
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//#endregion

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surName;
    private String email;
    private String password;
    private Boolean isActive = true;
    private Boolean logicalErase = false;
    private Date createdDate = new Date();
    private Date lastModifiedDate = new Date();

    @Enumerated(EnumType.STRING)
    private Role role;

    protected User(){}

    public User(String name, String surName, String pass, Role rol){
        this.name = name;
        this.surName = surName;
        this.password = pass;
        this.role = rol;
    }

    @Override
    public String toString(){ return String.format("User[id=%d, name=%s, surName=%s, role=%s]", id,name,surName,role); }

    //#region geters
    public Long getId(){ return id; }
    public String getName(){ return name; }
    public String getSurName(){ return surName; }
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }
    public Boolean getIsActive(){ return isActive; }
    public Boolean getLogicalErase(){ return logicalErase; }
    public Date getCreatedDate(){ return createdDate; }
    public Date getLastModifiedDate(){ return lastModifiedDate; }
    public Role getRole(){ return role; }
    //#endregion
}