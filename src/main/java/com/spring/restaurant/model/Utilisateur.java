package com.spring.restaurant.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utilisateur", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class Utilisateur extends BaseEntity {

    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 3, max = 50)
    private String username;

    @Size(max = 50)
    @Email
    private String email;

    @Size(min = 6, max = 100)
    private String password;

    private String photo = "avatar.jpg";

    private boolean isActive;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "userRole",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")}
    )
    private Set<Role> roles = new HashSet<>();

    public Utilisateur() {
        super();
    }

    public Utilisateur(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    //@JsonIgnore
    public String getPassword() {
        return password;
    }

    //	@JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
