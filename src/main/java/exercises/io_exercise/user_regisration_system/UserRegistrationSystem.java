package exercises.io_exercise.user_regisration_system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        String fileName = "users_info.csv";

        writeToFile(fileName, name, address, email);
        List<User> users = readFromFileAndPopulate(fileName);

        System.out.println(users);
    }

    private static void writeToFile(String fileName, String name, String address, String email) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(name + "," + address + "," + email + "\n");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static List<User> readFromFileAndPopulate(String fileName) {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    User user = new User(data[0], data[1], data[2]);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}
