import picocli.CommandLine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
   public static String fileName = "user.ser";
    private static final long serialVersionUID = 1L;
   private String FirstName;
   private String LastName;
   private  String userName;

    public User(String firstName, String lastName, String userName) {
        FirstName = firstName;
        LastName = lastName;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User {" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    static ArrayList<User> readUsersFromFile(String fileName) {
        ArrayList<User> usersInFile = new ArrayList<>();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                FileInputStream inputStream = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                usersInFile = (ArrayList<User>) ois.readObject();
                inputStream.close();
                ois.close();
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return usersInFile;
    }
    static boolean addUsersinFile(String file, ArrayList<User> users) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream ois = new ObjectOutputStream(outputStream);
            ois.writeObject(users);
            outputStream.close();
            ois.close();
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        }
    }
    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getUserName() {
        return userName;
    }


    }

