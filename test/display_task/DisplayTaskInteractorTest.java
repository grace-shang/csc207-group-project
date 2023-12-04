package display_task;

import api.ToDoList;
import api.Todo;
import app.Main;
import data_access.FileTaskDataAccessObject;
import data_access.InMemoryTaskDataAccessObject;
import entity.AllTaskFactory;
import entity.TaskFactory;
import entity.TaskI;
import org.junit.Test;
import use_case.display_task.DisplayTaskDataAccessInterface;
import use_case.display_task.DisplayTaskInteractor;
import use_case.display_task.DisplayTaskOutputBoundary;
import use_case.display_task.DisplayTaskOutputData;

import javax.swing.*;
import java.io.IOException;

import static org.junit.Assert.*;

public class DisplayTaskInteractorTest {

    public void addTwoTasks() {
        TaskFactory tf = new AllTaskFactory();
        FileTaskDataAccessObject ftdao;
        Todo todo = new ToDoList();

        try {
            ftdao = new FileTaskDataAccessObject("tasks.csv", tf, todo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ftdao.save(tf.create("task1", false));
        ftdao.save(tf.create("task2", false));
    }

    @Test
    public void successTest() {
        addTwoTasks();
        DisplayTaskDataAccessInterface taskRepository = new InMemoryTaskDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.

        DisplayTaskOutputBoundary successPresenter = new DisplayTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(DisplayTaskOutputData displayTasks) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertNotNull(displayTasks.getTaskInfo());
                assertNotNull(displayTasks.getAllTasks());
                assertEquals("task1", displayTasks.getAllTasks().get(0));
                assertEquals("task2", displayTasks.getAllTasks().get(1));
                assertEquals(false, displayTasks.getTaskInfo().get(0).get(0));
                assertEquals(false, displayTasks.getTaskInfo().get(1).get(0));
            }

        };

        DisplayTaskInteractor interactor = new DisplayTaskInteractor(taskRepository, successPresenter);
        interactor.execute();
    }
}