package com.bitchinc.romantics.user.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: prayagparmar
 * Date: 7/30/14
 * Time: 7:02 PM
 */
@Entity
@Table(name = "user_roles", schema = "applifyed", uniqueConstraints =
@UniqueConstraint(columnNames = { "role", "username" }))
public class UserRole implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_generator")
    @TableGenerator(name = "id_generator", initialValue = 1, allocationSize = 1)
    @Column(name = "user_role_id", unique = true, nullable = false)
    private Integer userRoleId;

    @Column(name = "role", nullable = false, length = 32)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    public UserRole(){

    }

    public UserRole(User user, String role){
        this.user = user;
        this.role = role;
    }

    public Integer getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
