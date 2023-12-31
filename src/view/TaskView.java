package view;

import interface_adapter.complete_task.CompleteTaskController;
import interface_adapter.complete_task.CompleteTaskState;
import interface_adapter.complete_task.CompleteTaskViewModel;
import interface_adapter.create_task.CreateTaskController;
import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel;

import interface_adapter.delete_task.DeleteTaskController;
import interface_adapter.delete_task.DeleteTaskState;
import interface_adapter.delete_task.DeleteTaskViewModel;
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
    private final DeleteTaskViewModel deleteTaskViewModel;
    private final DeleteTaskController deleteTaskController;

    private final JTextField createInputField = new JTextField(30);

    private final JTextField createTaskProjectInputField = new JTextField(30);
    private final JButton createTask, deleteTask;
    private final JPanel taskPanel = new JPanel();

    private ArrayList<JLabel> taskLabels = new ArrayList<>();

    private JFrame frame;


    /**
     * @param createTaskController the controller for create task
     * @param createTaskViewModel the view model for create task
     * @param completeTaskController the controller for complete task
     * @param completeTaskViewModel the view model for complete task
     * @param displayTaskViewModel the view model for display task
     * @param displayTaskController the controller for display task
     */
    public TaskView(CreateTaskController createTaskController, CreateTaskViewModel createTaskViewModel,
                    CompleteTaskController completeTaskController, CompleteTaskViewModel completeTaskViewModel, DisplayTaskViewModel displayTaskViewModel, DisplayTaskController displayTaskController, DeleteTaskViewModel deleteTaskViewModel, DeleteTaskController deleteTaskController){
        this.createTaskController = createTaskController;
        this.createTaskViewModel = createTaskViewModel;
        this.completeTaskController = completeTaskController;
        this.completeTaskViewModel = completeTaskViewModel;
        this.displayTaskViewModel = displayTaskViewModel;
        this.displayTaskController = displayTaskController;
        this.deleteTaskViewModel = deleteTaskViewModel;
        this.deleteTaskController = deleteTaskController;

        createTaskViewModel.addPropertyChangeListener(this);
        completeTaskViewModel.addPropertyChangeListener(this);
        displayTaskViewModel.addPropertyChangeListener(this);
        deleteTaskViewModel.addPropertyChangeListener(this);

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

        deleteTask = new JButton("Delete");
        buttons.add(deleteTask);

        frame = new JFrame("Planr");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create panel to display tasks
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBorder(LineBorder.createBlackLineBorder());

        //Create scroller as needed vertically and horizontally
        JScrollPane scroller = new JScrollPane(taskPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setPreferredSize(new Dimension(600,600));

        deleteTask.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(deleteTask)) {
                            TaskView.this.deleteTaskController.execute();
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

    /**
     * @return the create task button
     */
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

    public JCheckBox getCompleteTaskJCheck() {
        for (Component component : this.getComponents()) {
            if (component instanceof JPanel) {
                JPanel buttonsPanel = (JPanel) component;
                if (buttonsPanel.getComponentCount() > 0 && buttonsPanel.getComponent(0) instanceof JCheckBox) {
                    return (JCheckBox) buttonsPanel.getComponent(0);
                }
            }
        }

        throw new IllegalStateException("Complete Task button not found");
    }

    /**
     * @return the input field that was created
     */
    public JTextField getCreateTaskInputField(){
        return createInputField;
    }

    /**
     * @param index the index of which we want to return the task name
     * @return the name of the task at index
     */
    public String getTaskText(int index) {
        if (index >= 0 && index < taskLabels.size()) {
            return taskLabels.get(index).getText();
        }
        throw new IndexOutOfBoundsException("Invalid task index");
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("display")) {
            DisplayTaskState state = (DisplayTaskState) evt.getNewValue();
            for (int i = 0; i < state.getTasks().size(); i++) {
                JPanel newTask = new JPanel();
                newTask.setLayout(new FlowLayout(FlowLayout.LEFT));
                String taskName = state.getTasks().get(i);
                Boolean completion = state.getTaskInfo().get(i);

                CompleteTaskState completeTaskState = completeTaskViewModel.getState();
                JCheckBox check = new JCheckBox(taskName, completion);
                check.addItemListener(
                        new ItemListener() {
                            @Override
                            public void itemStateChanged(ItemEvent e) {
                                if (check.isSelected()) {
                                    completeTaskState.setTaskCompletion(taskName);
                                    try {
                                        completeTaskController.execute(completeTaskState.getTaskName());
                                        // JOptionPane.showMessageDialog(TaskView.this, "Task completed!");
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
                newTask.add(new JCheckBox(state.getTask(), true));
                createInputField.setText("");
                state.setTask("");

                taskPanel.add(newTask);
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        }
        else if (evt.getPropertyName().equals("delete")) {
            taskPanel.removeAll();
            taskPanel.revalidate();
            taskPanel.repaint();
        }
    }

}
