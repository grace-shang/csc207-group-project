package view;

import interface_adapter.complete_task.CompleteTaskController;
import interface_adapter.complete_task.CompleteTaskState;
import interface_adapter.complete_task.CompleteTaskViewModel;
import interface_adapter.create_task.CreateTaskController;
import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel;

import interface_adapter.display_task.DisplayTaskController;
import interface_adapter.display_task.DisplayTaskState;
import interface_adapter.display_task.DisplayTaskViewModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class TaskView extends JPanel implements ActionListener, PropertyChangeListener{

    public final String viewName = "Task Page";

    private final CreateTaskController createTaskController;
    private final CreateTaskViewModel createTaskViewModel;

    private final CompleteTaskController completeTaskController;
    private final CompleteTaskViewModel completeTaskViewModel;

    private final DisplayTaskViewModel displayTaskViewModel;
    private final DisplayTaskController displayTaskController;

    private final JTextField createInputField = new JTextField(30);

    private final JTextField createTaskProjectInputField = new JTextField(30);
    private final JButton createTask;

    private JFrame frame;


    public TaskView(CreateTaskController createTaskController, CreateTaskViewModel createTaskViewModel,
                    CompleteTaskController completeTaskController, CompleteTaskViewModel completeTaskViewModel, DisplayTaskViewModel displayTaskViewModel, DisplayTaskController displayTaskController){
        this.createTaskController = createTaskController;
        this.createTaskViewModel = createTaskViewModel;
        this.completeTaskController = completeTaskController;
        this.completeTaskViewModel = completeTaskViewModel;
        this.displayTaskViewModel = displayTaskViewModel;
        this.displayTaskController = displayTaskController;

        createTaskViewModel.addPropertyChangeListener(this);
        completeTaskViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(CreateTaskViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        LabelTextPanel createInfo = new LabelTextPanel(
                new JLabel(CreateTaskViewModel.CREATE_TASK_LABEL), createInputField);
        LabelTextPanel createProjectInfo = new LabelTextPanel(new JLabel(CreateTaskViewModel.CREATE_TASK_PROJECT_LABEL),
                createTaskProjectInputField);

        // Buttons
        JPanel buttons = new JPanel();
        createTask = new JButton(CreateTaskViewModel.CREATE_BUTTON_LABEL);
        buttons.add(createTask);
        // completeTask = new JCheckBox();

        frame = new JFrame("Task Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create panel to display tasks
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBorder(LineBorder.createBlackLineBorder());

        //Create scroller as needed vertically and horizontally
        JScrollPane scroller = new JScrollPane(taskPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setPreferredSize(new Dimension(600,600));


        createTask.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createTask)) {
                            CreateTaskState currentState = createTaskViewModel.getState();

                            createTaskController.execute(currentState.getTask());
                            JPanel newTask = new JPanel();
                            newTask.setLayout(new FlowLayout(FlowLayout.LEFT));
                            JLabel newTaskText = new JLabel(currentState.getTask());
                            newTask.add(newTaskText);
                            newTask.add(new JCheckBox());
                            createInputField.setText("");

                            taskPanel.add(newTask);
                            taskPanel.revalidate();
                            taskPanel.repaint();
                        }
                    }
                }
        );

        createInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateTaskState currentState = createTaskViewModel.getState();
                        String text = createInputField.getText() + e.getKeyChar();
                        currentState.setTask(text);
                        createTaskViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(createInfo);
        this.add(buttons);
        //add panel and scroller to frame
        this.add(taskPanel);
        this.add(scroller);

        TaskView.this.displayTaskController.execute(); //Display all the existing tasks in the CSV


    }

    public void setComponentNames() {
        createTask.setName("Create Task Button");
        createInputField.setName("Text Box");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("display")) {
            DisplayTaskState state = (DisplayTaskState) evt.getNewValue();
//            for (String task : gfg.keySet()) {
//
//            }
        }
    }

}
