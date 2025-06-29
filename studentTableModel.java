import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class studentTableModel extends AbstractTableModel {
    private String[] columns = {"Name", "ID", "Grade"};
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
        fireTableRowsInserted(students.size() - 1, students.size() - 1);
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Student s = students.get(row);
        switch (col) {
            case 0: return s.getName();
            case 1: return s.getId();
            case 2: return s.getGrade();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }
}
