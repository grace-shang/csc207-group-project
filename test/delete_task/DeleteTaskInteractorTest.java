package delete_task;

import api.ToDoList;
import api.Todo;
import data_access.FileTaskDataAccessObject;
import data_access.InMemoryTaskDataAccessObject;
import entity.AllTaskFactory;
import entity.TaskFactory;
import use_case.delete_task.DeleteTaskDataAccessInterface;
import use_case.delete_task.DeleteTaskInteractor;
import use_case.delete_task.DeleteTaskOutputBoundary;
import use_case.delete_task.DeleteTaskOutputData;

import java.io.IOException;

import static org.junit.Assert.*;

public class DeleteTaskInteractorTest {

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

    @org.junit.Test
    public void successTest() throws IOException {
        addTwoTasks();
        TaskFactory taskFactory = new AllTaskFactory();
        Todo todo = new ToDoList();
        DeleteTaskDataAccessInterface taskRepository = new FileTaskDataAccessObject("tasks.csv", taskFactory, todo);

        DeleteTaskOutputBoundary successPresenter = new DeleteTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteTaskOutputData deleted) {
                assertNotNull(deleted.getAllTasks());
                assertEquals(0, deleted.getAllTasks().size());
            }
        };

        DeleteTaskInteractor interactor = new DeleteTaskInteractor(taskRepository, successPresenter);
        interactor.execute();
    }
}