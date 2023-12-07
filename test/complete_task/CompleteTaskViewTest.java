package complete_task;

import api.ToDoList;
import api.Todo;
import app.Main;
import data_access.FileTaskDataAccessObject;
import entity.AllTaskFactory;
import entity.TaskFactory;
import view.TaskView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CompleteTaskViewTest {
    static String message = "";

    public JButton getButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // Sees if the window was found

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        TaskView tv = (TaskView) jp2.getComponent(0);

        JPanel buttons = (JPanel) tv.getComponent(4);

        return (JButton) buttons.getComponent(2); // this should be the clear button
    }


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
        ftdao.save(tf.create("task1", false, todo.addTask("projectName", "task1")));
        ftdao.save(tf.create("task2", false, todo.addTask("projectName", "task2")));
    }

    public void completeTaskWithGUI(){
        SwingUtilities.invokeLater(() -> {
            JFrame app = new JFrame("Test Frame");
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Main.main(null);
            JTextField inputTextField = findCreateTaskInputField(app);
            JButton createTaskButton = findCreateTaskButton(app);
            JCheckBox completeTaskJCheck = findCompleteTaskJCheck(app);

            assertNotNull("Create Task input text field not found", inputTextField);

            inputTextField.setText("task 1");
            createTaskButton.doClick();

            inputTextField.setText("task 2");
            createTaskButton.doClick();

            completeTaskJCheck.doClick();

            app.dispose();
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testCompleteButtonPresent() {
        SwingUtilities.invokeLater(() -> {
            JFrame app = new JFrame("Test Frame");
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Main.main(null);

            JCheckBox completeTaskCheck = findCompleteTaskJCheck(app);

            assertNotNull("Create Task button not found", completeTaskCheck);

            app.dispose();
        });

        try {
            Thread.sleep(2000); // Adjust waiting time if needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testCompleteTaskJCheckText() {
        SwingUtilities.invokeLater(() -> {
            JFrame app = new JFrame("Test Frame");
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Main.main(null);

            JCheckBox completeTaskCheck = findCompleteTaskJCheck(app);
            System.out.println(completeTaskCheck.getText());

            assertEquals("task 1", completeTaskCheck.getText());
            app.dispose();
        });

        try {
            Thread.sleep(2000); // Adjust waiting time if needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testCompleteDoesNotRemoveTasks() {
        JFrame app = new JFrame("Test Frame");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addTwoTasks();
        Main.main(null);
        JCheckBox completeTaskCheck = findCompleteTaskJCheck(app);

        createCloseTimer().start();

        completeTaskCheck.doClick();

        // checks that the amount of users left is still 2 (i.e. complete task hasn't deleted any)

        try {
            int lines = countLines();
            System.out.println("lines left = " + lines);
            assert(lines == 2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *
     * This test is the same as above, but it additionally checks that the JDialog contains the names of
     * all users deleted from the file.
     */

    @org.junit.Test
    public void testClearUsersReturnedUsersDeleted() throws InterruptedException {

        addTwoTasks();
        message = "";

        Main.main(null);

        JButton button = getButton();

        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.
        createCloseTimer().start();

        //click the button
        button.doClick();

        // will continue execution here after the JDialog is closed

        // check the message
        assert(message.contains("user1") && message.contains("user2"));
    }

    private Timer createCloseTimer() {
        ActionListener close = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {

                        JDialog dialog = (JDialog)window;

                        // this ignores old dialogs
                        if (dialog.isVisible()) {
                            String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                    .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
                            System.out.println("message = " + s);

                            // store the information we got from the JDialog
                            // ClearUsersTest.message = s;
                            // ClearUsersTest.popUpDiscovered = true;

                            System.out.println("disposing of..." + window.getClass());
                            window.dispose();
                        }
                    }
                }
            }

        };

        Timer t = new Timer(1000, close);
        t.setRepeats(false);
        return t;
    }

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

    private JCheckBox findCompleteTaskJCheck(JFrame frame) {
        for (Component component : frame.getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                JPanel mainPanel = (JPanel) component;
                for (Component subComponent : mainPanel.getComponents()) {
                    if (subComponent instanceof TaskView) {
                        TaskView taskView = (TaskView) subComponent;
                        return taskView.getCompleteTaskJCheck();
                    }
                }
            }
        }
        throw new IllegalStateException("Create Task button not found");
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

}
