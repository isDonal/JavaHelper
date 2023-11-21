import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

class CheckBoxList extends JList<JCheckBox> {
    CheckBoxList(DefaultListModel<JCheckBox> model) {
        super(model);
        setCellRenderer(new CheckBoxRenderer());
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                int index = locationToIndex(event.getPoint());
                if (index != -1) {
                    JCheckBox checkbox = getModel().getElementAt(index);
                    checkbox.setSelected(!checkbox.isSelected());
                    repaint();
                }
            }
        });
    }
}

class CheckBoxRenderer implements ListCellRenderer<JCheckBox> {
    public Component getListCellRendererComponent(JList<? extends JCheckBox> list, JCheckBox value, int index,
            boolean isSelected, boolean cellHasFocus) {
        value.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        value.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
        value.setEnabled(list.isEnabled());
        value.setFont(list.getFont());
        value.setFocusPainted(false);
        value.setBorderPainted(true);
        return value;
    }
}

public class CheckBoxListExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CheckBox JList Example");
        DefaultListModel<JCheckBox> listModel = new DefaultListModel<>();
        JCheckBox checkbox1 = new JCheckBox("Item 1");
        JCheckBox checkbox2 = new JCheckBox("Item 2");
        JCheckBox checkbox3 = new JCheckBox("Item 3");
        JCheckBox checkbox4 = new JCheckBox("Item 4");

        listModel.addElement(checkbox1);
        listModel.addElement(checkbox2);
        listModel.addElement(checkbox3);
        listModel.addElement(checkbox4);

        CheckBoxList checkboxList = new CheckBoxList(listModel);

        JScrollPane scrollPane = new JScrollPane(checkboxList);
        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
