package view;

import interface_adapter.create_task.CreateTaskController;
import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel;

import interface_adapter.ViewModel;
import view.LabelTextPanel;

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
    private final CreateTaskViewModel createTaskViewModel;

    private final JTextArea createInputField = new JTextArea();

    private final JTextArea createTaskProjectInputField = new JTextArea();
    private final JButton createTask;


    public TaskView(CreateTaskController createTaskController, CreateTaskViewModel createTaskViewModel){
        this.createTaskController = createTaskController;
        this.createTaskViewModel = createTaskViewModel;

        createTaskViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(CreateTaskViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        LabelTextPanel createInfo = new LabelTextPanel(
                new JLabel(CreateTaskViewModel.CREATE_TASK_LABEL), createInputField);
        LabelTextPanel createProjectInfo = new LabelTextPanel(new JLabel(CreateTaskViewModel.CREATE_TASK_PROJECT_LABEL),
                createTaskProjectInputField);

        JPanel buttons = new JPanel();
        createTask = new JButton(CreateTaskViewModel.CREATE_BUTTON_LABEL);
        buttons.add(createTask);


        createTask.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createTask)) {
                            CreateTaskState currentState = createTaskViewModel.getState();

                            createTaskController.execute(
                                    currentState.getTask(),
                                    String.valueOf(currentState.getProject())
                            );
                        }
                        CreateTaskState currentState = createTaskViewModel.getState();
                        JLabel final_task = new JLabel(currentState.getTask());

                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
