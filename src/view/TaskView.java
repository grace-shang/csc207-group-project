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
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


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
    private final JPanel taskPanel = new JPanel();

    private ArrayList<JLabel> taskLabels = new ArrayList<>();

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
        displayTaskViewModel.addPropertyChangeListener(this);

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

        frame = new JFrame("Task Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create panel to display tasks
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
                        }
                    }
                }
        );

        createTask.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(createTask)) {
                            CreateTaskState currentState = createTaskViewModel.getState();

                            if (!Objects.equals(currentState.getTask(), "")){
                                createTaskController.execute(currentState.getTask());
                                JLabel taskForLabel = new JLabel(currentState.getTask());
                                taskLabels.add(taskForLabel);
                                JPanel newTask = new JPanel();
                                newTask.setLayout(new FlowLayout(FlowLayout.LEFT));

                                // Implementation for checkbox item listener
                                JCheckBox check = new JCheckBox(currentState.getTask());

                                check.addItemListener(
                                        new ItemListener() {
                                            @Override
                                            public void itemStateChanged(ItemEvent e) {
                                                if (check.isSelected()) {
                                                    CompleteTaskState completeTaskState = completeTaskViewModel.getState();
                                                    System.out.println("checked");

                                                    try {
                                                        completeTaskController.execute(completeTaskState.getTaskName());
                                                    } catch (IOException exception) {
                                                        throw new RuntimeException(exception);
                                                    }
                                                }
                                            }
                                        }

                                );

                                newTask.add(check);

                                createInputField.setText("");
                                currentState.setTask("");

                                taskPanel.add(newTask);
                                taskPanel.revalidate();
                                taskPanel.repaint();
                            } else{
                                JOptionPane.showMessageDialog(TaskView.this, "An Empty Task Can't Be Added");
                            }
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
        TaskView.this.displayTaskController.execute(); //Display all the existing tasks in the CSV

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(createInfo);
        this.add(buttons);
        //add panel and scroller to frame
        this.add(taskPanel);
        this.add(scroller);

    }

    public JButton getCreateTaskButton() {
        for (Component component : this.getComponents()) {
            if (component instanceof JPanel) {
                JPanel buttonsPanel = (JPanel) component;
                if (buttonsPanel.getComponentCount() > 0 && buttonsPanel.getComponent(0) instanceof JButton) {
                    return (JButton) buttonsPanel.getComponent(0);
                }
            }
        }

        throw new IllegalStateException("Create Task button not found");
    }

    public JTextField getCreateTaskInputField(){
        return createInputField;
    }

    public String getTaskText(int index) {
        if (index >= 0 && index < taskLabels.size()) {
            return taskLabels.get(index).getText();
        }
        throw new IndexOutOfBoundsException("Invalid task index");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("display")) {
            DisplayTaskState state = (DisplayTaskState) evt.getNewValue();
            for (int i = 0; i < state.getTasks().size(); i++) {
                //String task: state.getTasks()
                JPanel newTask = new JPanel();
                newTask.setLayout(new FlowLayout(FlowLayout.LEFT));
                // boolean complete = (boolean) state.getTaskInfo().get(i).get(0);
                // System.out.println(complete);
                JCheckBox check = new JCheckBox(state.getTasks().get(i));

                check.addItemListener(
                        new ItemListener() {
                            @Override
                            public void itemStateChanged(ItemEvent e) {
                                if (check.isSelected()) {
                                    CompleteTaskState completeTaskState = completeTaskViewModel.getState();
                                    System.out.println("checked");

                                    try {
                                        completeTaskController.execute(completeTaskState.getTaskName());
                                    } catch (IOException exception) {
                                        throw new RuntimeException(exception);
                                    }
                                }
                            }
                        }

                );

                newTask.add(check);
                taskPanel.add(newTask);
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        } else if (evt.getPropertyName().equals("create")) {
            CreateTaskState state = (CreateTaskState) evt.getNewValue();
            if (state.getTaskError() != null) {
                JOptionPane.showMessageDialog(TaskView.this, "An Empty Task Can't Be Added");
            } else {
                JLabel taskForLabel = new JLabel(state.getTask());
                taskLabels.add(taskForLabel);
                JPanel newTask = new JPanel();
                newTask.setLayout(new FlowLayout(FlowLayout.LEFT));
                // JLabel newTaskText = new JLabel(currentState.getTask());
                newTask.add(new JCheckBox(state.getTask(), true));
                // newTask.add(newTaskText);
                createInputField.setText("");
                state.setTask("");

                taskPanel.add(newTask);
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        }
    }

}
