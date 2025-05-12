package com.authService.authService.models;

//#region imports
import java.util.Date;
import java.util.List;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
//#endregion

@Entity
@Table(name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int height;
    @Column(nullable = false)
    private int weight;
    @Column(nullable = false)
    private Date birthDate;

    private String surName;
    private String verifyToken;
    private String email;
    private Boolean isActive = true;
    private Boolean logicalErase = false;
    private Date createdDate = new Date();
    private Date lastModifiedDate = new Date();

    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User(){}

    public User(String userName, String name, String surName, int height, int weight, Date birthDate, String verifyToken, String email, 
                String password, Boolean isActive, Boolean logicalErase, Date createdDate, Date lastModifiedDate, Role role, Gender gender){
        this.userName = userName; this.name = name;
        this.surName = surName; this.height = height;
        this.weight = weight; this.birthDate = birthDate;
        this.verifyToken = verifyToken; this.email = email;
        this.password = password; this.isActive = isActive;
        this.logicalErase = logicalErase; this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate; this.role = role;
        this.gender = gender;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){ return List.of(); }

    @Override
    public String toString(){
        return String.format(
            "User[id = %d, userName = %s, name = %s, surName = %s, height = %d, weight = %d, birthDate = %s, " +
            "verifyToken = %s, email = %s, isActive = %s, logicalErase = %s, createdDate = %s, lastModifiedDate = %s, " +
            "role = %s, gender = %s]",
            id, userName, name, surName, height, weight, birthDate,
            verifyToken, email, isActive, logicalErase, createdDate, lastModifiedDate,
            role, gender
        );
    }

    //#region getters
    public Long getId(){ return id; }
    public String getUsername(){ return userName; }
    public String getName(){ return name; }
    public String getSurName(){ return surName; }
    public int getHeight(){ return height; }
    public int getWeight(){ return weight; }
    public String getVerifyToken(){ return verifyToken; }
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }
    public Boolean getIsActive(){ return isActive; }
    public Boolean getLogicalErase(){ return logicalErase; }
    public Date getCreatedDate(){ return createdDate; }
    public Date getLastModifiedDate(){ return lastModifiedDate; }
    public Date getBirthDate(){ return birthDate; }
    public Role getRole(){ return role; }
    public Gender getGender(){ return gender; }
    //#endregion

    //#region setters
    public void setUserName(String userName){ this.userName = userName; }
    public void setName(String name){ this.name = name; }
    public void setSurName(String surName){ this.surName = surName; }
    public void setHeight(int height){ this.height = height; }
    public void setWeight(int weight){ this.weight = weight; }
    public void setVerifyToken(String verifyToken){ this.verifyToken = verifyToken; }
    public void setEmail(String email){ this.email = email; }
    public void setPassword(String password){ this.password = password; }
    public void setIsActive(boolean isActive){ this.isActive = isActive; }
    public void setLogicalErase(boolean logicalErase){ this.logicalErase = logicalErase; }
    public void setCreatedDate(Date createdDate){ this.createdDate = createdDate; }
    public void setLastModifiedDate(Date lastModifiedDate){ this.lastModifiedDate = lastModifiedDate; }
    public void setBirthDate(Date birthDate){ this.birthDate = birthDate; }
    public void setRole(Role role){ this.role = role; }
    public void setGender(Gender gender){ this.gender = gender; }
    //#endregion
    
    @Override
    public boolean isAccountNonExpired(){ return this.isActive; }
}