package data_access;


import api.Todo;
import entity.TaskFactory;
import entity.TaskI;
import use_case.complete_task.CompleteTaskDataAccessInterface;
import use_case.create_task.CreateTaskDataAccessInterface;
import use_case.display_task.DisplayTaskDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileTaskDataAccessObject implements CreateTaskDataAccessInterface, CompleteTaskDataAccessInterface, DisplayTaskDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, TaskI> tasks = new HashMap<>();

    private TaskFactory taskFactory;
    private final Todo todo;

    public FileTaskDataAccessObject(String file, TaskFactory taskFactory, Todo todo) throws IOException{
        this.taskFactory = taskFactory;
        this.todo = todo;

        csvFile = new File(file);
        headers.put("task_name", 0);
        headers.put("completion", 1);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("task_name,completion");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String nameTask = String.valueOf(col[headers.get("task_name")]);
                    String completionTask = String.valueOf(col[headers.get("completion")]);
                    boolean complete = Boolean.parseBoolean(completionTask);
                    TaskI task = taskFactory.create(nameTask, complete);
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

    @Override
    public Map<String, ArrayList<Object>> getAllTasks() {
        Map<String, ArrayList<Object>> retTask = new LinkedHashMap<>();

        for (TaskI task: tasks.values()) {
            ArrayList<Object> taskInfo = new ArrayList<>();
            taskInfo.add(task.getComplete());
            retTask.put(task.getName(), taskInfo);
        }

        return retTask;
    }

    public void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (TaskI task : tasks.values()) {
                String line = String.format("%s,%s",
                        task.getName(), task.getComplete());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTask(TaskI taskI){
//        String projectName = taskI.getProjectName();
        String taskName = taskI.getName();
        todo.addTask("projectName", taskName);
    }

    @Override
    public boolean existByName(String identifier) {
        return tasks.containsKey(identifier);
    }


    /**
     * @param task the task we're completing
     * @throws IOException
     */
    @Override
    public void complete(TaskI task) throws IOException {
        todo.completeTask("projectName", task.getName());
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
