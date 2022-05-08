import picocli.CommandLine;

import java.util.ArrayList;

@CommandLine.Command(subcommands = {Commands.ShowAllUsers.class, Commands.createUser.class, Commands.addTask.class, Commands.showTasks.class})
public class Commands implements Runnable {
    static String fileNameUser = "user.ser";

    public static void main(String... args) {
        int exitCode = new CommandLine(new Commands()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
    }

    @CommandLine.Command(name = "-showAllUsers")
    static class ShowAllUsers implements Runnable {
        @Override
        public void run() {
            ArrayList<User> woi = User.readUsersFromFile(User.fileName);
            for (User u : woi)
                System.out.println(u.toString());
        }
    }

    @CommandLine.Command(name = "-createUser")
    static class createUser implements Runnable {
        @CommandLine.Option(names = {"-fn"}, required = true)
        public static String fn;
        @CommandLine.Option(names = {"-ln"}, required = true)
        public static String ln;
        @CommandLine.Option(names = {"-un"}, required = true)
        public static String un;

        @Override
        public void run() {
            User u = new User(fn, ln, un);
            ArrayList<User> usersInFile = User.readUsersFromFile(User.fileName);
            if (usersInFile.stream().anyMatch(x -> x.getUserName().equals(un)) == true)
                System.out.println("This username already exists!");
            else {
                usersInFile.add(u);
                if (User.addUsersinFile(User.fileName, usersInFile) == true)
                    System.out.println("User has been added successfully!");
                else
                    System.out.println("User has not been added!");
            }
        }
    }

    @CommandLine.Command(name = "-addTask")
    static class addTask implements Runnable {
        @CommandLine.Option(names = {"-un"}, required = true)
        public static String un;
        @CommandLine.Option(names = {"-tt"}, required = true)
        public static String tt;
        @CommandLine.Option(names = {"-td"}, required = true)
        public static String td;

        @Override
        public void run() {
            Task t = new Task(un, tt, td);
            ArrayList<User> users = User.readUsersFromFile(User.fileName);
            ArrayList<Task> tasksInFile = Task.readTasksFromFile(Task.fileName);
            if (users.stream().anyMatch(x -> x.getUserName().equals(un)) == false)
                System.out.println("This username doesn't exist!");
            else {
                tasksInFile.add(t);
                if (Task.addTasksinFile(Task.fileName, tasksInFile) == true)
                    System.out.println("Task has been added successfully!");
                else
                    System.out.println("Task has not been added!");
            }
        }
    }

    @CommandLine.Command(name = "-showTasks")
    static class showTasks implements Runnable {
        @Override
        public void run() {
            ArrayList<Task> t = Task.readTasksFromFile(Task.fileName);
            for (Task u : t)
                System.out.println(u.toString());
        }
    }
}