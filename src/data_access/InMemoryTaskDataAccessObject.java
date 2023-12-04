package data_access;

import api.Todo;
import entity.TaskI;
import use_case.complete_task.CompleteTaskDataAccessInterface;
import use_case.create_task.CreateTaskDataAccessInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTaskDataAccessObject implements CompleteTaskDataAccessInterface, CreateTaskDataAccessInterface{

    private final Map<String, TaskI> tasks = new HashMap<>();
    private Todo todo;

    public InMemoryTaskDataAccessObject(Todo todo){
        this.todo = todo;
    }

    /**
     * @param task the data to save
     */
    public void save(TaskI task) {
        tasks.put(task.getName(), task);
    }

    @Override
    public void addTask(TaskI taskI){
//        String projectName = taskI.getProjectName();
        String taskName = taskI.getName();
        todo.addTask("projectName", taskName);
    }

    @Override
    public void complete(TaskI task) throws IOException {
        task.setComplete(true);
        save(task);
    }

    /**
     * Checks if the task exists in the csv file
     * @param taskName the name of the task we're searching for
     * @return whether the task already exists
     */
    @Override
    public boolean existsByName(String taskName) {
        return tasks.containsKey(taskName);
    }

    /**
     * @param taskName the name of the task we're retrieving
     * @return the task object that is being sent
     */
    @Override
    public TaskI getTask(String taskName) {
        if (this.existsByName(taskName)) {
            return tasks.get(taskName);
        }
        else{
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
