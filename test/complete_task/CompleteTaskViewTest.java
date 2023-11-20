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

import static org.junit.Assert.assertNotNull;

public class CompleteTaskViewTest {
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

    // Fix depending on implementation
    @org.junit.Test
    public void testCompleteButtonPresent() {
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Complete"));
    }

    @org.junit.Test
    public void testCompleteDoesNotRemoveTasks() {

        addTwoTasks();
        Main.main(null);
        JButton button = getButton();

        createCloseTimer().start();

        button.doClick();

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

    private static int countLines() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("users.csv"));
        int lineCount = 0;
        while (reader.readLine() != null) {
            lineCount++;
        }
        return lineCount;
    }

}
