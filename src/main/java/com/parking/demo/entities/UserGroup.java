package com.parking.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usergroup")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Or AUTO depending on your setup
    @Column(name = "ID", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "usergroup", nullable = false)
    private String userGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }
}
