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

    }
}
