package view;

import interface_adapter.create_task.CreateTaskController;
import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


public class TaskView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "Task Page";

    private final JTextArea taskInputField = new JTextArea();

    private final CreateTaskController createTaskController;
    private final JButton createTask;


    public TaskView(CreateTaskController createTaskController){
        this.createTaskController = createTaskController;

        JLabel title = new JLabel(TaskView.TITLE_LABEl);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel createInfo = new LabelTextPanel(new JLabel(TaskView.CREATE_LABEL), createInputField);

        createTask = newJbutton(TaskView.Create_Button_Label);
        buttons.add(createTask);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
