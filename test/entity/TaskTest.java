package entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    private final Task task = new Task("task1", true);
    private final Task task3 = new Task("task3");

    @Test
    public void getName() {
        assertEquals("task1", task.getName());
        assertEquals("task3", task3.getName());
    }

    @Test
    public void getComplete() {
        assertTrue(task.getComplete());
        assertFalse(task3.getComplete());
    }

    @Test
    public void setName() {
        task.setName("task2");
        assertEquals("task2", task.getName());
    }

    @Test
    public void setComplete() {
        task.setComplete(false);
        assertFalse(task.getComplete());
    }
}