package complete_task;

import api.ToDoList;
import api.Todo;
import data_access.FileTaskDataAccessObject;
import entity.AllTaskFactory;
import entity.TaskFactory;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import use_case.complete_task.*;
import use_case.create_task.CreateTaskInputBoundary;
import use_case.create_task.CreateTaskInteractor;
import use_case.display_task.DisplayTaskDataAccessInterface;
import use_case.display_task.DisplayTaskInteractor;
import use_case.display_task.DisplayTaskOutputBoundary;
import use_case.display_task.DisplayTaskOutputData;

import java.io.IOException;

import static org.junit.Assert.*;

public class CompleteTaskInteractorTest {

    public void addTwoTasks() {
        TaskFactory tf = new AllTaskFactory();
        FileTaskDataAccessObject ftdao;
        Todo todo = new ToDoList();

        try {
            ftdao = new FileTaskDataAccessObject("tasks.csv", tf, todo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ftdao.save(tf.create("task1", false, 1));
        ftdao.save(tf.create("task2", false, 2));
    }

    @Test
    public void successTest() throws IOException {
        addTwoTasks();
        TaskFactory taskFactory = new AllTaskFactory();
        Todo todo = new ToDoList();
        CompleteTaskDataAccessInterface taskRepository = new FileTaskDataAccessObject("tasks.csv", taskFactory, todo);

        CompleteTaskOutputBoundary successPresenter = new CompleteTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(CompleteTaskOutputData completed) {
                assertEquals("task1", completed.getTask());
            }
        };

        CompleteTaskInputData completeTaskInputData= new CompleteTaskInputData("task1");
        CompleteTaskInputBoundary interactor = new CompleteTaskInteractor(taskRepository, successPresenter);
        interactor.execute(completeTaskInputData);
    }

    @Test
    public void completeTaskInputDataTest(){
        CompleteTaskInputData completeTaskInputData= new CompleteTaskInputData("task1");
        assertEquals(completeTaskInputData.getTaskName(), "task1");
    }

    @Test
    public void completeTaskOutputDataTest() {
        addTwoTasks();
        CompleteTaskOutputData completeTaskOutputData = new CompleteTaskOutputData("task1", false);
        String taskName = completeTaskOutputData.getTask();
        assertEquals(taskName, "task1");
    }
}
