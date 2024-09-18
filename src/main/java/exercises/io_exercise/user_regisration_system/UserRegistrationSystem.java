package exercises.io_exercise.user_regisration_system;

import java.io.FileWriter;
import java.io.IOException;
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

        try (FileWriter writer = new FileWriter("users_info.csv", true)) {
            writer.write(name + "," + address + "," + email + "\n");
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
