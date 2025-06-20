public class EmployeeManager {
    private Employee[] employees;
    private int count;

    public EmployeeManager(int capacity) {
        employees = new Employee[capacity];
        count = 0;
    }

    public void addEmployee(Employee emp) {
        if (count < employees.length) {
            employees[count++] = emp;
            System.out.println("Added: " + emp);
        } else {
            System.out.println("Employee array is full.");
        }
    }

    public Employee searchById(String id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId().equals(id)) {
                return employees[i];
            }
        }
        return null;
    }

    public void deleteById(String id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId().equals(id)) {
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--count] = null;
                System.out.println("Deleted employee with ID: " + id);
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public void displayAll() {
        System.out.println("Employee List:");
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }
}
