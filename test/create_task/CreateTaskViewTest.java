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

    public JButton getButton(){
        Frame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        assertNotNull(app); //check if a window was opened
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel taskViewPanel = (JPanel) jp.getComponent(0);
        TaskView taskView = (TaskView) taskViewPanel.getComponent(0);
        JPanel buttonsPanel = (JPanel) taskView.getComponent(2);
        return (JButton) buttonsPanel.getComponent(0);
    }


    @org.junit.Test
    public void testCompleteButtonPresent(){
        Main.main(null);
        JButton button = getButton();
        assert(button.getText().equals("Create Task"));
    }


    @org.junit.Test
    public void testCreateSavesTaskIntoFile(){}

    @org.junit.Test
    public void testCreateDisplaysNewTask(){}


    @org.junit.Test
    public void testLinesInCSVAfterCreate(){}



    private static int countLines() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("tasks.csv"));
        int lineCount = 0;
        while (reader.readLine() != null) {
            lineCount++;
        }
        return lineCount;
    }

}
