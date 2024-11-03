public class ToDoListManagerToo {


    static class Task {
        private String description;
        private boolean isCompleted;

        public Task(String description) {
            this.description = description;
            this.isCompleted = false;
        }

        public void markAsCompleted() {
            isCompleted = true;
        }

        @Override
        public String toString() {
            return description + " (Completed: " + isCompleted + ")";
        }
    }


    static class TaskList {
        private TaskNode head;


        private static class TaskNode {
            Task task;
            TaskNode next;

            TaskNode(Task task) {
                this.task = task;
                this.next = null;
            }
        }

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
        }

        public void markTaskAsCompleted(String description) {
            TaskNode current = head;
            while (current != null) {
                if (current.task.toString().contains(description)) {
                    current.task.markAsCompleted();
                    return;
                }
                current = current.next;
            }
        }

        public void printTasks() {
            TaskNode current = head;
            while (current != null) {
                System.out.println(current.task);
                current = current.next;
            }
        }
    }

    static class User {
        private String name;
        private TaskList taskList;

        public User(String name) {
            this.name = name;
            this.taskList = new TaskList();
        }

        public void addTask(String description) {
            taskList.addTask(new Task(description));
        }

        public void markTaskAsCompleted(String description) {
            taskList.markTaskAsCompleted(description);
        }

        public void printTasks() {
            System.out.println("Tasks for " + name + ":");
            taskList.printTasks();
        }
    }


    private User[] users = new User[10];  // max of 10 users
    private int userCount = 0;

    public void addUser(String name) {
        if (userCount < users.length) {
            users[userCount++] = new User(name);
        } else {
            System.out.println("User limit reached. Sorry, we're maxed out!");
        }
    }

    public User findUserByName(String name) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].name.equals(name)) {
                return users[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ToDoListManagerToo manager = new ToDoListManagerToo();

        // Add users
        manager.addUser("Naomi");
        manager.addUser("Dave");

        User naomi = manager.findUserByName("Naomi");
        if (naomi != null) {
            naomi.addTask("Buy groceries");
            naomi.addTask("Walk the dog");
            naomi.markTaskAsCompleted("Buy groceries");
            naomi.printTasks();
        }

        User dave = manager.findUserByName("Dave");
        if (dave != null) {
            dave.addTask("Finish SPRINT");
            dave.printTasks();
        }
    }
}
