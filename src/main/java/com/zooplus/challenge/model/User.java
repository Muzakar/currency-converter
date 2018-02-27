package com.zooplus.challenge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="USER")
public class User implements Serializable {

    @Id
    private String userId;

    private String userName;
    private String password;
    private String email;
    private boolean active;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", UserName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class Builder {

        private String userId;
        private String userName;
        private String password;
        private String email;
        private boolean active;

        public Builder(String userId) {
            this.userId = userId;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder password (String password) {
            this.password = password;
            return this;
        }

        public Builder email (String email) {
            this.email = email;
            return this;
        }

        public Builder active (boolean active) {
            this.active = active;
            return this;
        }

        public User build() {
            User user = new User();
            user.setUserId(userId);
            user.setUserName(userName);
            user.setPassword(password);
            user.setEmail(email);
            user.setActive(active);
            return user;
        }

    }

}
