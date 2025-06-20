public class Main {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager(5);

        manager.addEmployee(new Employee("E101", "Alice", "Manager", 80000));
        manager.addEmployee(new Employee("E102", "Bob", "Developer", 60000));
        manager.addEmployee(new Employee("E103", "Charlie", "Analyst", 50000));

        manager.displayAll();

        System.out.println("\nSearching for E102:");
        Employee found = manager.searchById("E102");
        System.out.println(found != null ? found : "Not found");

        System.out.println("\nDeleting E101:");
        manager.deleteById("E101");

        manager.displayAll();
    }
}
