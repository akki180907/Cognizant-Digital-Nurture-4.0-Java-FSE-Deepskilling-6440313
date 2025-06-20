public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Akshaya");
        student.setId("CSE123");
        student.setGrade("A");

        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);

        controller.updateView();

        // Update student details
        controller.setStudentName("S. Akshaya");
        controller.setStudentGrade("A+");

        System.out.println("\nAfter update:");
        controller.updateView();
    }
}
