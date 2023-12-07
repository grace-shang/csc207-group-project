package view;

import javax.swing.*;

public class LabelTextPanel extends JPanel{
    /**
     * @param label new jlabel that helps create label text panel
     * @param textField new text field that helps create label text panel
     */
    LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}


