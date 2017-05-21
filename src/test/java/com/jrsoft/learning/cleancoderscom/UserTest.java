package com.jrsoft.learning.cleancoderscom;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void oneUserIsSameAsItself() {
        User user = new User("user");
        assertTrue(user.isSame(user));
    }

    @Test
    public void twoDifferentUsersAreNotTheSame() {
        User u1 = new User("u1");
        User u2 = new User("u2");

        u1.setId("u1ID");
        u2.setId("u2ID");

        assertFalse(u1.isSame(u2));
    }

    @Test
    public void usersWithTheSameIDAreTheSame() {
        User u1 = new User("u1");
        User u2 = new User("u2");
        u1.setId("u1ID");
        u2.setId("u1ID");

        assertTrue(u1.isSame(u2));
    }
}
