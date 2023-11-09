package data_access;

import entity.TaskI;
import use_case.create_task.CreateTaskDataAccessInterface;
import api.Todo;

public class FileTaskDataAccessObject implements CreateTaskDataAccessInterface{
    private Todo todo;

    public FileTaskDataAccessObject(Todo todo){
        this.todo = todo;
    }

    @Override
    public void save(TaskI taskI) {

    }

    public void addTask(TaskI taskI){
        String projectName = taskI.getProjectName();
        String taskName = taskI.getName();
        todo.addTask(projectName, taskName);
    }
}
