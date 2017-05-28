package com.jrsoft.learning.cleancoderscom;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EntityTest {

    @Test
    public void oneEntityIsSameAsItself() {
        Entity user = new Entity();
        user.setId("e1ID");

        assertTrue(user.isSame(user));
    }

    @Test
    public void twoDifferentEntitysAreNotTheSame() {
        Entity e1 = new Entity();
        Entity e2 = new Entity();

        e1.setId("e1ID");
        e2.setId("e2ID");

        assertFalse(e1.isSame(e2));
    }

    @Test
    public void usersWithTheSameIDAreTheSame() {
        Entity e1 = new Entity();
        Entity e2 = new Entity();
        e1.setId("e1ID");
        e2.setId("e1ID");

        assertTrue(e1.isSame(e2));
    }

    @Test
    public void userWithNullIdAreNeverSame() {
        Entity e1 = new Entity();
        Entity e2 = new Entity();

        assertFalse(e1.isSame(e2));
    }
}
