import java.util.Scanner;

public class studentgradetrackerGUI {

    private static final int MAX_STUDENTS = 5;
    private static final int MAX_GRADES = 5;

    private static class Student {
        String name;
        int[] grades;
        int gradeCount;

        Student(String name) {
            this.name = name;
            this.grades = new int[MAX_GRADES];
            this.gradeCount = 0;
        }

        boolean addGrade(int grade) {
            if (gradeCount < MAX_GRADES) {
                grades[gradeCount++] = grade;
                return true;
            }
            return false;
        }

        double getAverage() {
            if (gradeCount == 0) return 0;
            int sum = 0;
            for (int i = 0; i < gradeCount; i++) {
                sum += grades[i];
            }
            return (double) sum / gradeCount;
        }

        int getHighest() {
            if (gradeCount == 0) return 0;
            int highest = grades[0];
            for (int i = 1; i < gradeCount; i++) {
                if (grades[i] > highest) highest = grades[i];
            }
            return highest;
        }

        int getLowest() {
            if (gradeCount == 0) return 0;
            int lowest = grades[0];
            for (int i = 1; i < gradeCount; i++) {
                if (grades[i] < lowest) lowest = grades[i];
            }
            return lowest;
        }
    }

    private Student[] students;
    private int studentCount;
    private Scanner scanner;

    public studentgradetrackerGUI() {
        students = new Student[MAX_STUDENTS];
        studentCount = 0;
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== Student Grade Tracker (Array version) ===");
        boolean running = true;
        while (running) {
            System.out.println("\nOptions:");
            System.out.println("1. Add new student");
            System.out.println("2. Add grades for student");
            System.out.println("3. Display summary report");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            int choice = inputInt();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGrades();
                    break;
                case 3:
                    displayReport();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, please select 1-4.");
            }
        }
    }

    private void addStudent() {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Maximum student limit reached (" + MAX_STUDENTS + ").");
            return;
        }
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        students[studentCount++] = new Student(name);
        System.out.println("Student \"" + name + "\" added.");
    }

    private void addGrades() {
        if (studentCount == 0) {
            System.out.println("No students available. Please add students first.");
            return;
        }
        System.out.println("Select student to add grades:");
        for (int i = 0; i < studentCount; i++) {
            System.out.printf("%d. %s%n", i + 1, students[i].name);
        }
        System.out.print("Enter student number: ");
        int index = inputInt() - 1;
        if (index < 0 || index >= studentCount) {
            System.out.println("Invalid student number.");
            return;
        }

        Student student = students[index];
        System.out.printf("Adding grades for %s (Max %d grades).%n", student.name, MAX_GRADES);
        System.out.println("Enter grades 0-100. Enter -1 to stop.");
        while (student.gradeCount < MAX_GRADES) {
            System.out.print("Enter grade: ");
            int grade = inputInt();
            if (grade == -1) break;
            if (grade < 0 || grade > 100) {
                System.out.println("Grade must be between 0 and 100.");
                continue;
            }
            boolean success = student.addGrade(grade);
            if (success) {
                System.out.println("Grade added.");
            } else {
                System.out.println("Grade limit reached for this student.");
                break;
            }
        }
    }

    private void displayReport() {
        if (studentCount == 0) {
            System.out.println("No student data available.");
            return;
        }
        System.out.println("\n=== Summary Report ===");
        for (int i = 0; i < studentCount; i++) {
            Student s = students[i];
            System.out.println("Student: " + s.name);
            if (s.gradeCount == 0) {
                System.out.println("  No grades entered.");
            } else {
                System.out.print("  Grades: ");
                for (int j = 0; j < s.gradeCount; j++) {
                    System.out.print(s.grades[j] + (j < s.gradeCount - 1 ? ", " : ""));
                }
                System.out.println();
                System.out.printf("  Average: %.2f%n", s.getAverage());
                System.out.println("  Highest: " + s.getHighest());
                System.out.println("  Lowest: " + s.getLowest());
            }
            System.out.println();
        }
    }

    private int inputInt() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input, enter a valid number: ");
            }
        }
    }

    public static void main(String[] args) {
        StudentGradeTrackerArray app = new StudentGradeTrackerArray();
        app.run();
    }
}
