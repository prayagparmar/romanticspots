package com.bitchinc.romantics.user.model;

import com.bitchinc.romantics.enums.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: prayagparmar
 * Date: 7/30/14
 * Time: 7:02 PM
 */
@Entity
@Table(name = "users", schema = "applifyed")
public class User implements Serializable{
    @Id
    @Column(name = "username", unique = true, nullable = false, length = 32)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_generator")
    @TableGenerator(name = "user_id_generator", initialValue = 1, allocationSize = 1)
    @Column(name="user_id", unique = true/*TODO, nullable = false*/)
    private Long userId;

    @Column(name = "email", nullable = false, length = 75)
    private String email;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "image")
    @Lob
    private byte[] image;

    @Column(name="created_on", nullable = false, insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name="modified_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade=CascadeType.ALL)
    private Set<UserRole> userRole = new HashSet<>(0);

    public User(){

    }

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(this, Role.USER.getRoleUser()));
        this.userRole = userRoles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    @PrePersist
    public void setCreatedOn() {
        this.createdOn = this.modifiedOn = new Date();
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    @PreUpdate
    public void setModifiedOn() {
        this.modifiedOn = new Date();
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
}
