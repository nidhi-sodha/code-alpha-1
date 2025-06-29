import javax.swing.*;
import java.awt.*;

public class StudentGradeTrackerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentGradeTrackerApp().createAndShowGUI());
    }

    private studentTableModel tableModel = new studentTableModel();

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Student Grade Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Form fields
        JTextField nameField = new JTextField(10);
        JTextField idField = new JTextField(10);
        JTextField gradeField = new JTextField(5);
        JButton addButton = new JButton("Add Student");

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            String gradeStr = gradeField.getText().trim();

            if (name.isEmpty() || id.isEmpty() || gradeStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.");
                return;
            }

            try {
                double grade = Double.parseDouble(gradeStr);
                Student student = new Student(name, id, grade);
                tableModel.addStudent(student);

                // Clear fields
                nameField.setText("");
                idField.setText("");
                gradeField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid grade input.");
            }
        });

        // Form layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        inputPanel.add(addButton);

        frame.getContentPane().add(BorderLayout.NORTH, inputPanel);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.setVisible(true);
    }
}
