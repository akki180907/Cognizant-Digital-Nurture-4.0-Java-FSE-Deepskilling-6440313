public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        manager.addTask(new Task("T101", "Design UI", "Pending"));
        manager.addTask(new Task("T102", "Develop API", "In Progress"));
        manager.addTask(new Task("T103", "Test Module", "Pending"));

        manager.displayTasks();

        System.out.println("\nSearching for task T102:");
        Task found = manager.searchTask("T102");
        System.out.println(found != null ? found : "Task not found");

        System.out.println("\nDeleting task T101:");
        manager.deleteTask("T101");

        manager.displayTasks();
    }
}

