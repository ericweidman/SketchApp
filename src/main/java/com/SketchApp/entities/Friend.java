package com.SketchApp.entities;

import javax.persistence.*;

/**
 * Created by ericweidman on 6/4/17.
 */
@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private boolean sentFriendRequest = false;

    @Column
    private boolean acceptedFriendRequest = false;

    @ManyToOne
    private User user;

    public Friend() {
    }

    public Friend(boolean sentFriendRequest, boolean acceptedFriendRequest, User user) {
        this.sentFriendRequest = sentFriendRequest;
        this.acceptedFriendRequest = acceptedFriendRequest;
        this.user = user;
    }

    public boolean isSentFriendRequest() {
        return sentFriendRequest;
    }

    public void setSentFriendRequest(boolean sentFriendRequest) {
        this.sentFriendRequest = sentFriendRequest;
    }

    public boolean isAcceptedFriendRequest() {
        return acceptedFriendRequest;
    }

    public void setAcceptedFriendRequest(boolean acceptedFriendRequest) {
        this.acceptedFriendRequest = acceptedFriendRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
