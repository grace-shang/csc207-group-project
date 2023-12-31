package data_access;

import api.Todo;
import entity.TaskFactory;
import entity.TaskI;
import use_case.complete_task.CompleteTaskDataAccessInterface;
import use_case.create_task.CreateTaskDataAccessInterface;
import use_case.delete_task.DeleteTaskDataAccessInterface;
import use_case.display_task.DisplayTaskDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileTaskDataAccessObject implements CreateTaskDataAccessInterface, CompleteTaskDataAccessInterface, DisplayTaskDataAccessInterface, DeleteTaskDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, TaskI> tasks = new HashMap<>();

    private TaskFactory taskFactory;
    private final Todo todo;

    /**
     * The constructor for the FileTaskDataAccessObject
     * @param file the name of the CSV file
     * @param taskFactory the Task Factory object
     * @param todo the API interface
     * @throws IOException
     */
    public FileTaskDataAccessObject(String file, TaskFactory taskFactory, Todo todo) throws IOException{
        this.taskFactory = taskFactory;
        this.todo = todo;

        csvFile = new File(file);
        headers.put("task_name", 0);
        headers.put("completion", 1);
        headers.put("task_Id", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("task_name,completion,task_Id");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String nameTask = String.valueOf(col[headers.get("task_name")]);
                    String completionTask = String.valueOf(col[headers.get("completion")]);
                    String taskIdstr = String.valueOf(col[headers.get("task_Id")]);
                    boolean complete = Boolean.parseBoolean(completionTask);
                    long taskId = Long.parseLong(taskIdstr);
                    TaskI task = taskFactory.create(nameTask, complete, taskId);
                    tasks.put(nameTask, task);
                }
            }
        }
    }

    /**
     * @param task the data to save
     */
    @Override
    public void save(TaskI task) {
        tasks.put(task.getName(), task);
        this.save();
    }

    /**
     * Deletes all tasks
     * @param tasks a map of all of the active tasks
     */
    @Override
    public void delete(Map<String, TaskI> tasks) {

        for (String task: tasks.keySet()) {
            todo.deleteTask("projectName", task, getTask(task).getTaskId());
        }

        tasks.clear();
    }

    /**
     *
     * @return all the tasks to delete
     */
    @Override
    public Map<String, TaskI> getAllTasksDelete() {
        return tasks;
    }

    /**
     *
     * @return all the tasks
     */
    @Override
    public Map<String, Boolean> getAllTasks() {
        Map<String, Boolean> retTask = new LinkedHashMap<>();

        for (TaskI task: tasks.values()) {
            retTask.put(task.getName(), task.getComplete());
        }

        return retTask;
    }

    /**
     * Writes to the CSV file
     */
    public void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (TaskI task : tasks.values()) {
                String line = String.format("%s,%s,%s",
                        task.getName(), task.getComplete(), task.getTaskId());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param taskName interface that calls the Data Access Interface to add the task
     * @return
     */
    @Override
    public long addTask(String taskName){
        return todo.addTask("projectName", taskName);
    }

    /**
     * @param identifier interface that calls the Data Access Interface to get the method to check whether the task exists in memory
     * @return
     */
    @Override
    public boolean existByName(String identifier) {
        return tasks.containsKey(identifier);
    }

    /**
     * @param task the task we're completing
     * @throws IOException
     */
    @Override
    public void complete(String task) throws IOException {
        todo.completeTask("projectName", task, getTask(task).getTaskId());
        tasks.get(task).setComplete(true);
        this.update(task);
    }

    @Override
    public void update(String taskName) {
        tasks.put(taskName, getTask(taskName));
        save();
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
