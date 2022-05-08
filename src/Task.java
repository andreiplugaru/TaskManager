import java.io.*;
import java.util.ArrayList;

public class Task implements Serializable{
    private static final long serialVersionUID = 1L;
    public static String fileName = "task.ser";
    private String userName;
    private String taskTitle;
    private String taskDescription;

    public Task(String userName, String taskTitle, String taskDescription) {
        this.userName = userName;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
    }

    static ArrayList<Task> readTasksFromFile(String fileName) {
        ArrayList<Task> tasksInFile = new ArrayList<>();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                tasksInFile = (ArrayList<Task>) ois.readObject();
                inputStream.close();
                ois.close();
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return tasksInFile;
    }

    static boolean addTasksinFile(String file, ArrayList<Task> tasks) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream ois = new ObjectOutputStream(outputStream);
            ois.writeObject(tasks);
            outputStream.close();
            ois.close();
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "userName='" + userName + '\'' +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }
}
