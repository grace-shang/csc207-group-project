package entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

    private final Task task = new Task("task1", true, -1);
    private final Task task3 = new Task("task3", -3);

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
    public void getTaskId() {
        assertEquals(-1, task.getTaskId());
        assertEquals(-3, task3.getTaskId());
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

    @Test
    public void setTaskId() {
        task.setTaskId(-2);
        assertEquals(-2, task.getTaskId());
    }

}