package data_access;

import api.Todo;
import entity.TaskI;
import use_case.complete_task.CompleteTaskDataAccessInterface;
import use_case.create_task.CreateTaskDataAccessInterface;
import use_case.display_task.DisplayTaskDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTaskDataAccessObject implements CompleteTaskDataAccessInterface, CreateTaskDataAccessInterface, DisplayTaskDataAccessInterface {

    private final Map<String, TaskI> tasks = new HashMap<>();
    private Todo todo;

    public InMemoryTaskDataAccessObject(Todo todo){
        this.todo = todo;
    }
    public InMemoryTaskDataAccessObject(){}

    /**
     * @param task the data to save
     */
    public void save(TaskI task) {
        tasks.put(task.getName(), task);
        save(task);
    }

    @Override
    public long addTask(String taskName){
//        String projectName = taskI.getProjectName();
        return todo.addTask("projectName", taskName);
    }

    @Override
    public boolean existByName(String identifier) {
        return tasks.containsKey(identifier);
    }

    @Override
    public void complete(String task) throws IOException {
        todo.completeTask("projectName", task, getTask(task).getTaskId());
        tasks.get(task).setComplete(true);
        this.update(task);
        System.out.println("Task completed in dao: " + tasks.get(task).getComplete());
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

    @Override
    public void update(String taskName) {
        tasks.put(taskName, getTask(taskName));
    }

    @Override
    public Map<String, Boolean> getAllTasks() {
        Map<String, Boolean> retTask = new HashMap<>();
        for (String task: tasks.keySet()) {
            retTask.put(task, tasks.get(task).getComplete());
        }
        return retTask;
    }
}
