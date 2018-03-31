package com.mk.challenge.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void builderTest() {
        User user = new User.Builder("abc1").userName("abc").password("pass")
                .email("abc@abc.com").active(true).build();
        assertEquals("userId", "abc1", user.getUserId());
        assertEquals("userName", "abc", user.getUserName());
        assertEquals("password", "pass", user.getPassword());
        assertEquals("email", "abc@abc.com", user.getEmail());
        assertEquals("active", true, user.isActive());
    }

}