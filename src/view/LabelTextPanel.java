package view;

import javax.swing.*;

public class LabelTextPanel extends JPanel{
    LabelTextPanel(JLabel label, JTextArea textField) {
        this.add(label);
        this.add(textField);
    }
}
}
