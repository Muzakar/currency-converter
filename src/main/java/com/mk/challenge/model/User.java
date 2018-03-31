package com.mk.challenge.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity to store user information
 */
@Entity
@Table(name="USER")
public class User implements Serializable {

    @Id
    private String userId;

    private String userName;
    private String password;
    private String email;
    private String address;
    private String postcode;
    private String country;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", country='" + country + '\'' +
                ", active=" + active +
                '}';
    }

    public static class Builder {

        private String userId;
        private String userName;
        private String password;
        private String email;
        private String address;
        private String postcode;
        private String country;
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

        public Builder address (String address) {
            this.address = address;
            return this;
        }

        public Builder postcode (String postcode) {
            this.postcode = postcode;
            return this;
        }

        public Builder country (String country) {
            this.country = country;
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
            user.setAddress(address);
            user.setPostcode(postcode);
            user.setCountry(country);
            user.setActive(active);
            return user;
        }

    }

}
