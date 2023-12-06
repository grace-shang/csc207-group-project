package create_task;

import api.ToDoList;
import api.Todo;
import data_access.InMemoryTaskDataAccessObject;
import entity.AllTaskFactory;
import org.junit.Test;
import use_case.create_task.*;

import static org.junit.Assert.*;

public class CreateTaskInteractorTest {

    private Todo todo = new ToDoList() {
        @Override
        public long addTask(String projectName, String taskName) {
            return -1;
        }

        @Override
        public void completeTask(String projectName, String taskName, long taskId) {

        }
    };

    /**
     * ensures there are at least 2 tasks in the CSV file for testing purposes
     */

    @Test
    public void successTest() {
        CreateTaskInputData inputData = new CreateTaskInputData("task1");
        CreateTaskDataAccessInterface taskRepository = new InMemoryTaskDataAccessObject(todo);

        // This creates a successPresenter that tests whether the test case is as we expect.
        CreateTaskOutputBoundary successPresenter = new CreateTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateTaskOutputData task) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("task1", task.getTask());
                assertTrue(taskRepository.existByName("task1"));
            }

            @Override
            public void prepareFailView(String error){
                fail("Use case failure is unexpected.");
            }
        };

        CreateTaskInputBoundary interactor = new CreateTaskInteractor(taskRepository, successPresenter, new AllTaskFactory());
        interactor.execute(inputData);
    }

    @Test
    public void failTest() {
        CreateTaskInputData inputData = new CreateTaskInputData("");
        CreateTaskDataAccessInterface taskRepository = new InMemoryTaskDataAccessObject(todo);

        // This creates a successPresenter that tests whether the test case is as we expect.
        CreateTaskOutputBoundary failPresenter = new CreateTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateTaskOutputData task) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error){
                assertEquals("An Empty Task Can't Be Added", error);
            }
        };

        CreateTaskInputBoundary interactor = new CreateTaskInteractor(taskRepository, failPresenter, new AllTaskFactory());
        interactor.execute(inputData);
    }

}
