package view;

import interface_adapter.create_task.CreateTaskController;
import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel;
import interface_adapter.mainpage.TaskViewModel;
import interface_adapter.ViewModel;

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

    private final CreateTaskController createTaskController;
    private final TaskViewModel taskViewModel;

    private final JTextArea createInputField = new JTextArea();

    private final JButton createTask;


    public TaskView(CreateTaskController createTaskController, TaskViewModel taskViewModel){
        this.createTaskController = createTaskController;
        this.taskViewModel = taskViewModel;

        taskViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(TaskViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        LabelTextPanel createInfo = new LabelTextPanel(new JLabel(TaskViewModel.CREATE_TASK_LABEL), createInputField);

        createTask = new Jbutton(TaskViewModel.CREATE_BUTTON_LABEL);
        buttons.add(createTask);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
