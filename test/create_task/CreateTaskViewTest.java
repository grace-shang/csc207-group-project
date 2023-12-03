package create_task;

import app.Main;
import data_access.FileTaskDataAccessObject;
import entity.AllTaskFactory;
import entity.TaskFactory;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import api.Todo;
import api.ToDoList;
import interface_adapter.create_task.CreateTaskViewModel;
import org.junit.Test;
import view.LabelTextPanel;
import view.TaskView;

import static org.junit.Assert.*;

public class CreateTaskViewTest {
    static String message = "";

    /**
     * ensures there are at least 2 tasks in the CSV file for testing purposes
     */
    public void addTwoTasks() {
        TaskFactory tf = new AllTaskFactory();
        FileTaskDataAccessObject ftdao;
        Todo todo = new ToDoList();

        try {
            ftdao = new FileTaskDataAccessObject("./tasks.csv", tf, todo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ftdao.save(tf.create("task1", false));
        ftdao.save(tf.create("task2", false));
    }

    @Test
    public void testCreateTaskButtonPresent() {
        SwingUtilities.invokeLater(() -> {
            JFrame app = new JFrame("Test Frame");
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Main.main(null);

            // Find the "Create Task" button
            JButton createTaskButton = findCreateTaskButton(app);

            // Assert that the button is not null
            assertNotNull("Create Task button not found", createTaskButton);

            // Assert the text on the button
            assertEquals("Create Task", createTaskButton.getText());

            // Clean up the application frame
            app.dispose();
        });

        // Add any necessary waiting time or conditions for Swing components to update
        try {
            Thread.sleep(2000); // Adjust waiting time if needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private JButton findCreateTaskButton(JFrame frame) {
        for (Component component : frame.getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                JPanel mainPanel = (JPanel) component;
                for (Component subComponent : mainPanel.getComponents()) {
                    if (subComponent instanceof TaskView) {
                        TaskView taskView = (TaskView) subComponent;
                        return taskView.getCreateTaskButton();
                    }
                }
            }
        }
        throw new IllegalStateException("Create Task button not found");
    }

    @org.junit.Test
    public void testCreateSavesTaskIntoFile() throws IOException {
        addTwoTasks();
        Main.main(null);
        ArrayList<String> names = getNames();
        System.out.println("Names In File: " + names);
        assertTrue("Task1 is not found in the file", names.contains("task1"));
        assertTrue("Task2 is not found in the file", names.contains("task2"));

    }

    @org.junit.Test
    public void testCreateDisplaysNewTask(){

    }


    @org.junit.Test
    public void testLinesInCSVAfterCreate(){addTwoTasks();
        addTwoTasks();
        Main.main(null);

        try {
            int lines = countLines();
            System.out.println("lines in csv file = " + lines);
            assert(lines == 3);
        } catch (IOException e){
            throw new RuntimeException(e);
        }}


    private static ArrayList<String> getNames() throws IOException {
        ArrayList<String> listOfNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.csv"))) {
            String line;
            // Skip the header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 1) {
                    // Trim to remove any leading/trailing whitespaces
                    listOfNames.add(values[0].trim());
                }
            }
            return listOfNames;
        }
    }
    private static int countLines() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("tasks.csv"));
        int lineCount = 0;
        while (reader.readLine() != null) {
            lineCount++;
        }
        return lineCount;
    }

}
