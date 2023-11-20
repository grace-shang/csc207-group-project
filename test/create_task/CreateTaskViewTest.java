package create_task;

import app.Main;
import data_access.FileTaskDataAccessObject;
import entity.AllTaskFactory;
import entity.TaskFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import api.Todo;
import api.ToDoList;
import view.TaskView;

import static org.junit.Assert.assertNotNull;

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

    // END OF FIXED TEST CODE
    public JButton getButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        TaskView tv = (TaskView) jp2.getComponent(0);

        JPanel buttons = (JPanel) tv.getComponent(4);

        return (JButton) buttons.getComponent(2); // this should be the clear button
    }

    /**
     *
     * Test that the Clear button is present and where it is expected to be
     */
    @org.junit.Test
    public void testClearButtonPresent() {
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Clear"));
    }

    /**
     *
     * Test that pressing the Clear button deletes all users. This test first
     * adds two users to the CSV file, then starts the program, then clicks the Clear button,
     * and then checks that the file's length has decreased.
     */
    @org.junit.Test
    public void testClearUsersDeletedUsersFromFile() {

        addTwoTasks();
        Main.main(null);
        JButton button = getButton();

        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.
        createCloseTimer().start();

        button.doClick();

        // will continue execution here after the JDialog is closed

        // users.csv format
        //username, password,timestamp (in format returned by a call like LocalDateTime.now())
        //example
        //user1,pass1,2023-10-12T14:46:26.733518

        //check the users.csv file to ensure the users are gone
        try {
            int lines = countLines();
            System.out.println("lines left = " + lines);
            assert(lines <= 1);

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
    public void testClearUsersPopUpShown() {

        addTwoTasks();

        Main.main(null);
        JFrame app = null;

        JButton button = getButton();


        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.
        createCloseTimer().start();

        //click the button
        button.doClick();

        // will continue execution here after the JDialog is closed

        // confirm a popUp was discovered
        assert(popUpDiscovered);
        System.out.println("popup was detected successfully.");

    }

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
                            ClearUsersTest.message = s;
                            ClearUsersTest.popUpDiscovered = true;

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
