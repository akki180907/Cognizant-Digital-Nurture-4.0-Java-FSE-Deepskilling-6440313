public class TaskManager {
    private TaskNode head;

    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Task added: " + task);
    }

    public void deleteTask(String taskId) {
        if (head == null) return;

        if (head.task.taskId.equals(taskId)) {
            head = head.next;
            System.out.println("Deleted task with ID: " + taskId);
            return;
        }

        TaskNode current = head;
        while (current.next != null && !current.next.task.taskId.equals(taskId)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            System.out.println("Deleted task with ID: " + taskId);
        } else {
            System.out.println("Task not found.");
        }
    }

    public Task searchTask(String taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.taskId.equals(taskId)) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    public void displayTasks() {
        TaskNode current = head;
        if (current == null) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("Task List:");
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }
}
