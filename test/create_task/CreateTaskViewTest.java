package create_task;

import app.Main;
import data_access.FileTaskDataAccessObject;
import data_access.InMemoryTaskDataAccessObject;
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
import org.json.JSONException;
import org.junit.Test;
import use_case.create_task.*;
import view.LabelTextPanel;
import view.TaskView;

import static org.junit.Assert.*;

public class CreateTaskViewTest {
    static String message = "";
    static boolean popUpDiscovered = false;

    public void addTwoTasks() {
        TaskFactory tf = new AllTaskFactory();
        FileTaskDataAccessObject ftdao;
        Todo todo = new ToDoList();

        try {
            ftdao = new FileTaskDataAccessObject("./tasks.csv", tf, todo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ftdao.save(tf.create("task1", false, todo.addTask("projectName", "task1")));
        ftdao.save(tf.create("task2", false, todo.addTask("projectName", "task2")));
    }

    public void addTwoTasksWithGui(){
        SwingUtilities.invokeLater(() -> {
            JFrame app = new JFrame("Test Frame");
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Main.main(null);
            JTextField inputTextField = findCreateTaskInputField(app);
            JButton createTaskButton = findCreateTaskButton(app);

            assertNotNull("Create Task input text field not found", inputTextField);
            assertNotNull("Create Task button not found", createTaskButton);

            inputTextField.setText("task1");
            createTaskButton.doClick();

            inputTextField.setText("task2");
            createTaskButton.doClick();

            app.dispose();
        });
        try {
            Thread.sleep(2000);
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


    private JTextField findCreateTaskInputField(JFrame frame) {
        for (Component component : frame.getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                JPanel mainPanel = (JPanel) component;
                for (Component subComponent : mainPanel.getComponents()) {
                    if (subComponent instanceof TaskView) {
                        TaskView taskView = (TaskView) subComponent;
                        return taskView.getCreateTaskInputField();
                    }
                }
            }
        }
        throw new IllegalStateException("Create Task input text field not found");
    }

    private TaskView findTaskView(JFrame frame) {
        for (Component component : frame.getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                JPanel mainPanel = (JPanel) component;
                for (Component subComponent : mainPanel.getComponents()) {
                    if (subComponent instanceof TaskView) {
                        return (TaskView) subComponent;
                    }
                }
            }
        }
        throw new IllegalStateException("TaskView not found");
    }

    @Test
    public void testCreateTaskButtonPresent() {
        SwingUtilities.invokeLater(() -> {
            JFrame app = new JFrame("Test Frame");
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Main.main(null);

            JButton createTaskButton = findCreateTaskButton(app);

            assertNotNull("Create Task button not found", createTaskButton);

            assertEquals("Create Task", createTaskButton.getText());
            app.dispose();
        });

        try {
            Thread.sleep(2000); // Adjust waiting time if needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateTaskInputFieldPresent() {
        SwingUtilities.invokeLater(() -> {
            JFrame app = new JFrame("Test Frame");
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Main.main(null);

            JTextField inputTextField = findCreateTaskInputField(app);

            assertNotNull("Create Task input text field not found", inputTextField);
            app.dispose();
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTaskWithEmptyInput(){
        SwingUtilities.invokeLater(() -> {
            JFrame app = new JFrame("Test Frame");
            popUpDiscovered = false;
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Main.main(null);
            JButton createTaskButton = findCreateTaskButton(app);
            createTaskButton.doClick();
            assert(popUpDiscovered);
            app.dispose();
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testMessageTaskNeeded(){
        SwingUtilities.invokeLater(() -> {
            JFrame app = new JFrame("Test Frame");
            message = "";
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Main.main(null);
            JButton createTaskButton = findCreateTaskButton(app);
            createTaskButton.doClick();
            assert(message.contains("An Empty Task Can't Be Added"));
            app.dispose();
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCreateSavesTaskIntoFile() throws IOException {
        addTwoTasksWithGui();
        Main.main(null);
        ArrayList<String> names = getNames();
        System.out.println("Names In File: " + names);
        assertTrue("Task1 is not found in the file", names.contains("task1"));
        assertTrue("Task2 is not found in the file", names.contains("task2"));

    }

    @Test
    public void testCreateDisplaysNewTask(){
        addTwoTasksWithGui();
        SwingUtilities.invokeLater(() -> {
            JFrame app = new JFrame("Test Frame");
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Main.main(null);

            TaskView taskView = findTaskView(app);
            assertNotNull("TaskView not found", taskView);
            assertEquals("task1", taskView.getTaskText(0));
            assertEquals("task2", taskView.getTaskText(1));

            app.dispose();
        });
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }


    @org.junit.Test
    public void testLinesInCSVAfterCreate(){addTwoTasks();
        addTwoTasksWithGui();
        Main.main(null);

        try {
            int lines = countLines();
            System.out.println("lines in csv file = " + lines);
            assert(lines >= 3);
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
